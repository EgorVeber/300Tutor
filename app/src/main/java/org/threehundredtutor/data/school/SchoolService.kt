package org.threehundredtutor.data.school

import org.threehundredtutor.data.school.SchoolApi.TUTOR_EXTERNAL_SCHOOL_QUERY_GET_ALL_CACHED
import org.threehundredtutor.data.school.model.GetSchoolResponse
import retrofit2.http.GET

interface SchoolService {
    @GET(TUTOR_EXTERNAL_SCHOOL_QUERY_GET_ALL_CACHED)
    suspend fun getSchool(): GetSchoolResponse
}
