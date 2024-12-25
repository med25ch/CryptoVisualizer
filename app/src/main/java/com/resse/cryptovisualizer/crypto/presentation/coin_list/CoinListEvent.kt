package com.resse.cryptovisualizer.crypto.presentation.coin_list

import com.resse.cryptovisualizer.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}