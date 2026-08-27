package com.example.kala.ui.components.buttons

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kala.R
import com.example.kala.ui.components.SVG_DESCRIPTION
import com.example.kala.ui.theme.dimens

@Composable
fun BuyMeACoffeeButton() {
    val context = LocalContext.current
    val url = "https://buymeacoffee.com/kandv008"

    Button(
        onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            context.startActivity(intent)
        },
        modifier = Modifier
            .width(dimens.buyMeACoffeeButtonWidth)
            .height(dimens.buyMeACoffeeButtonHeight),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(dimens.rounded),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.bmc_button),
            contentDescription = SVG_DESCRIPTION,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}
/**
 * Composable function for rendering a preview of the LargeButton component.
 * This preview function is used for testing and visualizing the LargeButton component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun BuyMeACoffeeButtonPreview() {
    Scaffold {
        BuyMeACoffeeButton()
    }
}
