package org.v_points.data.main.response

import com.google.gson.annotations.SerializedName
import org.v_points.data.subject_tests.models.IconResponse

class GroupWithCourseProgressResponse(
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
    @SerializedName("icon")
    val iconResponse: IconResponse?
)
