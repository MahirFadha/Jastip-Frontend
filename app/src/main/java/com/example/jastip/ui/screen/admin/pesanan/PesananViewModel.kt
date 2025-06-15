package com.example.jastip.ui.screen.admin.pesanan

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.usecase.admin.PesananDiprosesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PesananViewModel @Inject constructor(
    private val pesananUseCase: PesananDiprosesUseCase
): ViewModel(){

    private val stateKhusus = mutableStateOf(PesananState())
    val state: State<PesananState> = stateKhusus

    init {
        loadPesanan()
    }

    fun loadPesanan(){
        viewModelScope.launch {
            stateKhusus.value = stateKhusus.value.copy(isLoading = true)
            try {
                val pesanan = pesananUseCase()
                stateKhusus.value = stateKhusus.value.copy(pesananList = pesanan, isLoading = false)
            } catch (e: Exception) {
                print(e.printStackTrace())
            }
        }
    }
}