package com.carlostorres.jetpackcomposeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlostorres.jetpackcomposeinstagram.login.ui.LoginScreen
import com.carlostorres.jetpackcomposeinstagram.login.ui.LoginViewModel
import com.carlostorres.jetpackcomposeinstagram.model.Routes
import com.carlostorres.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeInstagramTheme(darkTheme = false) {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.LoginScreen.route){
                        composable(Routes.Pantalla1.route){ ScreenOne(navigationController) }
                        composable(Routes.Pantalla2.route){ ScreenTwo(navigationController) }
                        composable(Routes.Pantalla3.route){ ScreenThree(navigationController) }
                        composable(Routes.LoginScreen.route){ LoginScreen(loginViewModel) }
                        composable(Routes.ScaffoldScreen.route){ ScaffoldExample() }
                        composable(
                            route = Routes.Pantalla4.route,
                            arguments = listOf(navArgument("age") {type = NavType.IntType}))
                        { backStackEntry ->
                            ScreenFour(
                                navigationController,
                                backStackEntry.arguments?.getInt("age") ?: 0
                            )
                        }
                        composable(
                            route = Routes.Pantalla5.route,
                            arguments = listOf(navArgument("name", {defaultValue = "Pepe"}) ))
                        { backStackEntry ->
                            ScreenFive(
                                navigationController,
                                backStackEntry.arguments?.getString("name")
                            )
                        }
                    }


                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeInstagramTheme {
    }
}