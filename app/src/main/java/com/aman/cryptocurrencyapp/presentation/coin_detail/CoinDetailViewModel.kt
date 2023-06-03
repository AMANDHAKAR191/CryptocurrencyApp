package com.aman.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.cryptocurrencyapp.comman.Constraints
import com.aman.cryptocurrencyapp.comman.Resource
import com.aman.cryptocurrencyapp.domain.use_cases.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constraints.PARAM_COIN_ID)?.let { coinId->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch{
            getCoinUseCase(coinId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CoinDetailState(coin = result.data)
                    }

                    is Resource.Error -> {
                        _state.value = CoinDetailState(error = "An unexpected error occurred.")
                    }

                    is Resource.Loading -> {
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
            }
        }

    }
}