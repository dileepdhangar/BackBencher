package com.example.backbencher

import ApiResponseScreen
import LectureText_Notes_Summary_Screen
import MainScreen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.backbencher.Retrofit.RetrofitInstance
import com.example.backbencher.ui.theme.BackBencherTheme
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackBencherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }
                        composable(
                            "apiResponse/{apiResponse}",
                            arguments = listOf(navArgument("apiResponse") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val apiResponse =
                                backStackEntry.arguments?.getString("apiResponse") ?: ""
                            ApiResponseScreen(apiResponse)
                        }
                        composable(
                            "DataScreen/{apiResponse}",
                            arguments = listOf(navArgument("apiResponse") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val apiResponse =
                                backStackEntry.arguments?.getString("apiResponse") ?: ""
                            LectureText_Notes_Summary_Screen(apiResponse)
                        }
                        composable(
                            "SummaryScreen/{apiResponse}",
                            arguments = listOf(navArgument("apiResponse") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val apiResponse =
                                backStackEntry.arguments?.getString("apiResponse") ?: ""
                            LectureText_Notes_Summary_Screen(apiResponse)
                        }
                    }


                }

            }
        }
    }
}

suspend fun fetchDataFromApi(query: String): String {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.apiService.fetchData(query)
            if (response.isSuccessful) {
                Log.d("Praveen", response.body().toString())
                response.body() ?: "No response"

            } else {
                Log.d("error in retrofit", "${response.code()} ")
                "API Error: ${response.code()}"
            }

        } catch (e: Exception) {
            Log.d("exception", "${e.message}: ")
            "Error: ${e.message}"
        }
    }
}





