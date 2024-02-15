package com.example.kala.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.SmallButtonConfiguration
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.SmallButton
import com.example.kala.screens.components.Title
import com.example.kala.ui.theme.BoneWhite

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LanguageScreen(){
    Scaffold{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header(configuration = HeaderConfiguration.LANGUAGE_SCREEN)
            Spacer(Modifier.padding(10.dp))
            Title(configuration = TitleConfiguration.LANGUAGES)
            Spacer(modifier = Modifier.weight(0.7f))
            LanguageScreenBody()
            Spacer(modifier = Modifier.weight(1f))
            Footer(configuration = FooterConfiguration.BACK_AND_HOME)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LanguageScreenBody(){
    Row {
        SmallButton(configuration = SmallButtonConfiguration.ENGLISH)
        Spacer(modifier = Modifier.padding(15.dp))
        SmallButton(configuration = SmallButtonConfiguration.SPANISH)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun LanguageScreenPreview(){
    LanguageScreen()
}