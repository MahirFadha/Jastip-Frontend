package com.example.jastip.ui.screen.keranjang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.model.Keranjang
import com.example.jastip.domain.usecase.KeranjangUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeranjangViewModel @Inject constructor(
    private val keranjangUseCase: KeranjangUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(KeranjangState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        loadKeranjang()
    }

    private fun loadKeranjang() {
        viewModelScope.launch {
            keranjangUseCase.getAllItems()
                .collect { items ->
                    _state.value = KeranjangState(items = items, isLoading = false)
                }
        }
    }

    fun addItem(item: Keranjang) {
        viewModelScope.launch {
            keranjangUseCase.addItem(item)
            loadKeranjang() // Refresh after add
        }
    }

    fun removeItem(item: Keranjang) {
        viewModelScope.launch {
            keranjangUseCase.deleteItem(item)
            loadKeranjang() // Refresh after remove
        }
    }

    fun clearKeranjang() {
        viewModelScope.launch {
            keranjangUseCase.clearCart()
            loadKeranjang() // Refresh after clear
        }
    }

    fun updateQuantity(itemId: Int, newQuantity: Int) {
        viewModelScope.launch {
            keranjangUseCase.updateQuantity(itemId, newQuantity)
            loadKeranjang() // Refresh after update
        }
    }
}
