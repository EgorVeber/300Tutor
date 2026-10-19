package org.v_points.data.solution.models.directory


import com.google.gson.annotations.SerializedName

class TestQuestionTypeRequest(
    @SerializedName("types")
    val types: List<String>
)