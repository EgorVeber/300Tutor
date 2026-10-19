package com.v_points.app.data.solution.models.directory


import com.google.gson.annotations.SerializedName

class TestQuestionTypeRequest(
    @SerializedName("types")
    val types: List<String>
)