package com.example.jcex.kotlintraining

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcex.kotlintraining.composables.TabPage
import com.example.jcex.layouts.padding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun KotlinTrainingScreen() {
    val pagerState = rememberPagerState(0)
    val screenState = remember { mutableStateOf(TabPage.Classes) }

    Column(Modifier.background(MaterialTheme.colorScheme.tertiary)) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            count = TabPage.values().size,
            state = pagerState
        ) { page ->
            when (page) {
                0 -> screenState.value = TabPage.Collections
                1 -> screenState.value = TabPage.Classes
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TitleWithIcon(
                    title = screenState.value.name,
                    vector = screenState.value.icon,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Box(
                    Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    /**
                     * invoke!!
                     */
                    screenState.value.screen.invoke()
                }
            }
        }

        DotsIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            totalDots = TabPage.values().size,
            selectedIndex = pagerState.currentPage,
            selectedColor = MaterialTheme.colorScheme.onSecondary,
            unselectedColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unselectedColor: Color,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Dot(modifier = Modifier.padding(8.dp), selectedColor)
            } else {
                Dot(modifier = Modifier.padding(8.dp), unselectedColor)
            }
        }
    }
}

@Composable
fun Dot(modifier: Modifier = Modifier, backgroundColor: Color = Color.Blue) {
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    )
}

@Composable
fun TitleWithIcon(title: String, vector: ImageVector, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ScreenTitle(
            text = title,
            color = color
        )
        Spacer(modifier = Modifier.size(16.dp))
        Icon(imageVector = vector, contentDescription = null, tint = color)
    }
}

@Preview
@Composable
fun KotlinTrainingScreenPrev() {
    KotlinTrainingScreen()
}