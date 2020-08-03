package com.example.vknews.data.news.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetGroupsResponse(
    @Expose
    @SerializedName("response")
    val groupsResponse: GroupsResponse
)

data class GroupsResponse(
    @Expose
    @SerializedName("items")
    val groupResponses: List<GroupResponse>?
)

data class GroupResponse(
    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("name")
    val name: String
)