package com.example.kala.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.model.FireBaseService
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.components.buttons.LargeButton
import com.example.kala.ui.components.buttons.LargeButtonConfiguration
import com.example.kala.ui.components.popUps.ConfirmationPopUp
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.MAIN_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.OPTION_SCREEN_ROUTE
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
    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.OPTION_SCREEN,
        triggerScreen = OPTION_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_HOME,
        onAdviceTriggered = { }
    ) {
        Title(configuration = TitleConfiguration.OPTIONS)
        Spacer(modifier = Modifier.padding(dimens.space3))
        OptionScreenBody(navController)
        Spacer(modifier = Modifier.padding(dimens.space3))
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

    if (deletingUser){
        deletingUser = false
        FireBaseService.DeleteUser(navController)
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
