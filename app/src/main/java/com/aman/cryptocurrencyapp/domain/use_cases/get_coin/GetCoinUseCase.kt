package com.aman.cryptocurrencyapp.domain.use_cases.get_coin

import com.aman.cryptocurrencyapp.comman.Resource
import com.aman.cryptocurrencyapp.data.remote.dto.toCoin
import com.aman.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.aman.cryptocurrencyapp.domain.model.Coin
import com.aman.cryptocurrencyapp.domain.model.CoinDetail
import com.aman.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
){
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An Unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}