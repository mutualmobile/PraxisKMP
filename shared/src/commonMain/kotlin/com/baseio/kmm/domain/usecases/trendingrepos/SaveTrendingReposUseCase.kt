package com.baseio.kmm.domain.usecases.trendingrepos

import com.baseio.kmm.data.local.GithubTrendingLocal
import com.baseio.kmm.domain.model.GithubReposItem
import com.baseio.kmm.domain.usecases.BaseUseCase

class SaveTrendingReposUseCase(private val githubTrendingLocal: GithubTrendingLocal) :
    BaseUseCase<List<GithubReposItem>, Unit> {
    override suspend fun perform(input: List<GithubReposItem>?) {
        return githubTrendingLocal.saveRepos(input!!)
    }
}