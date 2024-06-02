package org.threehundredtutor.domain.main

import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.domain.main.models.GroupWithCourseModel
import org.threehundredtutor.domain.subject_tests.models.SubjectModel

interface MainRepository {
    suspend fun getSubjects(): List<SubjectModel>
    suspend fun getCourses(studentId: String): GroupWithCourseModel
    suspend fun enterGroup(key: String): EnterGroupModel
    suspend fun getExtraButtons(): List<ExtraButtonInfoModel>
}
