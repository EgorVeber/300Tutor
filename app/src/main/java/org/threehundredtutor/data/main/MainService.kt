package org.threehundredtutor.data.main

import org.threehundredtutor.data.main.MainApi.QUERY_ICON_SET_ID
import org.threehundredtutor.data.main.MainApi.TUTOR_SETTINGS_EXTRA_BUTTONS
import org.threehundredtutor.data.main.MainApi.TUTOR_STUDENT_GROUP_ENTER_VIA_CODE
import org.threehundredtutor.data.main.MainApi.TUTOR_STUDENT_GROUP_QUERY_SEARCH_WITH_PROGRESSES
import org.threehundredtutor.data.main.MainApi.TUTOR_STUDENT_TELEGRAM_CREATE_LINK
import org.threehundredtutor.data.main.MainApi.TUTOR_STUDENT_TELEGRAM_GET_DATA
import org.threehundredtutor.data.main.MainApi.TUTOR_SUBJECT_QUERY_GET_ALL_WITH_ICONS_CACHED
import org.threehundredtutor.data.main.request.EnterGroupRequest
import org.threehundredtutor.data.main.request.GroupWithCourseRequest
import org.threehundredtutor.data.main.response.CreateLinkTelegramResponse
import org.threehundredtutor.data.main.response.EnterGroupResponse
import org.threehundredtutor.data.main.response.ExtraButtonResponse
import org.threehundredtutor.data.main.response.GroupWithCourseResponse
import org.threehundredtutor.data.main.response.TelegramDataResponse
import org.threehundredtutor.data.subject_tests.models.SubjectResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MainService {
    @GET(TUTOR_SUBJECT_QUERY_GET_ALL_WITH_ICONS_CACHED)
    suspend fun getSubjects(@Query(QUERY_ICON_SET_ID) iconSetId: String): List<SubjectResponse>

    @POST(TUTOR_STUDENT_GROUP_QUERY_SEARCH_WITH_PROGRESSES)
    suspend fun getCourses(@Body params: GroupWithCourseRequest): GroupWithCourseResponse

    @POST(TUTOR_STUDENT_GROUP_ENTER_VIA_CODE)
    suspend fun enterGroup(@Body params: EnterGroupRequest): EnterGroupResponse

    @GET(TUTOR_SETTINGS_EXTRA_BUTTONS)
    suspend fun getExtraButtons(): ExtraButtonResponse

    @GET(TUTOR_STUDENT_TELEGRAM_GET_DATA)
    suspend fun getTelegramData(): TelegramDataResponse

    @POST(TUTOR_STUDENT_TELEGRAM_CREATE_LINK)
    suspend fun createLinkTelegram(): CreateLinkTelegramResponse
}