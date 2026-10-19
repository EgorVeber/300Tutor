package com.v_points.app.data.subject_detailed

import com.v_points.app.data.subject_detailed.SubjectDetailedApi.ID_OR_ALIAS
import com.v_points.app.data.subject_detailed.SubjectDetailedApi.TUTOR_SUBJECT_MENU_GET_BY_ALIAS_OR_ID_CACHED
import com.v_points.app.data.subject_detailed.models.ConfigurationMenuResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SubjectDetailedService {
    @GET(TUTOR_SUBJECT_MENU_GET_BY_ALIAS_OR_ID_CACHED)
    suspend fun getMenuSubjectConfig(@Query(ID_OR_ALIAS) idOrAlias: String): ConfigurationMenuResponse
}
