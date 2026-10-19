package org.v_points.presentation.main// ui/feature/MyFeatureScreen.kt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import org.v_points.presentation.main.ui_models.MainUiItem
import org.v_points.ui_core.theme.extendedColors

@Composable
fun MyFeatureScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.getUiItemStateFlow().collectAsStateWithLifecycle()

    Column(
        Modifier
            .safeDrawingPadding()
            .padding(top = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        NamedTappableScale(
            names = listOf(
                "Вадим", "Вячеслав", "Дарья", "Дима",
                "Евгений", "Егор", "Иван", "Максим",
                "Олеся", "Саша", "Света", "Юля"
            ),
            onResultChange = {}
        )
    }
}

@Composable
fun MyFeatureContent(
    modifier: Modifier = Modifier,
    uiState: List<MainUiItem>
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.extendedColors.contentBackground
                    )
                ) {
                    Text(
                        text = item.toString(),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}