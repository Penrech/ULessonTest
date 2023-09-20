package com.enrech.ulessontest.common.components.tabbar

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle

@Composable
fun <T : TabBarData> TabBarHorizontalLayout(
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    data: List<T>,
    screenToLoad: @Composable (tab: T, index: Int, isSelected: Boolean) -> Unit,
    pagerState: PagerState
) {

    var actualCurrentTab: T? by remember { mutableStateOf(data.firstOrNull()) }

    //Page change callback
    LaunchedEffect(pagerState.currentPage, pagerState.currentPageOffsetFraction) {
        if (pagerState.currentPageOffsetFraction == 0.0f) {
            actualCurrentTab = data.getOrNull(pagerState.currentPage)
        }
    }

    HorizontalPager(
        modifier = modifier,
        userScrollEnabled = userScrollEnabled,
        state = pagerState
    ) { index ->
        if (LocalLifecycleOwner.current.lifecycle.currentState != Lifecycle.State.DESTROYED) {
            data.getOrNull(index)?.let { screenToLoad(it, index, it == actualCurrentTab) }
        }
    }
}