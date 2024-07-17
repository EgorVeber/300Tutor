package org.threehundredtutor.domain.subject_detailed.models

enum class SubjectMenuItemType {
    MENU_ITEM,
    WORK_SPACE_LINK,
    SUBJECT_TEST_LINK,
    UNKNOWN;

    companion object {
        private const val menuItem = "MenuItem"
        private const val workSpaceLink = "WorkSpaceLink"
        private const val subjectTestsLink = "SubjectTestsLink"
        fun fromMenuItem(type: String) =
            when (type) {
                menuItem -> MENU_ITEM
                workSpaceLink -> WORK_SPACE_LINK
                subjectTestsLink -> SUBJECT_TEST_LINK
                else -> UNKNOWN
            }
    }
}