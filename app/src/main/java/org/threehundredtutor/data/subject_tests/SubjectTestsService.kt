package org.threehundredtutor.data.subject_tests

import org.threehundredtutor.data.subject_tests.SubjectTestsApi.TUTOR_SUBJECT_SEARCH
import org.threehundredtutor.data.subject_tests.models.SearchTestResponse
import org.threehundredtutor.data.subject_tests.models.SearchTestsRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SubjectTestsService {
    @POST(TUTOR_SUBJECT_SEARCH)
    suspend fun searchTests(@Body params: SearchTestsRequest): SearchTestResponse
}
