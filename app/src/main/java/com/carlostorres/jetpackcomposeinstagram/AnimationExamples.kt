package com.carlostorres.jetpackcomposeinstagram

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.carlostorres.jetpackcomposeinstagram.model.Routes
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSimple() {

    var secondColor by rememberSaveable {
        mutableStateOf(false)
    }

    var showBox by rememberSaveable {
        mutableStateOf(true)
    }

    val realColor2 by animateColorAsState(
        targetValue = if (secondColor) Color.Red else Color.Yellow,
        animationSpec = tween(durationMillis = 2000),
        finishedListener = {
            showBox = !showBox
        }
    )

    if (showBox){
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor2)
            .clickable { secondColor = !secondColor }
        ){
        }
    }



}

@Composable
fun SizeAnimation(navigationController: NavHostController) {

    var smallSize by rememberSaveable {
        mutableStateOf(true)
    }

    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 500),
        finishedListener = {
            navigationController.navigate(Routes.VisivilityAnimation.route)
        }
    )
    Box(modifier = Modifier
        .size(size)
        .background(Color.Cyan)
        .clickable { smallSize = !smallSize }
    ){
    }

}

@Composable
fun VisivilityAnimation() {

    var isVisible by rememberSaveable {
        mutableStateOf(true)
    }
    
    Column(Modifier.fillMaxSize()) {

        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Mostrar/Ocultar")
        }
        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility (
            visible = isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ){
            Box(
                Modifier
                    .size(150.dp)
                    .background(Color.Red)) {

            }

        }
    }


    
}

@Composable
fun CrossfadeExamppleAnimation() {
    var myComponentType : ComponentType by  remember{ mutableStateOf(ComponentType.Text) }

    Column(Modifier.fillMaxSize()) {
        
        Button(onClick = { myComponentType = getComponentTypeRandom() }) {
            Text(text = "Cambiar Componente")
        }

        Crossfade(targetState = myComponentType) {myComponentType->

            when(myComponentType){

                ComponentType.Image->{
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
                ComponentType.Text->{
                    Text(text = "Soy un componente")
                }
                ComponentType.Box->{
                    Box(modifier = Modifier
                        .size(150.dp)
                        .background(Color.Red))

                }
                ComponentType.Error->{
                    Text(text = "Eroooor")
                }

            }
        }

    }


}

enum class ComponentType(){
    Image, Text, Box, Error
}

fun getComponentTypeRandom():ComponentType{
    return when (nextInt(from= 0, until = 3)){
        0 -> ComponentType.Image
        1 -> ComponentType.Box
        2 -> ComponentType.Text
        else -> ComponentType.Error
    }
}