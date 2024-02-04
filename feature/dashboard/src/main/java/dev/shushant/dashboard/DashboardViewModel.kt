package dev.shushant.dashboard

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.shushant.data.base.BaseViewModel
import dev.shushant.data.base.State
import dev.shushant.data.utils.NetworkMonitor
import dev.shushant.domain.NewsFeedUseCase
import dev.shushant.model.Article
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val newsFeedUseCase: NewsFeedUseCase, networkMonitor: NetworkMonitor
) : BaseViewModel<DashboardUIState>(DashboardUIState()) {

    private val isOnline = networkMonitor.isOnline.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = true,
    )

    init {
        observeNetworkConnectivity()
    }

    private fun observeNetworkConnectivity() {
        viewModelScope.launch {
            isOnline.collect {
                if (it && currentState.newsArticles.isEmpty()) {
                    fetchNewsData()
                }
            }
        }
    }

    private suspend fun fetchNewsData() {
        val result = newsFeedUseCase.invoke()
        when {
            result.isSuccess -> {
                setState { state ->
                    state.copy(
                        newsArticles = result.getOrDefault(listOf()),
                        isLoading = false,
                        isRefreshing = false
                    )
                }
            }

            result.isFailure -> {
                setState { state ->
                    state.copy(
                        error = result.exceptionOrNull()?.message,
                        isLoading = false,
                        isRefreshing = false
                    )
                }
            }
        }
    }

    fun onArrangementClick(arrangement: NewsArrangement) {
        when (arrangement) {
            NewsArrangement.NewToOld -> setState { state -> state.copy(newsArticles = currentState.newsArticles.sortedByDescending { it.publishedAt.epochSeconds }) }
            NewsArrangement.OldToNew -> setState { state ->
                state.copy(newsArticles = currentState.newsArticles.sortedByDescending { it.publishedAt.epochSeconds }
                    .reversed())
            }
        }
    }

    fun refreshNewsData() {
        viewModelScope.launch {
            setState { state -> state.copy(isRefreshing = true) }.also {
                fetchNewsData()
            }
        }
    }
}

data class DashboardUIState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val isRefreshing: Boolean = false,
    val newsArticles: List<Article> = listOf()
) : State