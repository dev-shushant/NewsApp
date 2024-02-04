package dev.shushant.domain

import dev.shushant.data.repository.NewsRepository
import javax.inject.Inject

class NewsFeedUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke() = newsRepository.getNewsData()
}