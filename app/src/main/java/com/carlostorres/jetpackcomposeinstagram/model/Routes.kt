package com.carlostorres.jetpackcomposeinstagram.model

sealed class Routes(val route:String){
    object Pantalla1:Routes("pantalla1")
    object Pantalla2:Routes("pantalla2")
    object Pantalla3:Routes("pantalla3")
    object Pantalla4:Routes("pantalla4/{age}"){
        fun createRoute(age : Int) = "pantalla4/$age"
    }
    object Pantalla5:Routes("pantalla5?name={name}"){
        fun createRoute(name : String) = "pantalla5?name=$name"
    }
    object LoginScreen:Routes("login_screen")
    object ScaffoldScreen:Routes("scaffold_screen")
    object ColorAnimationSimple:Routes("color_animation_simple")
    object SizeAnimation:Routes("size_animation")
    object VisivilityAnimation:Routes("visivility_animation")
    object CrossfadeExamppleAnimation:Routes("crossfade_example_animation")
}
