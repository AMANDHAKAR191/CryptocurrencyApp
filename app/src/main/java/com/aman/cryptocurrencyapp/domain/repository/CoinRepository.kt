package com.aman.cryptocurrencyapp.domain.repository

import com.aman.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.aman.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId:String): CoinDetailDto
}