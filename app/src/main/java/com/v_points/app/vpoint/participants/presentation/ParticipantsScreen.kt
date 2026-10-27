package com.v_points.app.vpoint.participants.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.ui_core.components.users_variants.UserCardWithStatsRight
import com.v_points.app.ui_core.components.users_variants.UserStat
import com.v_points.app.ui_core.components.users_variants.UserUiModel

@Composable
fun ParticipantsScreen(
    viewModel: ParticipantsViewModel,
) {
    val mainState: MutableParticipantsState = viewModel.getMainState()

    Column {
        ParticipantsScreenContent(
            mainState = mainState,
            //  onAction = viewModel::onAction
        )
    }
}

@Composable
fun ParticipantsScreenContent(
    modifier: Modifier = Modifier,
    mainState: ParticipantsState,
    // onAction: (MainScreenAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        mainState.users.forEach { user ->
            UserCardWithStatsRight(user)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParticipantsScreenContentPreview() {
    MaterialTheme {
        ParticipantsScreenContent(
            mainState = MutableParticipantsState().apply {
                users = sampleUsersWithStats()
            },
        )
    }
}

private fun sampleUsersWithStats(): List<UserUiModel> = listOf(
    UserUiModel(
        id = "1",
        firstName = "Анна",
        lastName = "Смирнова",
        description = "Преподаватель математики",
        role = "Учитель",
        stats = listOf(
            UserStat("Курсы", "12"),
            UserStat("Ученики", "146")
        )
    ),
    UserUiModel(
        id = "2",
        firstName = "Иван",
        lastName = "Петров",
        description = "Студент, 3 курс",
        role = "Студент",
        stats = listOf(
            UserStat("Курсы", "5"),
            UserStat("Баллы", "820")
        )
    )
)