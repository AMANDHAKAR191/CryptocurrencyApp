package com.aman.cryptocurrencyapp.data.repository

import com.aman.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.aman.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.aman.cryptocurrencyapp.data.remote.dto.CoinDto
import com.aman.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        println("CoinRepositoryImpl + getCoins()")
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}