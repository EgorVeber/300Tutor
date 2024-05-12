package org.threehundredtutor.presentation.main_menu

import org.threehundredtutor.core.UiCoreDrawable
import org.threehundredtutor.ui_common.UiCoreStrings

enum class MainMenuItem {
    SCHEDULE,
    NOTES,
    GROUPS,
    FAVORITES_QUESTION,
    COMPLETED_TESTS,
    NOT_COMPLETED_TESTS,
    NOTIFICATION;

    companion object {
        fun MainMenuItem.getIcon(): Int {
            return when (this) {
                SCHEDULE -> UiCoreDrawable.ic_schedule
                NOTES -> UiCoreDrawable.ic_notes
                GROUPS -> UiCoreDrawable.ic_groups
                FAVORITES_QUESTION -> UiCoreDrawable.ic_favorites_question
                COMPLETED_TESTS -> UiCoreDrawable.ic_completed_question
                NOT_COMPLETED_TESTS -> UiCoreDrawable.ic_not_completed_question
                NOTIFICATION -> UiCoreDrawable.ic_notification
            }
        }

        fun MainMenuItem.getTitle(): Int {
            return when (this) {
                SCHEDULE -> UiCoreStrings.main_menu_schedule
                NOTES -> UiCoreStrings.main_menu_notes
                GROUPS -> UiCoreStrings.main_menu_groups
                FAVORITES_QUESTION -> UiCoreStrings.main_menu_favorites_question
                COMPLETED_TESTS -> UiCoreStrings.main_menu_completed_tests
                NOT_COMPLETED_TESTS -> UiCoreStrings.main_menu_not_completed_tests
                NOTIFICATION -> UiCoreStrings.main_menu_notification
            }
        }
    }
}