package com.resse.cryptovisualizer.crypto.data.networking

import com.resse.cryptovisualizer.core.data.networking.constructUrl
import com.resse.cryptovisualizer.core.data.networking.safeCall
import com.resse.cryptovisualizer.core.domain.util.NetworkError
import com.resse.cryptovisualizer.core.domain.util.Result
import com.resse.cryptovisualizer.core.domain.util.map
import com.resse.cryptovisualizer.crypto.data.mappers.toCoin
import com.resse.cryptovisualizer.crypto.data.networking.dto.CoinsResponseDto
import com.resse.cryptovisualizer.crypto.domain.Coin
import com.resse.cryptovisualizer.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource (
    private val httpClient: HttpClient
): CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}