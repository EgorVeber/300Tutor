package com.v_points.app.data.school

import com.v_points.app.data.school.SchoolApi.TUTOR_EXTERNAL_SCHOOL_QUERY_GET_ALL_CACHED
import com.v_points.app.data.school.model.GetSchoolResponse
import retrofit2.http.GET

interface SchoolService {
    @GET(TUTOR_EXTERNAL_SCHOOL_QUERY_GET_ALL_CACHED)
    suspend fun getSchool(): GetSchoolResponse
}
