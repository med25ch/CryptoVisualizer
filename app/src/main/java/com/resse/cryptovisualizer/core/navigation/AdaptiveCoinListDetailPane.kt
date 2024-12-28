package com.resse.cryptovisualizer.core.navigation

import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.resse.cryptovisualizer.core.presentation.util.ObserveAsEvents
import com.resse.cryptovisualizer.core.presentation.util.toString
import com.resse.cryptovisualizer.crypto.presentation.coin_detail.CoinDetailScreen
import com.resse.cryptovisualizer.crypto.presentation.coin_list.CoinListAction
import com.resse.cryptovisualizer.crypto.presentation.coin_list.CoinListEvent
import com.resse.cryptovisualizer.crypto.presentation.coin_list.CoinListScreen
import com.resse.cryptovisualizer.crypto.presentation.coin_list.CoinListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(events = viewModel.events) { event ->
        when(event) {
            is CoinListEvent.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(event.error.toString(context))
                }
            }
        }
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    state = state,
                    onAction = { action ->
                        viewModel.onAction(action)
                        when(action) {
                            is CoinListAction.OnCoinClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailScreen(state = state)
            }
        },
        modifier = modifier
    )
}