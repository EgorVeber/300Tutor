package org.threehundredtutor.domain.main

import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.domain.main.models.GroupWithCourseModel
import org.threehundredtutor.domain.main.models.SearchTestModel
import org.threehundredtutor.domain.main.models.SubjectModel

interface MainRepository {
    suspend fun getSubjects(): List<SubjectModel>
    suspend fun searchTests(subjectId: String): SearchTestModel
    suspend fun getCourses(): GroupWithCourseModel
    suspend fun enterGroup(key: String): EnterGroupModel
    suspend fun getExtraButtons(): List<ExtraButtonInfoModel>
}
