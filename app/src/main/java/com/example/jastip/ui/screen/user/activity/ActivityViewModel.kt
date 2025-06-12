package com.example.jastip.ui.screen.user.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.model.RiwayatPesanan
import com.example.jastip.domain.usecase.RiwayatPesananUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val riwayatPesananUseCase: RiwayatPesananUseCase
) : ViewModel() {

    private val riwayat = mutableStateOf<List<RiwayatPesanan>>(emptyList())
    val riwayatPesanan: State<List<RiwayatPesanan>> = riwayat

    fun loadPesanan(nim: String) {
        viewModelScope.launch {
            val hasil = riwayatPesananUseCase(nim)
//            Log.d("VIEWMODEL", "Pesanan untuk $nim: ${hasil.size}") // Tambahkan ini
            riwayat.value = hasil        }

    }
}