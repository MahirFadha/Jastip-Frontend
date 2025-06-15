package com.example.jastip.ui.screen.user.keranjang

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.model.Keranjang
import com.example.jastip.domain.usecase.user.KeranjangUseCase
import com.example.jastip.domain.usecase.user.PesananUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeranjangViewModel @Inject constructor(
    private val keranjangUseCase: KeranjangUseCase,
    private val pesananUseCase: PesananUseCase,
    application: Application
) : ViewModel() {

    private val _state = MutableStateFlow(KeranjangState(isLoading = true))
    val state = _state.asStateFlow()

    private val context = application.applicationContext
    private val userNim: String? = getLoggedNim(context)

    init {
        if (userNim != null) {
            loadKeranjang()
        } else {
            _state.value = KeranjangState(items = emptyList(), isLoading = false)
        }
    }

    private fun getLoggedNim(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userNim", null)
    }

    private fun loadKeranjang() {
        userNim?.let { nim ->
            viewModelScope.launch {
                keranjangUseCase.getKeranjangByUserNim(nim)
                    .collect { items ->
                        // Pertahankan selectedItems yang sudah ada, tapi hapus yang tidak ada lagi
                        val currentSelected = _state.value.selectedItems
                        val validSelectedItems = currentSelected.filter { selectedId ->
                            items.any { it.id == selectedId }
                        }.toSet()

                        _state.value = _state.value.copy(
                            items = items,
                            selectedItems = validSelectedItems,
                            isLoading = false
                        )
                    }
            }
        }
    }

    fun addItem(item: Keranjang) {
        userNim?.let { nim ->
            val itemWithUser = item.copy(userNim = nim)
            viewModelScope.launch {
                keranjangUseCase.addItem(itemWithUser)
                loadKeranjang()
            }
        }
    }

    fun removeItem(item: Keranjang) {
        userNim?.let {
            viewModelScope.launch {
                keranjangUseCase.deleteItem(item)
                loadKeranjang()
            }
        }
    }

    fun clearKeranjang() {
        userNim?.let { nim ->
            viewModelScope.launch {
                keranjangUseCase.clearCart(nim)
                loadKeranjang()
            }
        }
    }

    fun updateQuantity(itemId: Int, newQuantity: Int) {
        userNim?.let { nim ->
            viewModelScope.launch {
                // Simpan selection saat ini sebelum update
                val currentSelection = _state.value.selectedItems

                // Update quantity di database
                keranjangUseCase.updateQuantity(itemId, newQuantity)

                // Update quantity di state lokal sambil mempertahankan selection
                val updatedItems = _state.value.items.map { item ->
                    if (item.id == itemId) {
                        item.copy(quantity = newQuantity)
                    } else {
                        item
                    }
                }

                // Pastikan selection tetap sama
                _state.value = _state.value.copy(
                    items = updatedItems,
                    selectedItems = currentSelection
                )
            }
        }
    }

    fun setItemSelection(itemId: Int, isSelected: Boolean) {
        val currentSelected = _state.value.selectedItems
        val newSelected = if (isSelected) {
            currentSelected + itemId
        } else {
            currentSelected - itemId
        }
        _state.value = _state.value.copy(selectedItems = newSelected)
    }

    fun toggleSelectAll() {
        val allItemIds = _state.value.items.map { it.id }.toSet()
        val currentSelected = _state.value.selectedItems

        val newSelected = if (currentSelected.size == allItemIds.size) {
            emptySet<Int>() // Deselect all
        } else {
            allItemIds // Select all
        }
        _state.value = _state.value.copy(selectedItems = newSelected)
    }


    fun getSelectedItemsTotal(): Double {
        val totalMenu = _state.value.items
            .filter { _state.value.selectedItems.contains(it.id) }
            .sumOf { it.price * it.quantity }

        return if (totalMenu > 0) {
            totalMenu + getOngkir()
        } else {
            0.0
        }
    }

    fun getOngkir(): Double = 5000.0

    fun order() {
        userNim?.let { nim ->
            viewModelScope.launch {
                val selectedItems =
                    _state.value.items.filter { _state.value.selectedItems.contains(it.id) }

                if (selectedItems.isNotEmpty()) {
                    _state.value = _state.value.copy(isOrdering = true) // Mulai loading
                    val result = pesananUseCase.placeOrder(nim, selectedItems)
                    if (result.isSuccess){
                        selectedItems.forEach { item ->
                            keranjangUseCase.deleteItem(item)
                        }
                        delay(400)
                        loadKeranjang()
                    }else{
                        Log.e("KeranjangViewModel", "Error placing order: ${result.exceptionOrNull()?.message}")
                        }
                    _state.value = _state.value.copy(isOrdering = false)
                    }
                }

            }
        }
    }




