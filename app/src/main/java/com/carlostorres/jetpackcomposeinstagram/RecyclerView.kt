package com.carlostorres.jetpackcomposeinstagram

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlostorres.jetpackcomposeinstagram.model.SuperHero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {

//    LazyRow(content = )

    val myList = listOf("Ari", "Pepe", "Manolo", "Jaime")

    LazyColumn {
        item {
            Text(text = "primer item")
        }
        items(7) {
            Text(text = "Este es el itme $it")
        }
        items(myList) {
            Text(text = "Hola, me llamo $it")
        }
    }

}

@Composable
fun SuperHeroView() {

    val context = LocalContext.current

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperHeros()) { superHero ->
            ItemHero(
                superHero = superHero,
                onItemSelected = {
                    Toast.makeText(
                        context,
                        superHero.superheroName,
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {

    val context = LocalContext.current
    val superHero = getSuperHeros().groupBy { it.publisher }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superHero.forEach{(publisher, mySuperhero)->

            stickyHeader {
                Text(text = publisher, modifier = Modifier.fillMaxWidth().background(Color.Green), fontSize = 16.sp, color = Color.Black)
            }

            items(mySuperhero) { superHero ->
                ItemHero(
                    superHero = superHero,
                    onItemSelected = {
                        Toast.makeText(
                            context,
                            superHero.superheroName,
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            }
        }


    }

}

@Composable
fun SuperHeroWithSpecialControls() {

    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScope = rememberCoroutineScope()
    
    Column() {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperHeros()) { superHero ->
                ItemHero(
                    superHero = superHero,
                    onItemSelected = {
                        Toast.makeText(
                            context,
                            superHero.superheroName,
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            }
        }

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }
        if(showButton){
            Button(
                onClick = {
                    coroutinesScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(text = "Soy un boton")
            }
        }


    }

    

}

@Composable
fun SuperHeroGrindView() {

    val context = LocalContext.current

    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(getSuperHeros()) { superHero ->
            ItemHero(
                superHero = superHero,
                onItemSelected = {
                    Toast.makeText(
                        context,
                        superHero.superheroName,
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }
    })
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected : (SuperHero) -> Unit) {
    Card(border = BorderStroke(1.dp, Color.Red), modifier = Modifier
//        .width(200.dp)
        .padding(vertical = 8.dp, horizontal = 8.dp)
        .clickable { onItemSelected(superHero) }, shape = RoundedCornerShape(5.dp)) {
        Column() {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "Super Hero Avatar", 
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = superHero.superheroName, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = superHero.realName, modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 12.sp)
            Text(text = superHero.publisher, fontSize = 10.sp, modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp))
        }
    }
}

fun getSuperHeros(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Miles Morales", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Thor", "Thos Odison", "Marvel", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lanter", "Alan Scott", "DC", R.drawable.green_lantern),
        SuperHero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman),

        )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevSuperHeroWithSpecialControls() {
    SuperHeroStickyView()
}