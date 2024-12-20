package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }
}


@Composable
fun Design(modifier: Modifier = Modifier) {

    var currentIndex by remember { mutableIntStateOf(1) }
    var StartCount by remember { mutableIntStateOf(1) }


    val stepText = when (currentIndex) {
        1 -> R.string.Lemon_tree
        2 -> R.string.Lemon
        3 -> R.string.Glass_of_lemonade
        4 -> R.string.Empty_glass
        else -> R.string.Lemon_tree
    }
    val imageResource = when (currentIndex) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    Text(
        text = stringResource(R.string.app_name), modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .padding(8.dp),
        color = Color.Black,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(100.dp))

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = stepText),
            modifier = Modifier
                .size(200.dp)
                .background(color = Color(0xFF03DAC5))
                .clickable {
                    when (currentIndex) {
                        1 -> {

                            StartCount = (2..5).random()
                            currentIndex++
                        }
                        2 -> {

                            if (StartCount > 1) {
                                StartCount--
                            } else {
                                currentIndex++
                            }
                        }
                        3 -> currentIndex++
                        4 -> currentIndex = 1
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(text = stringResource(id = stepText))

    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        Design(modifier = Modifier.fillMaxSize())
    }
}