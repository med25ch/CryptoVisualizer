package com.resse.cryptovisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.resse.cryptovisualizer.core.presentation.util.ObserveAsEvents
import com.resse.cryptovisualizer.core.presentation.util.toString
import com.resse.cryptovisualizer.crypto.presentation.coin_detail.CoinDetailScreen
import com.resse.cryptovisualizer.crypto.presentation.coin_list.CoinListEvent
import com.resse.cryptovisualizer.crypto.presentation.coin_list.CoinListScreen
import com.resse.cryptovisualizer.crypto.presentation.coin_list.CoinListViewModel
import com.resse.cryptovisualizer.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {

                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { innerPadding ->

                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current



                    ObserveAsEvents(events = viewModel.events) { event ->
                        when(event) {
                            is CoinListEvent.Error -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(event.error.toString(context))
                                }
                            }
                        }
                    }
                    when {
                        state.selectedCoin != null -> {
                            CoinDetailScreen(
                                state = state,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        else -> {
                            CoinListScreen(
                                state = state,
                                modifier = Modifier.padding(innerPadding),
                                onAction = viewModel::onAction
                            )
                        }

                    }
                }
            }
        }
    }
}
