package dev.shushant.dashboard

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier,
    onShowSnackBar: suspend (String, String?) -> Unit = { _: String, _: String? -> },
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    val uiState by dashboardViewModel.state.collectAsStateWithLifecycle()
    val okText = stringResource(id = R.string.ok)
    val context = LocalContext.current
    LaunchedEffect(key1 = uiState) {
        (uiState.error).takeIf { it != null }?.let {
            onShowSnackBar.invoke(it, okText)
        }
    }
    DashboardScreen(dashboardState = uiState, modifier = modifier, onPostClick = {
        context.startActivity(Intent(Intent.ACTION_VIEW).apply { data = it.toUri() })
    }, onArrangementClick = dashboardViewModel::onArrangementClick, onRefresh = dashboardViewModel::refreshNewsData)
}






