package com.example.kala.ui.screens.commons

import com.example.kala.ui.components.buttons.NavigationButtonConfiguration

/*
    HeaderComponent
 */

enum class HeaderConfiguration(
    private val left: Pair<NavigationButtonConfiguration, Float>,
    private val center: Float,
    private val right: Pair<NavigationButtonConfiguration, Float>
) {
    UNREGISTERED_USER (
        Pair(NavigationButtonConfiguration.HELP, 1F),
        0F,
        Pair(NavigationButtonConfiguration.LANGUAGE, 1F)
    ),
    REGISTERED_USER(
        Pair(NavigationButtonConfiguration.HELP, 1F),
        1F,
        Pair(NavigationButtonConfiguration.OPTIONS, 1F)
    ),
    HELP_SCREEN(
        Pair(NavigationButtonConfiguration.HELP, 0F),
        1F,
        Pair(NavigationButtonConfiguration.LANGUAGE, 1F)
    ),
    LANGUAGE_SCREEN(
        Pair(NavigationButtonConfiguration.HELP, 1F),
        1F,
        Pair(NavigationButtonConfiguration.OPTIONS, 0F)
    ),
    OPTION_SCREEN(
        Pair(NavigationButtonConfiguration.HELP, 1F),
        1F,
        Pair(NavigationButtonConfiguration.LANGUAGE, 1F)
    );

    fun left(): Pair<NavigationButtonConfiguration, Float>{
        return left
    }

    fun center(): Float{
        return center
    }

    fun right(): Pair<NavigationButtonConfiguration, Float>{
        return right
    }
}

/*
    FooterComponent
 */

enum class FooterConfiguration(
    private val left: Float,
    private val center: Float,
    private val right: Float,
){
    EMPTY(0F, 0F, 0F),
    ONLY_BACK(1F, 0F, 0F),
    ONLY_NEXT(0F, 0F, 1F),
    BACK_AND_NEXT(1F, 0F, 1F),
    BACK_AND_HOME(1F, 1F, 0F),
    NEXT_AND_HOME(0F, 1F, 1F),
    ALL(1F, 1F, 1F);

    fun left(): Float{
        return left
    }

    fun center(): Float{
        return center
    }

    fun right(): Float{
        return right
    }
}