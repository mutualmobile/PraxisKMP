package com.baseio.kmm.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GithubReposItem(
    val author: String? = null,
    val avatar: String? = null,
    val description: String? = null,
    val language: String? = null,
    val name: String? = null,
    val url: String? = null
)

