package com.example.kala

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.model.FireBaseService
import com.example.kala.ui.components.buttons.NavigationButtonConfiguration
import com.example.kala.ui.components.buttons.getLanguageIconViaContext
import com.example.kala.ui.screens.AppNavigation
import com.example.kala.ui.screens.navigation.AppScreens
import com.example.kala.ui.theme.KalaTheme
import com.google.firebase.auth.FirebaseAuth

/**
 * MainActivity is the entry point of the application.
 * It sets up the content view and initializes the navigation button configuration.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val languageIcon = getLanguageIconViaContext(this)
        NavigationButtonConfiguration.LANGUAGE.updateIcon(languageIcon)

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {

            FireBaseService.loadUser(currentUser.uid) {

                setContent {
                    KalaTheme {
                        AppNavigation(AppScreens.homeScreen.route)
                    }
                }
            }

        } else {

            setContent {
                KalaTheme {
                    AppNavigation(AppScreens.mainScreen.route)
                }
            }
        }
    }
}

/**
 * A preview function for displaying the greeting in the Android Studio preview.
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KalaTheme {
        AppNavigation(AppScreens.mainScreen.route)
    }
}