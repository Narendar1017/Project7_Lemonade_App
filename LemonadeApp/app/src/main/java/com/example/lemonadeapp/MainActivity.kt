package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme
import java.util.concurrent.ThreadLocalRandom

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF878036))
                    .statusBarsPadding()) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadeApp() {
    LemonadeAppDesign(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    )
}

@Composable
fun LemonadeAppDesign(modifier: Modifier) {
    var stages by remember {
        mutableStateOf(value = 1)
    }

    var imageSelector = when(stages) {
        1 -> painterResource(id = R.drawable.lemon_tree)
        2 -> painterResource(id = R.drawable.lemon_squeeze)
        3 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }
    var instructionText = when(stages) {
        1 -> "Tap the lemon tree to select a lemon"
        2 -> "Keep taping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }
    var squeezeRequired = ThreadLocalRandom.current().nextInt(1,4)
    var squeezeCount = 0
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Lemonade",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFF8E44C))
                .padding(vertical = 12.dp),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button (
                onClick = {
                    if(stages == 2 && squeezeCount!=squeezeRequired) {
                        squeezeCount++
                    }else if(stages == 2 && squeezeCount==squeezeRequired) {
                        stages = 3
                        squeezeCount = 0
                        squeezeRequired = ThreadLocalRandom.current().nextInt(1,4)
                    }else {
                        if (stages == 4) stages = 1 else stages++
                    }
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonColors(containerColor = Color(0xFFC3ECD2), contentColor = Color(0xFFC3ECD2), disabledContentColor = Color(0xFFC3ECD2), disabledContainerColor = Color(0xFFC3ECD2))
            ){
                Image(
                    painter = imageSelector,
                    modifier = Modifier.height(160.dp),
                    contentDescription = null,
                )
            }
            Text(
                text = instructionText,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 30.dp)
            )
        }
    }
}