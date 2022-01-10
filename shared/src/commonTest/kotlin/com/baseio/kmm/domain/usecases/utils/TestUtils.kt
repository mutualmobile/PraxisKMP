package com.baseio.kmm.domain.usecases.utils

import com.baseio.kmm.domain.model.GithubReposItem

val testItem = GithubReposItem(
    author = "Shubham",
    avatar = "Test",
    description = "This is a test",
    language = "Kotlin Multiplatform Mobile",
    name = "TestName",
    url = "TestUrl"
)

val testListOfItems = listOf(testItem)
