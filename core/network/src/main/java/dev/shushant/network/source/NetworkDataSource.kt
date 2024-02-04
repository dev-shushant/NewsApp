package dev.shushant.network.source

import dev.shushant.model.Article


interface NetworkDataSource {
    suspend fun getNewsData(): Result<List<Article>>
}