package com.example.jcex.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Screen metadata for Rally.
 */
enum class RallyScreen(
    val icon: ImageVector,
    /**
     * Rimuovo il body da questa classe enum, in questo modo i ragionamenti inerenti la navigazione
     * risiedono solo nel NavHost di RallyApp
     */
//    val body: @Composable ((String) -> Unit) -> Unit
) {
    Overview(
        icon = Icons.Filled.PieChart,
//        body = { OverviewBody() }
    ),
    Accounts(
        icon = Icons.Filled.AttachMoney,
//        body = { AccountsBody(UserData.accounts) }
    ),
    Bills(
        icon = Icons.Filled.MoneyOff,
//        body = { BillsBody(UserData.bills) }
    );

//    @Composable
//    fun content(onScreenChange: (String) -> Unit) {
//        body(onScreenChange)
//    }

    companion object {
        fun fromRoute(route: String?): RallyScreen =
            when (route?.substringBefore("/")) {
                Accounts.name -> Accounts
                Bills.name -> Bills
                Overview.name -> Overview
                null -> Overview
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}