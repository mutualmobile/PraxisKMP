package com.baseio.kmm.data.network

import com.baseio.kmm.domain.model.GithubReposItem
import io.ktor.client.*
import io.ktor.client.request.*

const val BASE_URL = "https://gtrend.yapie.me"

class GithubTrendingAPIImpl(private val httpClient: HttpClient) : GithubTrendingAPI {
    override suspend fun getTrendingRepos(query: String): List<GithubReposItem> {
        return httpClient.get("$BASE_URL/repositories?language=$query")
    }
}