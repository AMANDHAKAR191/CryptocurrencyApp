package com.aman.cryptocurrencyapp.domain.use_cases.get_coins

import com.aman.cryptocurrencyapp.comman.Resource
import com.aman.cryptocurrencyapp.data.remote.dto.toCoin
import com.aman.cryptocurrencyapp.domain.model.Coin
import com.aman.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
){
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        }catch (e: HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An Unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}