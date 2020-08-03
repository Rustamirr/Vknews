package com.example.vknews.data.news.network

import com.example.vknews.domain.news.NewsFeed
import com.example.vknews.domain.news.NewsInfo
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

private const val ATTACHMENT_TYPE_PHOTO = "photo"
private const val ATTACHMENT_TYPE_LINK = "link"

fun GetNewsResponse.toNewsFeed(groupResponse: GroupResponse) = newsResponse.run {
    if (postResponses == null) return@run NewsFeed(nextPageKey, emptyList())

    val list = postResponses
        .filter(::postPredicate)
        .map { it.toNewsInfo(groupResponse.name) }

    return NewsFeed(nextPageKey, list)
}

private fun postPredicate(postResponse: PostResponse): Boolean {
    if (postResponse.attachmentResponses == null) return false
    return postResponse.attachmentResponses.any { it.type == ATTACHMENT_TYPE_PHOTO || it.type == ATTACHMENT_TYPE_LINK }
}

private fun PostResponse.toNewsInfo(groupName: String): NewsInfo {
    val attachmentResponse = requireNotNull(attachmentResponses)
        .first { it.type == ATTACHMENT_TYPE_PHOTO || it.type == ATTACHMENT_TYPE_LINK }

    val date = LocalDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneId.systemDefault())

    return when (attachmentResponse.type) {
        ATTACHMENT_TYPE_LINK -> {
            val linkResponse = requireNotNull(attachmentResponse.linkResponse)
            NewsInfo(
                id,
                linkResponse.title,
                linkResponse.url + "\n" + linkResponse.description + "\n" + text,
                date,
                linkResponse.photo.sizeResponses?.lastOrNull()?.imageUrl
            )
        }
        else -> {
            val photoResponse = requireNotNull(attachmentResponse.photoResponse)
            NewsInfo(
                id,
                groupName,
                text,
                date,
                photoResponse.sizeResponses?.lastOrNull()?.imageUrl
            )
        }
    }
}