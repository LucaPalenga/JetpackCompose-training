package com.example.jcex

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jcex.animations.HomeAnimations
import com.example.jcex.components.ComposableScreen
import com.example.jcex.drawermenu.DrawerBody
import com.example.jcex.drawermenu.DrawerHeader
import com.example.jcex.kotlintraining.KotlinTrainingScreen
import com.example.jcex.layouts.StraggeredGridLab
import com.example.jcex.state.TodoActivityScreen
import com.example.jcex.state.TodoVewModel
import com.example.jcex.theming.Home
import com.example.jcex.ui.theme.AnimationCodelabTheme
import com.google.android.material.composethemeadapter.MdcTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MdcTheme {
                JCExApp()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun JCExApp() {
        val navigationController = rememberNavController()
        val screens =
            listOf(
                Screen.ComponentsScreen,
                Screen.KotlinTrainingScreen,
                Screen.StateScreen,
                Screen.Animations,
                Screen.Layouts,
                Screen.Theming
            )

        val context = LocalContext.current

        Scaffold(drawerContent = {
            DrawerHeader(modifier = Modifier.fillMaxWidth())
            DrawerBody(modifier = Modifier.fillMaxWidth(), onItemClick = {
                val intent = Intent(context, it.intentTo::class.java)
                context.startActivity(intent)
            })
        }, bottomBar = { BottomNavBar(navigationController, screens) }) { innerPadding ->
            NavHost(
                navController = navigationController,
                startDestination = Screen.ComponentsScreen.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = Screen.ComponentsScreen.route) {
                    ComposableScreen()
                }
                composable(route = Screen.KotlinTrainingScreen.route) {
                    KotlinTrainingScreen()
                }
                composable(route = Screen.StateScreen.route) {
                    val viewModel by viewModels<TodoVewModel>()
                    TodoActivityScreen(viewModel = viewModel)
                }
                composable(Screen.Animations.route) {
                    AnimationCodelabTheme {
                        HomeAnimations()
                    }
                }
                composable(route = Screen.Layouts.route) {
                    StraggeredGridLab()
                }
                composable(Screen.Theming.route) {
                    Home()
                }
                // ???
//                fragment<FirstFragment>(Screen.ViewToComposeScreen.route) {
//                    navigationController.navigatorProvider.getNavigator(R.id.nav_host_fragment_content_main)
//                        .createDestination().apply {
//                            setContent {
//                                (ComponentName(
//                                    LocalContext.current,
//                                    ViewToComposeActivity::class.java
//                                ))
//                            }
//                        }

//                    FirstFragment()
//                }
//                activity(route = Screen.ViewToComposeScreen.route) {
//                    val intent = Intent(LocalContext.current, ViewToComposeActivity::class.java)
//                    startActivity(intent)
//                }
            }
        }
    }

    @Composable
    fun BottomNavBar(
        navController: NavController,
        screens: List<Screen>
    ) {
        BottomNavigation(modifier = Modifier.wrapContentHeight()) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDest = navBackStackEntry?.destination

            screens.forEach { screen ->
                val selected = currentDest?.hierarchy?.any { it.route == screen.route } == true

                BottomNavigationItem(
                    label = {
                        if (selected) {
                            Text(screen.route)
                        } else {
                            Text("")
                        }
                    },
                    icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                    selected = selected,
                    onClick = { navController.navigate(screen.route) },
                    modifier = Modifier.then(
                        Modifier.weight(
                            if (selected) {
                                2f
                            } else {
                                1f
                            }
                        )
                    )
                )
            }
        }
    }

    @Preview
    @Composable
    fun JCExAppPreview() {
        MdcTheme {
            JCExApp()
        }
    }

}