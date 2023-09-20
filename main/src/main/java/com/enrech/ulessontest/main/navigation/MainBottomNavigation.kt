package com.enrech.ulessontest.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.enrech.ulessontest.classes.navigation.ClassesDestinations
import com.enrech.ulessontest.common_resources.theme.ULessonTheme
import com.enrech.ulessontest.common_resources.theme.grey50
import com.enrech.ulessontest.common_resources.theme.toolbarIconGrey
import com.enrech.ulessontest.common_resources.theme.typePrimary
import com.enrech.ulessontest.downloads.navigation.DownloadDestinations
import com.enrech.ulessontest.home.navigation.HomeDestinations
import com.enrech.ulessontest.more.navigation.MoreDestinations
import com.enrech.ulessontest.subscribe.navigation.SubscribeDestinations

@Composable
fun MainBottomNavigation(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    UIContent(
        selected = { screenRoute ->
            currentDestination?.hierarchy?.any { it.route == screenRoute } ?: (screenRoute == HomeDestinations.Root.routeDestination)
        }, onClick = { screenRoute ->
            navController.navigate(screenRoute) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        })
}

@Composable
private fun UIContent(selected: (route: String) -> Boolean, onClick: (route: String) -> Unit) {
    val items = listOf(
        MainTab.Home,
        MainTab.Classes,
        MainTab.Subscribe,
        MainTab.Downloads,
        MainTab.More
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEach { screen ->
            val isSelected = selected(screen.route)
            val labelColor = if (isSelected) typePrimary else grey50

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = grey50,
                    unselectedTextColor = grey50,
                    selectedIconColor = typePrimary,
                    selectedTextColor = typePrimary,
                    indicatorColor = toolbarIconGrey
                ),
                icon = { TabIcon(screen = screen, selected = selected(screen.route)) },
                label = {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = stringResource(id = screen.textId),
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center,
                        color = labelColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                selected = selected(screen.route),
                onClick = { onClick(screen.route) }
            )
        }
    }
}

@Composable
private fun TabIcon(screen: MainTab, selected: Boolean) {
    return Icon(
        painter =
        if (selected) painterResource(id = screen.selectedIconId)
        else painterResource(id = screen.unSelectedIconId ?: screen.selectedIconId),
        contentDescription = null,
        modifier = Modifier.width(24.dp)
    )
}

@Preview(showBackground = true, name = "Home Selected")
@Composable
private fun HomeSelected() {
    ULessonTheme {
        UIContent(selected = { it == MainTab.Home.route }, onClick = {} )
    }
}

@Preview(showBackground = true, name = "Video Selected")
@Composable
private fun ClassesSelected() {
    ULessonTheme {
        UIContent(selected = { it == MainTab.Classes.route }, onClick = {} )
    }
}

@Preview(showBackground = true, name = "None Selected")
@Composable
private fun NoneSelected() {
    ULessonTheme {
        UIContent(selected = { false }, onClick = {} )
    }
}

sealed class MainTab(
    val route: String,
    @StringRes val textId: Int,
    @DrawableRes val selectedIconId: Int,
    @DrawableRes val unSelectedIconId: Int? = null
) {

    data object Home: MainTab(
        HomeDestinations.Root.routeDestination,
        com.enrech.ulessontest.home.R.string.home_tab,
        com.enrech.ulessontest.common_resources.R.drawable.ic_home
    )

    data object Classes: MainTab(
        ClassesDestinations.Root.routeDestination,
        com.enrech.ulessontest.classes.R.string.classes_tab,
        com.enrech.ulessontest.common_resources.R.drawable.ic_classes
    )

    data object Subscribe : MainTab(
        SubscribeDestinations.Root.routeDestination,
        com.enrech.ulessontest.subscribe.R.string.subscribe_tab,
        com.enrech.ulessontest.common_resources.R.drawable.ic_buy
    )

    data object Downloads : MainTab(
        DownloadDestinations.Root.routeDestination,
        com.enrech.ulessontest.downloads.R.string.downloads_tab,
        com.enrech.ulessontest.common_resources.R.drawable.ic_download
    )

    data object More : MainTab(
        MoreDestinations.Root.routeDestination,
        com.enrech.ulessontest.more.R.string.more_tab,
        com.enrech.ulessontest.common_resources.R.drawable.ic_more
    )
}