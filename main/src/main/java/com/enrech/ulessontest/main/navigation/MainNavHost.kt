package com.enrech.ulessontest.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.enrech.ulessontest.classes.navigation.classesGraphGraph
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.downloads.navigation.downloadGraphGraph
import com.enrech.ulessontest.home.navigation.HomeDestinations
import com.enrech.ulessontest.home.navigation.homeGraphGraph
import com.enrech.ulessontest.more.navigation.moreGraphGraph
import com.enrech.ulessontest.subscribe.navigation.subscribeGraphGraph

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val mainNavController = LocalNavController.current

    NavHost(
        navController = mainNavController,
        startDestination = HomeDestinations.Root.routeDestination,
        modifier = modifier
    ) {
        homeGraphGraph()
        classesGraphGraph()
        subscribeGraphGraph()
        downloadGraphGraph()
        moreGraphGraph()
    }
}