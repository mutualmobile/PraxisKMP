package com.baseio.kmm.domain.usecases.trendingrepos

import com.baseio.kmm.data.network.GithubTrendingAPI
import com.baseio.kmm.domain.model.GithubReposItem
import com.baseio.kmm.domain.usecases.BaseUseCase

class FetchTrendingReposUseCase(private val githubTrendingAPI: GithubTrendingAPI) :
    BaseUseCase<String, List<GithubReposItem>> {
    override suspend fun perform(input: String?): List<GithubReposItem> {
        return githubTrendingAPI.getTrendingRepos(input!!)
    }
}