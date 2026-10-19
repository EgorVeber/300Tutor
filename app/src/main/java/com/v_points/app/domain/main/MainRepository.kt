package com.v_points.app.domain.main

import com.v_points.app.domain.main.models.CreateLinkTelegramModel
import com.v_points.app.domain.main.models.ExtraButtonInfoModel
import com.v_points.app.domain.main.models.GroupWithCourseModel
import com.v_points.app.domain.main.models.TelegramDataModel
import com.v_points.app.domain.subject_tests.models.SubjectModel

interface MainRepository {
    suspend fun getTelegramData(): TelegramDataModel
    suspend fun getSubjects(): List<SubjectModel>
    suspend fun getCourses(studentId: String): GroupWithCourseModel
    suspend fun enterGroup(key: String): EnterGroupModel
    suspend fun getExtraButtons(): List<ExtraButtonInfoModel>
    suspend fun createLinkTelegram(): CreateLinkTelegramModel
}
