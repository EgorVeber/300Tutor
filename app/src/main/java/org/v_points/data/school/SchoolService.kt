package org.v_points.data.school

import org.v_points.data.school.SchoolApi.TUTOR_EXTERNAL_SCHOOL_QUERY_GET_ALL_CACHED
import org.v_points.data.school.model.GetSchoolResponse
import retrofit2.http.GET

interface SchoolService {
    @GET(TUTOR_EXTERNAL_SCHOOL_QUERY_GET_ALL_CACHED)
    suspend fun getSchool(): GetSchoolResponse
}
