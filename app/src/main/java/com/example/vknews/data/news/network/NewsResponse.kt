package com.example.vknews.data.news.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetNewsResponse(
    @Expose
    @SerializedName("response")
    val newsResponse: NewsResponse
)

data class NewsResponse(
    @Expose
    @SerializedName("items")
    val postResponses: List<PostResponse>?,

    @Expose
    @SerializedName(" next_from")
    val nextPageKey: String?
)

data class PostResponse(
    @Expose
    @SerializedName("post_id")
    val id: Long,

    @Expose
    @SerializedName("date")
    val date: Long,

    @Expose
    @SerializedName("text")
    val text: String,

    @Expose
    @SerializedName("attachments")
    val attachmentResponses: List<AttachmentResponse>?
)

data class AttachmentResponse(
    @Expose
    @SerializedName("type")
    val type: String,

    @Expose
    @SerializedName("photo")
    val photoResponse: PhotoResponse?,

    @Expose
    @SerializedName("link")
    val linkResponse: LinkResponse?
)

data class LinkResponse(
    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("url")
    val url: String,

    @Expose
    @SerializedName("photo")
    val photo: PhotoResponse
)

data class PhotoResponse(
    @Expose
    @SerializedName("sizes")
    val sizeResponses: List<SizeResponse>?
)

data class SizeResponse(
    @Expose
    @SerializedName("url")
    val imageUrl: String
)
