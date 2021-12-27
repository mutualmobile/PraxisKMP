package com.baseio.kmm.data.network

import com.baseio.kmm.domain.model.GithubReposItem

interface GithubTrendingAPI {
    suspend fun getTrendingRepos(
        query: String
    ): List<GithubReposItem>
}