package com.aman.cryptocurrencyapp.presentation.coin_detail

import com.aman.cryptocurrencyapp.domain.model.Coin
import com.aman.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
