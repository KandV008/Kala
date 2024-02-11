package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kala.R

const val LANGUAGE = "language"
const val HELP = "help"
const val OPTIONS = "options"
const val BACK = "back"
const val HOME = "home"
const val NEXT = "next"


val svgNavigationButton: Map<String, Int> = mapOf(
    LANGUAGE to R.drawable.ic_united_kingdom_flag, /* TODO Dynamic Icon */
    HELP to R.drawable.ic_question,
    OPTIONS to R.drawable.ic_options,
    BACK to R.drawable.ic_back,
    HOME to R.drawable.ic_home,
    NEXT to R.drawable.ic_next,
)

val actionsNavigationButton: Map<String, () -> Unit> = mapOf(
    /* TODO Functions associate to a configuration */
)

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationButton(configuration: String, alpha: Float = 1F) {
    Button(
        onClick = { actionsNavigationButton.getOrDefault(configuration) { println("NOT VALID CONFIGURATION") } },
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .size(60.dp)
            .alpha(alpha),
        contentPadding = PaddingValues(10.dp),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Image(
            painter = painterResource(id = svgNavigationButton.getOrDefault(configuration, 0)),
            contentDescription = "SVG FILE"
        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun NavigationButtonPreview() {
    Column {
        NavigationButton(LANGUAGE)
        Spacer(modifier = Modifier.padding(5.dp))
        NavigationButton(HELP)
        Spacer(modifier = Modifier.padding(5.dp))
        NavigationButton(OPTIONS)
        Spacer(modifier = Modifier.padding(5.dp))
        NavigationButton(BACK)
        Spacer(modifier = Modifier.padding(5.dp))
        NavigationButton(HOME)
        Spacer(modifier = Modifier.padding(5.dp))
        NavigationButton(NEXT)
    }
}