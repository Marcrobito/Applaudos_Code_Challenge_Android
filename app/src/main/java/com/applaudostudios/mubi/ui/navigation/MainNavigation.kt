package com.applaudostudios.mubi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.applaudostudios.mubi.ui.screen.signin.SignInScreen
import com.applaudostudios.mubi.ui.screen.SplashScreen
import com.applaudostudios.mubi.ui.screen.home.HomeScreen
import com.applaudostudios.mubi.ui.screen.profile.ProfileScreen
import com.applaudostudios.mubi.ui.screen.search.SearchScreen
import com.applaudostudios.mubi.ui.screen.seriesdetail.SeriesDetailScreen
import com.applaudostudios.mubi.ui.screen.signin.LoginScreen
import com.applaudostudios.mubi.ui.screen.signin.RegisterScreen
import com.applaudostudios.mubi.ui.theme.MubiTheme

@Composable
fun MainNavigation(mainNavController: NavHostController? = null) {
    val navController = mainNavController ?: rememberNavController()
    MubiTheme {
        NavHost(
            navController = navController,
            startDestination = SPLASH_ROUTE_STRING
        ) {
            composable(SPLASH_ROUTE_STRING) {
                SplashScreen(navController = navController)
            }
            composable(SIGN_IN_ROUTE_STRING) {
                SignInScreen(navController = navController)
            }
            composable(REGISTER_ROUTE_STRING) {
                RegisterScreen(navController = navController)
            }
            composable(LOGIN_ROUTE_STRING) {
                LoginScreen(navController = navController)
            }
            composable(HOME_ROUTE_STRING) {
                HomeScreen(navController = navController)
            }
            composable(SEARCH_ROUTE_STRING) {
                SearchScreen(navController = navController)
            }
            composable(PROFILE_ROUTE_STRING) {
                ProfileScreen(navController = navController)
            }
            composable("$DETAIL_ROUTE_STRING/{seriesId}") {
                val seriesId = it.arguments?.getString("seriesId")
                seriesId?.let { id ->
                    SeriesDetailScreen(navController = navController, seriesId = id.toInt())
                }
            }
        }
    }
}