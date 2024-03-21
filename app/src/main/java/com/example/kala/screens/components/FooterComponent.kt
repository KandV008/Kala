package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.NavigationButtonConfiguration
import com.example.kala.screens.components.buttons.NavigationButton

/**
 * Composable function for rendering the footer component.
 *
 * @param configuration The configuration data for the footer.
 * @param navController The navigation controller to handle navigation actions.
 * @param onAdviceTriggered The callback for handling advice triggered event.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Footer(
    configuration: FooterConfiguration,
    navController: NavController? = null,
    onAdviceTriggered: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RectangleShape)
            .background(Color.White)
            .padding(30.dp, 10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NavigationButton(
                configuration = NavigationButtonConfiguration.BACK,
                alpha = configuration.left(),
                navController = navController
            )
            NavigationButton(
                configuration = NavigationButtonConfiguration.HOME,
                alpha = configuration.center(),
                navController = navController
            )
            NavigationButton(
                configuration = NavigationButtonConfiguration.NEXT,
                alpha = configuration.right(),
                navController = navController,
                onAdviceTriggered = onAdviceTriggered,
            )
        }
    }
}

/**
 * Composable function for previewing the Footer component.
 * This preview function is used for testing and visualizing the Footer component.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun FooterPreview() {
    Column {
        Footer(FooterConfiguration.EMPTY)
        Spacer(modifier = Modifier.padding(5.dp))
        Footer(FooterConfiguration.ONLY_BACK)
        Spacer(modifier = Modifier.padding(5.dp))
        Footer(FooterConfiguration.ONLY_NEXT)
        Spacer(modifier = Modifier.padding(5.dp))
        Footer(FooterConfiguration.BACK_AND_NEXT)
        Spacer(modifier = Modifier.padding(5.dp))
        Footer(FooterConfiguration.BACK_AND_HOME)
        Spacer(modifier = Modifier.padding(5.dp))
        Footer(FooterConfiguration.NEXT_AND_HOME)
        Spacer(modifier = Modifier.padding(5.dp))
        Footer(FooterConfiguration.ALL)
    }
}
