package com.carlostorres.jetpackcomposeinstagram

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.carlostorres.jetpackcomposeinstagram.model.Routes

@Composable
fun Navigation() {

}

@Composable
fun ScreenOne(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan)) {
        Text(
            text = "Pantalla 1",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla2.route) }
        )
    }
}

@Composable
fun ScreenTwo(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Green)) {
        Text(
            text = "Pantalla 2",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla3.route) }
        )
    }
}

@Composable
fun ScreenThree(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Magenta)) {
        Text(
            text = "Pantalla 3",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla4.createRoute(24)) }
        )
    }
}
@Composable
fun ScreenFour(navController: NavHostController, age: Int) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow)) {
        Text(
            text = "Tengo $age años",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla5.route) }
        )
    }
}
@Composable
fun ScreenFive(navController: NavHostController, name: String?) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Gray)) {
        Text(
            text = "Me llamo $name",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {  }
        )
    }
}
