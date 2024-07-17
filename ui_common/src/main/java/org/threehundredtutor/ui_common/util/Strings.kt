package org.threehundredtutor.ui_common.util

import android.net.Uri
import android.text.Spanned
import androidx.core.text.HtmlCompat

private const val VND_YOUTUBE_APP = "vnd.youtube:"
private const val VND_YOUTUBE_BROWSER = "http://www.youtube.com/watch?v="

fun String.addQuotesSymbol() = "'$this'"

fun String.addPercentSymbol() = "$this%"

fun String.videoId() = this.substringAfterLast('=')

fun String.getUrlYoutube(): Pair<Uri, Uri> =
    Uri.parse(VND_YOUTUBE_APP + this.videoId()) to Uri.parse(VND_YOUTUBE_BROWSER + this.videoId())

fun String.fromHtml(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)