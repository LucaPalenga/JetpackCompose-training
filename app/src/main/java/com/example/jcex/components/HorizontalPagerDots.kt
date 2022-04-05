package com.example.jcex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcex.layouts.padding
import com.example.jcex.ui.theme.colorTotal
import com.example.jcex.ui.theme.colorVal1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

/***
 * PAllUA - 05/04/22
 */

@Preview
@Composable
fun HorizontalPagerDotsScreen() {
    HorizontalPagerDots(
        listOf(
            PageObj("pagina1") { Text(text = "contenuto della PRIMA pagina") },
            PageObj("pagina2") { Text(text = "contenuto della SECONDA pagina") }
        )
    )
}

data class PageObj(val name: String, val composable: @Composable () -> Unit)


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerDots(pages: List<PageObj>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(0)
    val screenState = remember { mutableStateOf(pages.first()) }

    val currentIndex = pagerState.currentPage
    val currentPageOffset = pagerState.currentPageOffset
    val maxOffset = 0.3f
    val maxOffsetDp = 30.dp

//    val expanded = rememberSaveable { mutableStateOf(false) }
//    val animatedSizeDp: Dp by animateDpAsState(targetValue = if (expanded.value) 300.dp else 100.dp)
    Column(horizontalAlignment = CenterHorizontally) {
        HorizontalPager(
            modifier = modifier.weight(1f),
            count = pages.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
//        verticalAlignment = Alignment.Top
        ) { page ->
            screenState.value = pages[page]

            //TODO pallua
            val percHeight = when (page) {
                currentIndex -> {
//                    currentPageOffset.absoluteValue
                    1f
                }
                currentIndex - 1 -> {
//                    1 + currentPageOffset.coerceAtMost(0f)
//                    (1 - currentPageOffset) * maxOffset
                    1 * maxOffset
                }
                currentIndex + 1 -> {
//                    1 - currentPageOffset.coerceAtLeast(0f)
//                    (1 + currentPageOffset) * maxOffset
                    1 * maxOffset
                }
                else -> {
                    1f
                }
            }
//            val offset = maxOffsetDp * when (page) {
//                currentIndex -> {
////                    currentPageOffset.absoluteValue
//                    0f
//                }
//                currentIndex - 1 -> {
//                    1 + currentPageOffset.coerceAtMost(0f)
//                }
//                currentIndex + 1 -> {
//                    1 - currentPageOffset.coerceAtLeast(0f)
//                }
//                else -> {
//                    1f
//                }
//            }

//                ScreenTitle(text = screenState.value.name, color = Color.Red)
            Box(
                Modifier
                    .fillMaxHeight(percHeight)
//                    .fillMaxWidth(0.7f)
//                    .fillMaxHeight(0.7f)
//                    .offset(y = -offset),
            ) {
                screenState.value.composable.invoke()
            }

        }
        DotsIndicator(
            totalDots = pages.size,
            selectedIndex = pagerState.currentPage,
            selectedColor = colorVal1,
            unselectedColor = colorTotal
        )
    }
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unselectedColor: Color,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(totalDots) { index ->
            Dot(
                backgroundColor = if (index == selectedIndex) {
                    selectedColor
                } else {
                    unselectedColor
                }
            )
        }
    }
}

@Composable
fun Dot(modifier: Modifier = Modifier, backgroundColor: Color = Color.Blue) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(6.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    )
}
