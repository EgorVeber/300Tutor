package org.threehundredtutor.data.subject_detailed

import org.threehundredtutor.domain.subject_detailed.SubjectDetailedRepository
import org.threehundredtutor.domain.subject_detailed.models.MenuSubjectConfigModel
import org.threehundredtutor.domain.subject_detailed.models.SubjectMenuItemType
import org.threehundredtutor.ui_common.util.BadRequestException
import javax.inject.Inject

class SubjectDetailedRepositoryImpl @Inject constructor(
    private val subjectDetailedRemoteDataSource: SubjectDetailedRemoteDataSource,
) : SubjectDetailedRepository {

    override suspend fun getMenuSubjectConfig(idOrAlias: String): MenuSubjectConfigModel =
        subjectDetailedRemoteDataSource.getMenuSubjectConfig(idOrAlias).rootResponse?.toRootModel()
            ?: throw BadRequestException()

    // TODO TutorAndroid-63
    fun getMockk(): MenuSubjectConfigModel = MenuSubjectConfigModel(
        text = "", type = SubjectMenuItemType.MENU_ITEM, workSpaceId = "", path = "",
        subjectMenuItems = listOf(
            MenuSubjectConfigModel(
                text = "Теория к заданиям",
                path = "Теория к заданиям",
                type = SubjectMenuItemType.WORK_SPACE_LINK,
                workSpaceId = "8aed921b-fa30-4004-b2e1-338de335ce15",
                subjectMenuItems = listOf()
            ),
            MenuSubjectConfigModel(
                text = "Статьи-конспекты",
                path = "Статьи-конспекты",
                type = SubjectMenuItemType.MENU_ITEM,
                workSpaceId = "e4a9b108-ce69-4fac-8ff2-f2cd7f770f49",
                subjectMenuItems = listOf(
                    MenuSubjectConfigModel(
                        text = "Теория к заданиям2",
                        path = "Теория к заданиям2",
                        type = SubjectMenuItemType.WORK_SPACE_LINK,
                        workSpaceId = "8sssaed921b-fa30-4004-b2e1-338de335ce15",
                        subjectMenuItems = listOf()
                    ),
                    MenuSubjectConfigModel(
                        text = "Статьи-конспекты2",
                        path = "Статьи-конспекты2",
                        type = SubjectMenuItemType.WORK_SPACE_LINK,
                        workSpaceId = "e4a9b108-ce69-4fac-8ff2sss-f2cd7f770f49",
                        subjectMenuItems = listOf()
                    ),
                    MenuSubjectConfigModel(
                        text = "Варианты ЕГЭ2",
                        path = "Варианты ЕГЭ2",
                        type = SubjectMenuItemType.SUBJECT_TEST_LINK,
                        workSpaceId = "",
                        subjectMenuItems = listOf()
                    ),
                )
            ),
            MenuSubjectConfigModel(
                text = "Варианты ЕГЭ",
                path = "Варианты ЕГЭ",
                type = SubjectMenuItemType.SUBJECT_TEST_LINK,
                workSpaceId = "",
                subjectMenuItems = listOf()
            ),
        )
    )
}
