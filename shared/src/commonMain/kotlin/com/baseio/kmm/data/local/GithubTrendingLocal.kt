package com.baseio.kmm.data.local

import com.baseio.kmm.domain.model.GithubReposItem
import com.squareup.sqldelight.db.SqlDriver
import db.Trending_repos
import kotlinx.coroutines.flow.Flow

interface GithubTrendingLocal {
    var driver: SqlDriver?
    fun saveRepos(input: List<GithubReposItem>)
    fun getAll(): Flow<List<Trending_repos>>
}