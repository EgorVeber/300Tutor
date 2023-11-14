package org.threehundredtutor.data.subject

import org.threehundredtutor.data.subject.SubjectApi.TUTOR_SUBJECT_GET_ALL_CACHED
import org.threehundredtutor.data.subject.SubjectApi.TUTOR_SUBJECT_SEARCH
import org.threehundredtutor.data.subject.request.SearchTestsRequest
import org.threehundredtutor.data.subject.response.SearchTestResponse
import org.threehundredtutor.data.subject.response.SubjectResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SubjectService {
    @GET(TUTOR_SUBJECT_GET_ALL_CACHED)
    suspend fun getSubjects(): List<SubjectResponse>

    @POST(TUTOR_SUBJECT_SEARCH)
    suspend fun searchTests(@Body params: SearchTestsRequest): SearchTestResponse
}
