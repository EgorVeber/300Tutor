package org.threehundredtutor.data.main

import org.threehundredtutor.data.main.MainApi.TUTOR_SETTINGS_EXTRA_BUTTONS
import org.threehundredtutor.data.main.MainApi.TUTOR_STUDENT_GROUP_ENTER_VIA_CODE
import org.threehundredtutor.data.main.MainApi.TUTOR_STUDENT_GROUP_QUERY_SEARCH_WITH_PROGRESSES
import org.threehundredtutor.data.main.MainApi.TUTOR_SUBJECT_GET_ALL_CACHED
import org.threehundredtutor.data.main.MainApi.TUTOR_SUBJECT_SEARCH
import org.threehundredtutor.data.main.request.EnterGroupRequest
import org.threehundredtutor.data.main.request.GroupWithCourseRequest
import org.threehundredtutor.data.main.request.SearchTestsRequest
import org.threehundredtutor.data.main.response.EnterGroupResponse
import org.threehundredtutor.data.main.response.ExtraButtonResponse
import org.threehundredtutor.data.main.response.GroupWithCourseResponse
import org.threehundredtutor.data.main.response.SearchTestResponse
import org.threehundredtutor.data.main.response.SubjectResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainService {
    @GET(TUTOR_SUBJECT_GET_ALL_CACHED)
    suspend fun getSubjects(): List<SubjectResponse>

    @POST(TUTOR_SUBJECT_SEARCH)
    suspend fun searchTests(@Body params: SearchTestsRequest): SearchTestResponse

    @POST(TUTOR_STUDENT_GROUP_QUERY_SEARCH_WITH_PROGRESSES)
    suspend fun getCourses(@Body params: GroupWithCourseRequest): GroupWithCourseResponse

    @POST(TUTOR_STUDENT_GROUP_ENTER_VIA_CODE)
    suspend fun enterGroup(@Body params: EnterGroupRequest): EnterGroupResponse

    @GET(TUTOR_SETTINGS_EXTRA_BUTTONS)
    suspend fun getExtraButtons(): ExtraButtonResponse
}