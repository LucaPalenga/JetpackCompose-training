package com.example.jcex.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jcex.navigation.components.RallyTabRow
import com.example.jcex.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val allScreens = RallyScreen.values().toList()
//        var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }

        /**
         * Creo un navigation controller
         */
        val navController = rememberNavController()

        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = RallyScreen.fromRoute(backStackEntry.value?.destination?.route)

        val accountsName = RallyScreen.Accounts.name

        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = allScreens,
                    /**
                     * Uso il callback della RallyTabRow per cambiare schermata tramite
                     * il navigation controller
                     */
                    onTabSelected = { screen -> navController.navigate(screen.name) },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = RallyScreen.Overview.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(RallyScreen.Overview.name) {
                    OverviewBody(
                        onClickSeeAllAccounts = { navController.navigate(RallyScreen.Accounts.name) },
                        onClickSeeAllBills = { navController.navigate(RallyScreen.Bills.name) },
                        onAccountClick = { name -> navigateToSingleAccount(navController, name) }
                    )
                }
                composable(route = RallyScreen.Accounts.name) {
                    AccountsBody(accounts = UserData.accounts) { name ->
                        navigateToSingleAccount(navController, name)
                    }
                }
                composable(route = RallyScreen.Bills.name) {
                    BillsBody(bills = UserData.bills)
                }
                composable(
                    route = "$accountsName/{name}",
                    arguments = listOf(
                        navArgument("name") {
                            type = NavType.StringType
                        })
                )
                { entry ->
                    // ricavo il "name" dagli arguments della NavBackStackEntry
                    val accountName = entry.arguments?.getString("name")
                    // ricavo l'account tramite l'accountName
                    val account = UserData.getAccount(accountName = accountName)
                    // creao il composable passandogli l'account
                    SingleAccountBody(account = account)
                }
            }
//            Box(Modifier.padding(innerPadding)) {
//                currentScreen.content(
//                    onScreenChange = { screen ->
//                        currentScreen = RallyScreen.valueOf(screen)
//                    }
//                )
//            }
        }
    }
}

// gestione click su singolo account
fun navigateToSingleAccount(navController: NavHostController, accountName: String) {
    navController.navigate("${RallyScreen.Accounts.name}/$accountName")
}
