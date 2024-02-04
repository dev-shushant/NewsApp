package dev.shushant.network.source

import dev.shushant.model.Article
import dev.shushant.model.Source
import dev.shushant.network.service.NewsService
import kotlinx.datetime.Instant
import org.json.JSONObject
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor() : NetworkDataSource {
    override suspend fun getNewsData(): Result<List<Article>> {
        val result = NewsService.getNewsData()
        return takeIf { result.isSuccess }?.let {
            result.getOrNull()?.parseNewsResponse()
        } ?: run {
            Result.failure(result.exceptionOrNull() ?: Exception())
        }
    }
}


fun JSONObject.optStringOrNull(key: String): String? {
    return if (has(key)) optString(key) else null
}

fun JSONObject.toSource(): Source {
    val sourceObject = getJSONObject("source")
    return Source(
        id = sourceObject.optStringOrNull("id"), name = sourceObject.getString("name")
    )
}

fun JSONObject.toArticle(): Article {
    return Article(
        source = toSource(),
        author = optStringOrNull("author"),
        title = getString("title"),
        description = optStringOrNull("description"),
        url = getString("url"),
        urlToImage = optStringOrNull("urlToImage"),
        publishedAt = Instant.parse(getString("publishedAt")),
        content = optStringOrNull("content")
    )
}

fun String.parseNewsResponse(): Result<List<Article>> {
    return runCatching {
        val jsonObject = JSONObject(this)
        val status = jsonObject.getString("status")
        if (status == "ok") {
            val articlesArray = jsonObject.getJSONArray("articles")

            val articles = (0 until articlesArray.length()).map {
                articlesArray.getJSONObject(it).toArticle()
            }
            Result.success(articles)
        } else {
            throw Exception("No data found!")
        }
    }.getOrElse { exception ->
        Result.failure(exception)
    }
}