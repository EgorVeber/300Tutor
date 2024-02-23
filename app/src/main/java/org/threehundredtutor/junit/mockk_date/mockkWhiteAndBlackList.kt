package org.threehundredtutor.junit.mockk_date

fun getAllWhiteList() =
    listOf(
        "11",
        "22",
        "33",
        "44",
        "55",
        "66",
        "12312311",
        "asdasdass",
    )

fun getMockWhiteList() =
    listOf(
        "11",
        "22",
        "33",
        "44",
        "55",
        "12312311",
        "asdasdass",
    )

fun getAllBlackList() =
    listOf(
        "11",
        "22",
        "33",
        "44",
        "55",
        "66",
        "12312311",
        "asdasdass",
    )

fun getMockkBlackList() =
    listOf(
        "11",
        "44",
        "12312311",
        "asdasdass",
    )
