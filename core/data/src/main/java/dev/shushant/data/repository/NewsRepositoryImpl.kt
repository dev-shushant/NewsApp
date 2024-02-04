package dev.shushant.data.repository

import dev.shushant.data.utils.AppDispatcher
import dev.shushant.model.Article
import dev.shushant.network.source.NetworkDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource, private val appDispatcher: AppDispatcher
) : NewsRepository {
    override suspend fun getNewsData(): Result<List<Article>> {
        return withContext(appDispatcher.dispatcherIO) {
            networkDataSource.getNewsData()
        }
    }

}