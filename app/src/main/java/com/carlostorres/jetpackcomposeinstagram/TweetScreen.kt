package com.carlostorres.jetpackcomposeinstagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlostorres.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme

@Composable
fun TweetScreen() {

    Column(Modifier.fillMaxWidth()) {

        var isChatClicked by rememberSaveable {
            mutableStateOf(false)
        }
        var isRtClicked by rememberSaveable {
            mutableStateOf(false)
        }
        var isLikeClicked by rememberSaveable {
            mutableStateOf(false)
        }
        var chatCount by rememberSaveable {
            mutableStateOf(1)
        }
        var rtCount by rememberSaveable {
            mutableStateOf(1)
        }
        var likeCount by rememberSaveable {
            mutableStateOf(1)
        }

        Row() {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .padding(15.dp)
                    .clip(CircleShape),
            )

            Column(modifier = Modifier.padding(top=15.dp), verticalArrangement = Arrangement.Center) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Aris",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "@AristiDev",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp),
                        color = Color.Gray
                    )
                    Text(
                        text = "4h",
                        color = Color.Gray,
                    )
                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dots),
                            contentDescription = "Dots",
                            modifier = Modifier.padding(end= 15.dp)
                        )
                    }

                }

                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    maxLines = 5,
                    modifier = Modifier.padding(end= 15.dp)

                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(end = 15.dp, top = 10.dp), shape = RoundedCornerShape(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Tweet Picture",
                        contentScale = ContentScale.Crop,
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)){

                    Row(modifier = Modifier
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = if (isChatClicked == false){ painterResource(id = R.drawable.ic_chat)} else painterResource(id = R.drawable.ic_chat_filled),
                            contentDescription = "Chat",
                            modifier = Modifier
                                .clickable {
                                    isChatClicked = !isChatClicked
                                    if(isChatClicked){
                                        chatCount += 1
                                    } else {
                                        chatCount -= 1
                                    }
                                }
                        )
                        Text(text = chatCount.toString())

                    }
                    Row(modifier = Modifier
                        .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        if (isRtClicked){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rt),
                                contentDescription = "RT",
                                tint = Color.Green,
                                modifier= Modifier.clickable {
                                    isRtClicked = !isRtClicked
                                    if (isRtClicked){
                                        rtCount += 1
                                    } else {
                                        rtCount -= 1
                                    }
                                }
                            )
                            Text(text = rtCount.toString())
                        }else{
                            Icon(
                                painter = if (isRtClicked) painterResource(id = R.drawable.ic_rt) else painterResource(id = R.drawable.ic_rt) ,
                                contentDescription = "Share",
                                modifier= Modifier.clickable {
                                    isRtClicked = !isRtClicked
                                    if (isRtClicked){
                                        rtCount += 1
                                    } else {
                                        rtCount -= 1
                                    }
                                }
                            )
                            Text(text = rtCount.toString())
                        }


                    }
                    Row(modifier = Modifier
                        .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        if (isLikeClicked){
                            Icon(
                                tint =Color.Red,
                                painter = painterResource(id = R.drawable.ic_like_filled),
                                contentDescription = "like",
                                modifier=Modifier.clickable {
                                    isLikeClicked = !isLikeClicked
                                    if (isLikeClicked){
                                        likeCount += 1
                                    } else{
                                        likeCount -= 1
                                    }
                                }
                            )
                            Text(text = likeCount.toString())
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "like",
                                modifier=Modifier.clickable {
                                    isLikeClicked = !isLikeClicked
                                    if (isLikeClicked){
                                        likeCount += 1
                                    } else{
                                        likeCount -= 1
                                    }
                                }
                            )
                            Text(text = likeCount.toString())
                        }

                    }

                }




            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp), color = Color.Gray)
    }

}

@Preview (showSystemUi = true, showBackground = true)
@Composable
fun TweetPrev() {

    JetpackComposeInstagramTheme(darkTheme = true){
        TweetScreen()
    }


}