package dev.shushant.dashboard

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.shushant.designsystem.theme.LoadingAnimation
import kotlinx.coroutines.launch

/**
 * A composable that represents the dashboard screen displaying a list of news articles and filter options.
 *
 * @param modifier The [Modifier] for configuring the compatible's layout behavior.
 * @param dashboardState The [DashboardUIState] representing the state of the dashboard screen.
 * @param onPostClick The lambda function to be invoked when a news article is clicked.
 * @param onArrangementClick The lambda function to be invoked when a filter option is selected.
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier,
    dashboardState: DashboardUIState,
    onPostClick: (String) -> Unit,
    onArrangementClick: (NewsArrangement) -> Unit,
    onRefresh: () -> Unit
) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val selectedArrangement = remember { mutableStateOf("") }
    val state = rememberPullToRefreshState()
    val scaleFraction = if (state.isRefreshing) 1f else
        LinearOutSlowInEasing.transform(state.progress).coerceIn(0f, 1f)

    if (state.isRefreshing) {
        onRefresh.invoke()
    }
    if (dashboardState.isRefreshing.not()) {
        NewsArrangement.getValue(selectedArrangement.value)
            ?.let { onArrangementClick.invoke(it) }
        state.endRefresh()
    }


    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(state.nestedScrollConnection)
    ) {
        if (dashboardState.isLoading) {
            LoadingAnimation(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag(stringResource(R.string.loader))
            )
        }
        LazyColumn(
            Modifier.fillMaxSize(),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NewsArrangement.entries.forEach { newsArrangement ->
                        ArticleArrangementChip(newsArrangement, selectedArrangement) {
                            onArrangementClick.invoke(it).also {
                                coroutineScope.launch {
                                    scrollState.scrollToItem(0)
                                }
                            }
                        }
                    }
                }
            }
            items(dashboardState.newsArticles, key = { it.url }) {
                NewsItem(it, onPostClick)
            }
        }

        PullToRefreshContainer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction),
            state = state,
        )
    }
}