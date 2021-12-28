package com.baseio.kmm.data.local

import com.baseio.kmm.domain.model.GithubReposItem
import db.Trending_repos
import kotlinx.coroutines.flow.Flow

interface GithubTrendingLocal {
    fun saveRepos(input: List<GithubReposItem>)
    fun getAll(): Flow<List<Trending_repos>>

}