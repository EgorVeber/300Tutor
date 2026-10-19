package org.v_points.presentation.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

data class ScalePoint(
    val value: Int,
    val name: String
)

private const val STEP = 5
private const val LONG_PRESS_MS = 500L
private const val MAX_PER_VALUE = 4

private fun snapToStep(raw: Float): Int {
    val snapped = (raw / STEP).roundToInt() * STEP
    return snapped.coerceIn(0, 100)
}

@Composable
fun NamedTappableScale(
    names: List<String>,
    modifier: Modifier = Modifier,
    pointRadius: Dp = 6.dp,
    trackHeight: Dp = 5.dp,
    sidePadding: Dp = 16.dp,
    labelBaseOffset: Dp = 26.dp,
    labelRowGap: Dp = 14.dp,
    labelFontSize: Int = 11,
    topReserved: Dp = 105.dp,
    bottomReserved: Dp = 55.dp,
    trackAreaHeight: Dp = 70.dp,
    onResultChange: (List<ScalePoint>) -> Unit = {}
) {
    val textMeasurer = rememberTextMeasurer()
    val haptic = LocalHapticFeedback.current

    var points by remember { mutableStateOf<List<ScalePoint>>(emptyList()) }
    var highlightValue by remember { mutableStateOf<Int?>(null) }

    val checkLabels = remember { listOf("Подача", "Прием", "Атака", "Пас", "Защита") }
    var selectedIndex by remember { mutableStateOf(0) }

    // при смене radio — очищаем всё
    val onSelectRadio: (Int) -> Unit = { newIndex ->
        if (newIndex != selectedIndex) {
            selectedIndex = newIndex
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            points = emptyList()
            highlightValue = null
        }
    }

    val nextTargetName = names.firstOrNull { name ->
        points.none { it.name == name }
    }

    LaunchedEffect(points) { onResultChange(points) }

    val totalHeight = topReserved + trackAreaHeight + bottomReserved

    Column(modifier = modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(totalHeight)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(names, points) {
                        awaitEachGesture {
                            val down = awaitFirstDown(requireUnconsumed = false)

                            val canvasWidthPx = size.width.toFloat()
                            val paddingPx = sidePadding.toPx()
                            val trackWidthPx = canvasWidthPx - paddingPx * 2

                            val downValue = snapToStep(
                                ((down.position.x - paddingPx) / trackWidthPx * 100f)
                                    .coerceIn(0f, 100f)
                            )

                            val longPressHappened = withTimeoutOrNull(LONG_PRESS_MS) {
                                var pointer = down
                                while (true) {
                                    val event = awaitPointerEvent()
                                    val change = event.changes.firstOrNull { it.id == pointer.id }
                                        ?: return@withTimeoutOrNull false
                                    if (!change.pressed) return@withTimeoutOrNull false
                                    pointer = change
                                }
                                @Suppress("UNREACHABLE_CODE")
                                false
                            } == null

                            if (longPressHappened) {
                                val hasPointsHere = points.any { it.value == downValue }
                                if (hasPointsHere) {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    highlightValue = downValue
                                    points = points.filterNot { it.value == downValue }

                                    var pointer = down
                                    while (true) {
                                        val event = awaitPointerEvent()
                                        val change = event.changes.firstOrNull { it.id == pointer.id }
                                            ?: break
                                        if (!change.pressed) break
                                        pointer = change
                                    }
                                    highlightValue = null
                                }
                                return@awaitEachGesture
                            }

                            val onThisValue = points.count { it.value == downValue }
                            if (onThisValue >= MAX_PER_VALUE) return@awaitEachGesture
                            if (points.size >= names.size) return@awaitEachGesture

                            val usedNames = points.map { it.name }.toSet()
                            val nextName = names.firstOrNull { it !in usedNames }
                                ?: return@awaitEachGesture

                            points = (points + ScalePoint(downValue, nextName))
                                .sortedBy { it.value }
                        }
                    }
            ) {
                val paddingPx = sidePadding.toPx()
                val trackWidth = size.width - paddingPx * 2

                val topReservedPx = topReserved.toPx()
                val trackAreaPx = trackAreaHeight.toPx()
                val trackY = topReservedPx + trackAreaPx * 0.30f

                val trackHeightPx = trackHeight.toPx()
                val pointRadiusPx = pointRadius.toPx()

                val labelBaseOffsetPx = labelBaseOffset.toPx()
                val labelRowGapPx = labelRowGap.toPx()

                drawRoundRect(
                    color = Color(0xFFE0E0E0),
                    topLeft = Offset(paddingPx, trackY - trackHeightPx / 2),
                    size = Size(trackWidth, trackHeightPx),
                    cornerRadius = CornerRadius(trackHeightPx / 2)
                )

                val tickBase = trackY + trackHeightPx / 2 + 4f

                for (i in 0..100 step STEP) {
                    val x = paddingPx + trackWidth * (i / 100f)
                    val isMultiple50 = i % 50 == 0
                    val isMultiple10 = i % 10 == 0

                    val tickLength = when {
                        isMultiple50 -> 22f
                        isMultiple10 -> 15f
                        else -> 10f
                    }
                    val tickColor = when {
                        isMultiple50 -> Color(0xFF212121)
                        isMultiple10 -> Color(0xFF616161)
                        else -> Color(0xFF9E9E9E)
                    }
                    val tickWidth = when {
                        isMultiple50 -> 2.5f
                        isMultiple10 -> 1.6f
                        else -> 1.2f
                    }

                    drawLine(
                        color = tickColor,
                        start = Offset(x, tickBase),
                        end = Offset(x, tickBase + tickLength),
                        strokeWidth = tickWidth
                    )

                    if (isMultiple10) {
                        val labelLayout = textMeasurer.measure(
                            text = i.toString(),
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = if (isMultiple50) Color(0xFF212121) else Color(0xFF616161),
                                fontWeight = if (isMultiple50) FontWeight.Bold else FontWeight.Normal
                            )
                        )
                        drawText(
                            textLayoutResult = labelLayout,
                            topLeft = Offset(
                                x - labelLayout.size.width / 2f,
                                tickBase + 24f
                            )
                        )
                    }
                }

                val groups: Map<Int, List<ScalePoint>> = points.groupBy { it.value }
                val groupValues: List<Int> = groups.keys.sorted()

                data class LabelItem(
                    val point: ScalePoint,
                    val pointX: Float,
                    val labelX: Float,
                    val above: Boolean,
                    val layout: TextLayoutResult,
                    var row: Int = 0
                )

                val items = mutableListOf<LabelItem>()
                groupValues.forEach { value ->
                    val group = groups[value]!!
                    val pointX = paddingPx + trackWidth * (value / 100f)

                    group.forEachIndexed { idxInGroup, point ->
                        val above = idxInGroup % 2 == 0

                        val layout = textMeasurer.measure(
                            text = "${point.name} $value",
                            style = TextStyle(
                                fontSize = labelFontSize.sp,
                                color = if (value == highlightValue) Color(0xFFD32F2F)
                                else Color(0xFF0D47A1),
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                lineHeight = (labelFontSize + 3).sp
                            )
                        )

                        val halfWidth = layout.size.width / 2f
                        val labelX = pointX.coerceIn(
                            halfWidth + 2f,
                            size.width - halfWidth - 2f
                        )

                        items.add(LabelItem(point, pointX, labelX, above, layout))
                    }
                }

                val sortedForPacking = items.withIndex().sortedBy { it.value.labelX }
                val occupied = mutableMapOf<Pair<Boolean, Int>, MutableList<Pair<Float, Float>>>()

                sortedForPacking.forEach { (originalIndex, item) ->
                    val half = item.layout.size.width / 2f + 4f
                    val left = item.labelX - half
                    val right = item.labelX + half

                    var row = 0
                    while (true) {
                        val key = item.above to row
                        val intervals = occupied.getOrPut(key) { mutableListOf() }
                        val overlaps = intervals.any { (l, r) -> left < r && right > l }
                        if (!overlaps) {
                            intervals.add(left to right)
                            items[originalIndex].row = row
                            break
                        }
                        row++
                    }
                }

                items.forEach { item ->
                    val finalRow = item.row
                    val labelX = item.labelX
                    val pointX = item.pointX
                    val isHighlighted = item.point.value == highlightValue
                    val labelLayout = item.layout

                    val labelTopY: Float
                    val labelBottomY: Float

                    if (item.above) {
                        val y = trackY - labelBaseOffsetPx - finalRow * labelRowGapPx
                        labelBottomY = y
                        labelTopY = y - labelLayout.size.height
                    } else {
                        val y = trackY + labelBaseOffsetPx + finalRow * labelRowGapPx + 30f
                        labelTopY = y
                        labelBottomY = y + labelLayout.size.height
                    }

                    val lineStartY = if (item.above) labelBottomY else labelTopY
                    val lineEndX = pointX
                    val lineEndY = if (item.above) trackY - pointRadiusPx
                    else trackY + pointRadiusPx

                    drawLine(
                        color = if (isHighlighted) Color(0xFFD32F2F) else Color(0xFF90CAF9),
                        start = Offset(labelX, lineStartY),
                        end = Offset(lineEndX, lineEndY),
                        strokeWidth = if (isHighlighted) 1.5f else 1f
                    )

                    drawText(
                        textLayoutResult = labelLayout,
                        topLeft = Offset(
                            labelX - labelLayout.size.width / 2f,
                            labelTopY
                        )
                    )
                }

                groupValues.forEach { value ->
                    val group = groups[value]!!
                    val x = paddingPx + trackWidth * (value / 100f)
                    val isHighlighted = value == highlightValue
                    val color = if (isHighlighted) Color(0xFFD32F2F) else Color(0xFF2196F3)

                    if (isHighlighted) {
                        drawCircle(Color(0x33D32F2F), pointRadiusPx * 3.5f, Offset(x, trackY))
                    }

                    val stackStep = pointRadiusPx * 1.8f
                    group.forEachIndexed { idx, _ ->
                        val cy = when (idx) {
                            0 -> trackY
                            1 -> trackY - stackStep
                            else -> trackY + (idx - 1) * stackStep
                        }

                        drawCircle(
                            color = color,
                            radius = pointRadiusPx,
                            center = Offset(x, cy)
                        )
                        drawCircle(
                            color = Color.White,
                            radius = pointRadiusPx / 2.5f,
                            center = Offset(x, cy)
                        )
                    }
                }
            }

            Text(
                text = checkLabels[selectedIndex],
                color = Color(0xFF0D47A1),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            if (nextTargetName != null) {
                Text(
                    text = "Выберите для: $nextTargetName",
                    color = Color(0xFF0D47A1),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 8.dp)
                    .size(28.dp)
                    .clickable(enabled = points.isNotEmpty()) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        points = emptyList()
                        highlightValue = null
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Очистить всё",
                    tint = if (points.isNotEmpty()) Color(0xFFD32F2F)
                    else Color(0xFFBDBDBD),
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = sidePadding),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Column(Modifier.padding(12.dp)) {
                val placedPoints = points.sortedByDescending { it.value }
                val placedNames = points.map { it.name }.toSet()

                // 👇 проставленные — ОБЫЧНЫЙ ТАП удаляет точку
                placedPoints.forEachIndexed { index, point ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    points = points.filterNot { it.name == point.name }
                                },
                                onLongClick = { }
                            )
                            .padding(vertical = 5.dp)
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${index + 1}.",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2196F3),
                                modifier = Modifier.width(24.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF2196F3))
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = point.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                        Text(
                            text = "${point.value}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2196F3)
                        )
                    }
                }

                val notPlacedNames = names.filterNot { it in placedNames }
                if (notPlacedNames.isNotEmpty() && placedPoints.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE0E0E0))
                    )
                    Spacer(Modifier.height(8.dp))
                }
                notPlacedNames.forEachIndexed { index, name ->
                    val number = placedPoints.size + index + 1
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "$number.",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF9E9E9E),
                                modifier = Modifier.width(24.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFBDBDBD))
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )
                        }
                        Text(
                            text = "—",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        // RadioButton-группа
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = sidePadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (i in 0..2) {
                    CompactRadio(
                        label = checkLabels[i],
                        selected = selectedIndex == i,
                        onClick = { onSelectRadio(i) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (i in 3..4) {
                    CompactRadio(
                        label = checkLabels[i],
                        selected = selectedIndex == i,
                        onClick = { onSelectRadio(i) },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun CompactRadio(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            modifier = Modifier.size(22.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF2196F3),
                unselectedColor = Color(0xFFBDBDBD)
            )
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = label,
            fontSize = 13.sp,
            color = if (selected) Color.Black else Color.Gray,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NamedScalePreview() {
    val names = listOf(
        "Иван", "Пётр", "Анна", "Олег",
        "Мария", "Сергей", "Ольга", "Дмитрий",
        "Елена", "Николай", "Татьяна", "Максим"
    )
    MaterialTheme {
        Surface {
            NamedTappableScale(
                names = names,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }
    }
}