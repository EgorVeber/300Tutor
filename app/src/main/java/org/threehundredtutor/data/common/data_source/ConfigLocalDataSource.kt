package org.threehundredtutor.data.common.data_source

import org.threehundredtutor.BuildConfig

class ConfigLocalDataSource {
    //TODO TutorAndroid-67 Добавить механизм смены домена.
    val baseUrl = BuildConfig.BASE_URL_TEST

    val refId = BuildConfig.REF_ID

    val siteUrl = BuildConfig.SITE_URL

    val staticMediumUrl = BuildConfig.STATIC_MEDIUM_URL

    val staticOriginalUrl = BuildConfig.STATIC_ORIGINAL_URL

    val telegramBotUrl = BuildConfig.TELEGRAM_BOT_URL
}