package dev.shushant.data.repository

import dev.shushant.model.Article

interface NewsRepository {
    suspend fun getNewsData(): Result<List<Article>>
}