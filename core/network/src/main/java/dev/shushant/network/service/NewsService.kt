package dev.shushant.network.service

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException


object NewsService {
    private const val API_URL =
        "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"

    fun getNewsData(): Result<String> {
        val url = URL(API_URL)
        val connection = (url.openConnection() as HttpURLConnection)
        return runCatching {
            connection.connect()
            val result = BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                reader.readText()
            }
            connection.disconnect()
            Result.success(result)
        }.getOrElse {
            connection.disconnect()
            return@getOrElse when (it) {
                is UnknownHostException -> {
                    Result.failure(Exception("Please check your internet connection."))
                }

                else -> Result.failure(it)
            }
        }
    }
}