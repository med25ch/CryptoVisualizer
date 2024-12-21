package com.resse.cryptovisualizer.crypto.domain

import com.resse.cryptovisualizer.core.domain.util.NetworkError
import com.resse.cryptovisualizer.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}