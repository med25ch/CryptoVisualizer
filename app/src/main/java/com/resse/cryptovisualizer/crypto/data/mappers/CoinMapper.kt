package com.resse.cryptovisualizer.crypto.data.mappers

import com.resse.cryptovisualizer.crypto.data.networking.dto.CoinDto
import com.resse.cryptovisualizer.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}