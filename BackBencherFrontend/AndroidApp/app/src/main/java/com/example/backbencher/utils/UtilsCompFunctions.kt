import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.backbencher.fetchDataFromApi
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.backbencher.R
import kotlinx.coroutines.delay


@Composable
fun ApiResponseScreen(apiResponse: String) {
    val questions  = apiResponse.split("\n")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Questions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            content = {
              items(questions){
                  Text(
                      text = it,
                      fontSize = 16.sp, // Adjust the fontSize as needed
                      modifier = Modifier.padding(8.dp)
                  )
                  Spacer(modifier = Modifier.height(8.dp))

              }  
            }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var videoLink by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.backbencherfront), // Replace with your image resource
            contentDescription = null, // Provide a description if needed
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Adjust the height as needed
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "BackBencher",
            style = TextStyle(
                fontSize = 40.sp, // Adjust the font size as needed
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = videoLink,
            onValueChange = { videoLink = it },
            label = {
                Text(text = "Enter The Video URL")
            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
           shape= RectangleShape,

            onClick = {

                scope.launch {
                    isLoading = true
                    val videoLinkValue = videoLink
                    val apiResponse = fetchDataFromApi(videoLinkValue)
                    navController.navigate("apiResponse/$apiResponse")
                    isLoading = false
                }


            }
        ) {
            Text("Get Questions")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            shape= RectangleShape,
                    onClick = {

                scope.launch {
                    isLoading = true
                    val videoLinkValue = videoLink
                    val apiResponse = fetchDataFromApiSummary(videoLinkValue)
                    navController.navigate("SummaryScreen/$apiResponse")
                    isLoading = false
                }


            }
        ) {
            Text("Get Summary")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            shape= CutCornerShape(1.dp),
                    onClick = {

                scope.launch {
                    isLoading = true
                    val videoLinkValue = videoLink
                    val apiResponse = fetchDataFromApiNotes(videoLinkValue)
                    navController.navigate("DataScreen/$apiResponse")
                    isLoading = false
                }


            }
        ) {
            Text("Get Notesss")
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Composable
fun LottieAnimationTheme(){



    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec= LottieCompositionSpec.RawRes(R.raw.splashanimationlottie))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )



    LottieAnimation(compositionResult.value, progress, modifier = Modifier.padding(5.dp) )
}


@Composable
fun LectureText_Notes_Summary_Screen(text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lecture",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

//
//@Composable
//fun CardViewWithImageAndText(imageResId: Int, title: String, subtitle1: String, subtitle2: String) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .clip(MaterialTheme.shapes.medium)
//            .clickable {
//
//            },
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 10.dp
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.background)
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = imageResId),
//                contentDescription = null, // Provide a description if needed
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(MaterialTheme.shapes.medium),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column(
//                modifier = Modifier.weight(1f),
//                verticalArrangement = Arrangement.spacedBy(4.dp)
//            ) {
//                Text(
//                    text = title,
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                )
//                Text(text = subtitle1)
//                Text(text = subtitle2)
//            }
//        }
//    }
//}
//
//@Composable
//fun CardViewExample() {
//    Column {
//
//            CardViewWithImageAndText(
//                imageResId = R.drawable.shipment, // Replace with your image resource
//                title = "Title",
//                subtitle1 = "Subtitle 1",
//                subtitle2 = "Subtitle 2",
//            )
//            Divider(
//                color = Color.Gray,
//                thickness = 1.dp,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ModernStyledTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    modifier: Modifier = Modifier,
//    shape: Shape = MaterialTheme.shapes.medium,
//    border: BorderStroke? = BorderStroke(4.dp, Color.Black),
//    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
//        textColor = Color.Black,
//        containerColor = Color.LightGray,
//        focusedIndicatorColor = Color.Transparent,
//        unfocusedIndicatorColor = Color.Transparent,
//        disabledIndicatorColor = Color.Transparent,
//
//
//    )
//) {
//    val density = LocalDensity.current.density
//    TextField(
//        value = value,
//        onValueChange = onValueChange,
//        textStyle = TextStyle(fontSize = 16.sp),
//        keyboardOptions = KeyboardOptions.Default.copy(
//            imeAction = ImeAction.Done
//        ),
//        keyboardActions = KeyboardActions(
//            onDone = {
//                // Handle the done action if needed
//            }
//        ),
//        singleLine = true,
//        label={ Text(text = "Enter The Video Link")},
//        shape = shape,
//        colors = colors,
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    )
//}

fun actionBarRemove(window : Window){
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
}