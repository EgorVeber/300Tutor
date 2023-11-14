package org.threehundredtutor.domain.subject

import org.threehundredtutor.domain.subject.models.SearchTestModel
import org.threehundredtutor.domain.subject.models.SubjectModel

interface SubjectRepository {
    suspend fun getSubjects(): List<SubjectModel>
    suspend fun searchTests(subjectId:String): SearchTestModel
}
