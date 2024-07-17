package com.example.kala.ui.components.inputs

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.ui.components.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens
import com.example.kala.ui.theme.fontFamily

/**
 * Composable function for rendering a small text input field.
 *
 * @param configuration The configuration for the small text input.
 * @param valueInput The current value of the text input.
 * @param onValueChange Callback function invoked when the value of the text input changes.
 */
@Composable
fun SmallTextInput(
    configuration: SmallTextInputConfiguration,
    valueInput: String,
    onValueChange: (String) -> Unit
) {
    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = if (configuration.isPassword()) KeyboardType.Password else KeyboardType.Text
    )

    val visualTransformation =
        if (configuration.isPassword())
            AsteriskTransformation()
        else
            VisualTransformation.None

    Column(
        modifier = Modifier
            .width(dimens.width8)
    ) {
        Text(
            text = stringResource(id = configuration.getLayer()),
            fontSize = dimens.fontSize1,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
        )

        Row(
            modifier = Modifier
                .height(dimens.height2),
        ) {
            TextField(
                value = valueInput,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = dimens.fontSize0,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .width(dimens.width5)
                    .height(dimens.height2)
                    .clip(RoundedCornerShape(dimens.rounded))
                    .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
                colors = inputTextColor,
                placeholder = {
                    Text(
                        text = stringResource(id = configuration.getPlaceholder()),
                        fontSize = dimens.fontSize0,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontFamily,
                    )
                }
            )
            Spacer(modifier = Modifier.size(dimens.space1))
            Box(
                modifier = Modifier
                    .border(dimens.border, Color.Black, shape = CircleShape)
                    .size(dimens.image4)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(dimens.padding2),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(
                        id = configuration.getSVGFile()
                    ),
                    contentDescription = SVG_DESCRIPTION
                )
            }
        }
    }
}

/**
 * Transformation for masking text with asterisks (used for password fields).
 */
class AsteriskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Replace each character with an asterisk
        val transformedText = AnnotatedString("*".repeat(text.length))
        // OffsetMapping.Identity ensures that cursor position and text selection work correctly
        return TransformedText(transformedText, OffsetMapping.Identity)
    }
}

/**
 * Composable function for rendering a preview of the SmallTextInput component.
 * This preview function is used for testing and visualizing the SmallTextInput component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewSmallTextInput() {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            items(SmallTextInputConfiguration.entries.toTypedArray()) { value ->
                SmallTextInput(configuration = value, "") {}
                Spacer(modifier = Modifier.padding(dimens.space0))
            }
        }
    }
}
