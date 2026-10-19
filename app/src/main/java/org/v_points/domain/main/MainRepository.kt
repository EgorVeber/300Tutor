package org.v_points.domain.main

import org.v_points.domain.main.models.CreateLinkTelegramModel
import org.v_points.domain.main.models.ExtraButtonInfoModel
import org.v_points.domain.main.models.GroupWithCourseModel
import org.v_points.domain.main.models.TelegramDataModel
import org.v_points.domain.subject_tests.models.SubjectModel

interface MainRepository {
    suspend fun getTelegramData(): TelegramDataModel
    suspend fun getSubjects(): List<SubjectModel>
    suspend fun getCourses(studentId: String): GroupWithCourseModel
    suspend fun enterGroup(key: String): EnterGroupModel
    suspend fun getExtraButtons(): List<ExtraButtonInfoModel>
    suspend fun createLinkTelegram(): CreateLinkTelegramModel
}
