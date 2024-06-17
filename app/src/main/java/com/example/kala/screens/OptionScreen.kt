package com.example.kala.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.LargeButtonConfiguration
import com.example.kala.configuration.MAIN_SCREEN_ROUTE
import com.example.kala.configuration.OPTION_SCREEN_ROUTE
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.model.FireBaseService
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.buttons.LargeButton
import com.example.kala.screens.components.popups.ConfirmationPopUp
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth.getInstance

/**
 * List of configurations for the type buttons in the Option screen.
 */
val typeButtons: List<LargeButtonConfiguration> = listOf(
    LargeButtonConfiguration.LOG_OUT,
    LargeButtonConfiguration.DELETE_USER,
)

/**
 * Composable function for rendering the Option screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OptionScreen(navController: NavController? = null){
    Scaffold(
        topBar = {
            Header(
                configuration = HeaderConfiguration.OPTION_SCREEN,
                navController = navController,
                triggerScreen = OPTION_SCREEN_ROUTE,
            )
        },
        bottomBar = {
            Footer(configuration = FooterConfiguration.BACK_AND_HOME, navController)
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.padding(dimens.space4))
            Title(configuration = TitleConfiguration.OPTIONS)
            Spacer(modifier = Modifier.weight(1f))
            OptionScreenBody(navController)
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(dimens.space4))
        }
    }
}

/**
 * Composable function for rendering the body of the Option screen.
 */
@Composable
fun OptionScreenBody(navController: NavController? = null){
    var deleteButtonTriggered by remember {
        mutableStateOf(false)
    }
    var deletingUser by remember {
        mutableStateOf(false)
    }

    val optionFunctions: List<() -> Unit> = listOf(
        {
            getInstance().signOut()
            navController?.navigate(route = MAIN_SCREEN_ROUTE)
        },
        { deleteButtonTriggered = true },
    )

    if (deleteButtonTriggered){
        ConfirmationPopUp(
            onConfirmButton = {
                deleteButtonTriggered = false
                deletingUser = true
            },
            onDismissButton = { deleteButtonTriggered = false },
        )
    }

    val current = LocalContext.current

    if (deletingUser){
        deletingUser = false
        FireBaseService.DeleteUser(current, navController)
    }

    LazyColumn{
        itemsIndexed(typeButtons) { index, type ->
            LargeButton(configuration = type, onAdviceTriggered = optionFunctions[index])
            Spacer(modifier = Modifier.padding(dimens.space1))
        }
    }
}



/**
 * Preview function for testing and visualizing the Option screen.
 */
@Preview(showBackground = true)
@Composable
fun OptionScreenPreview(){
    OptionScreen()
}
