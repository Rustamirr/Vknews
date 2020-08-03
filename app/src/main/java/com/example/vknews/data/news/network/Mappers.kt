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

    return when (attachmentResponse.type) {
        ATTACHMENT_TYPE_LINK -> {
            val linkResponse = requireNotNull(attachmentResponse.linkResponse)
            NewsInfo(
                id = id,
                title = linkResponse.title,
                date = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault()),
                description = linkResponse.url + "\n" + linkResponse.description + "\n" + text,
                imageUrl = linkResponse.photo.sizeResponses.last().imageUrl
            )
        }
        else -> {
            val photoResponse = requireNotNull(attachmentResponse.photoResponse)
            NewsInfo(
                id = id,
                title = groupName,
                date = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault()),
                description = text,
                imageUrl = photoResponse.sizeResponses.last().imageUrl
            )
        }
    }
}