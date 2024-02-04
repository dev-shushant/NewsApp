package dev.shushant.model

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Article(
    val source: Source,
    val author: String? = "UnKnown",
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Instant,
    val content: String?
) {
    val desc = content.takeIf { it == "null" }?.let { description } ?: run { content }
    val authorName = author.takeIf { it == "null" }?.let { "UnKnown" } ?: run { author }
    val date = publishedAt.toLocalDateTime(TimeZone.UTC).date.toString()
}

data class Source(
    val id: String?,
    val name: String
)
