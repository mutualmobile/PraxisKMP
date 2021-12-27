package com.baseio.kmm.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GithubReposItem(
    val author: String? = null,
    val avatar: String? = null,
    val builtBy: List<BuiltBy>? = null,
    val currentPeriodStars: Int? = null,
    val description: String? = null,
    val forks: Int? = null,
    val language: String? = null,
    val languageColor: String? = null,
    val name: String? = null,
    val stars: Int? = null,
    val url: String? = null
)

@Serializable
data class BuiltBy(
    val avatar: String? = null,
    val href: String? = null,
    val username: String? = null
)