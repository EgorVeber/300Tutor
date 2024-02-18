package org.threehundredtutor.data.main.response

import com.google.gson.annotations.SerializedName

class GroupWithCourseProgressResponse (
    @SerializedName("groupId")
    val groupId: String?,
    @SerializedName("groupName")
    val groupName: String?,
    @SerializedName("groupNameHtml")
    val groupNameHtml: String?,
    @SerializedName("groupUseHtmlForName")
    val groupUseHtmlForName: Boolean?,
    @SerializedName("isBlocked")
    val isBlocked: Boolean?,
    @SerializedName("shouldBlockOnUtc")
    val shouldBlockOnUtc: String?,
    @SerializedName("useCourse")
    val useCourse: Boolean?,
    @SerializedName("courseProgress")
    val courseProgressResponse: CourseProgressResponse?,
)
