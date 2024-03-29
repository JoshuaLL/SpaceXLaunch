package com.joshua.spacexlaunch.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.joshua.spacexlaunch.*
import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.ui.launches.GetLaunches
import com.google.accompanist.insets.navigationBarsHeight
import com.joshua.spacexlaunch.ui.about.GetAboutPage
import timber.log.Timber

@ExperimentalFoundationApi
@Preview
@Composable
fun Navigation() {
    val baseTitle = stringResource(id = R.string.app_name)
    val (title, setTitle) = remember { mutableStateOf(baseTitle) }
    val (canPop, setCanPop) = remember { mutableStateOf(false) }

    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }

    // Used for check if is detail screen.
    val isCurrentLaunchesDetail = remember { mutableStateOf(false) }

    val toolBarIcon = remember { mutableStateOf(R.drawable.ic_awesome) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color.White
                    )
                },
                // Only show back icon when detail screen.
                navigationIcon = if (canPop && isCurrentLaunchesDetail.value) {
                    {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(Icons.Outlined.ArrowBack, "back", tint = Color.White)
                        }
                    }
                } else {
                    {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painterResource(R.drawable.ic_rocket_logo),
                                "main",
                                tint = Color.White
                            )
                        }
                    }
                },
            )
        },

        bottomBar = {
            // Not show bottom navigation if go to movie detail screen.
            if (!isCurrentLaunchesDetail.value) {
                BottomNavigation(
                    modifier = Modifier.navigationBarsHeight(additional = 56.dp)
                ) {

                    val currentRoute = navController.currentBackStackEntry?.destination?.route

                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(screen.drawableId), screen.route) },
                            label = { Text(stringResource(screen.stringId)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo = navController.graph.startDestinationId
                                    launchSingleTop = true

                                    toolBarIcon.value = screen.drawableId

                                    Timber.i("BottomNavigation Item click toolBarIcon:${toolBarIcon.value}")

                                }
                            },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }
            }
        },
    ){ innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        NavHost(navController, startDestination = Screen.Launches.route) {
            composable(Screen.Launches.route) {
                GetLaunches(navController, setTitle, modifier)
                isCurrentLaunchesDetail.value = false
            }
            composable(
                route = ROUTE_LAUNCHES_DETAIL,
                arguments = listOf(navArgument(ROUTE_LAUNCHES_KEY) {
                    type = NavType.StringType
                })
            ) {

                isCurrentLaunchesDetail.value = true
            }
            composable(Screen.About.route) {
                GetAboutPage(navController, setTitle, modifier)
                isCurrentLaunchesDetail.value = false
            }
        }
    }
}

val items = listOf(
    Screen.Launches,
    Screen.About,
)

sealed class Screen(val route: String, @DrawableRes val drawableId: Int, @StringRes val stringId: Int)  {
//    object Splash :Screen(ROUTE_SPLASH,Icons.Default.None, R.string.screen_splash)
    object Launches : Screen(ROUTE_LAUNCHES,  R.drawable.ic_awesome, R.string.screen_launches)
    object About: Screen(ROUTE_ABOUT, R.drawable.ic_about, R.string.screen_about)

}