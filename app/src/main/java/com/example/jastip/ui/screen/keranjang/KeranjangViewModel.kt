package com.example.jastip.ui.screen.keranjang

import android.app.Application
import android.content.Context
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
    private val keranjangUseCase: KeranjangUseCase,
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
                        _state.value = KeranjangState(items = items, isLoading = false)
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
                keranjangUseCase.updateQuantity(itemId, newQuantity)
                loadKeranjang()
            }
        }
    }
}

