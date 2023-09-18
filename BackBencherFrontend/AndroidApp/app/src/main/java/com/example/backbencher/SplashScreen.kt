package com.example.backbencher

import LottieAnimationTheme
import actionBarRemove
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.backbencher.ui.theme.BackBencherTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBarRemove(window)
        setContent {
            BackBencherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                  Column(
                      horizontalAlignment = Alignment.CenterHorizontally,
                      verticalArrangement = Arrangement.Center
                  ) {
                      LottieAnimationTheme()
                      Text(
                          text = "BackBencher",
                          style = TextStyle(
                              fontSize = 30.sp, // Adjust the font size as needed
                              fontWeight = FontWeight.Bold,
                              color = Color.Black,
                              fontStyle = FontStyle.Italic
                          ),
                          modifier = Modifier.padding(bottom = 16.dp)
                      )
                  }


                }
            }
        }
        GlobalScope.launch {
            try {
                delay(4000L)
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                finish()
            } catch (e: Exception) {
                Log.d("kotlin", "onCreate: ${e.toString()}")
            }
        }
    }
}

