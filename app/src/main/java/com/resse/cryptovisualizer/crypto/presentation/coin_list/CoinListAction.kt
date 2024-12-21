package com.resse.cryptovisualizer.crypto.presentation.coin_list

import com.resse.cryptovisualizer.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}