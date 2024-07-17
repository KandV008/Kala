package com.example.kala.ui.screens.commons

import com.example.kala.ui.components.buttons.NavigationButtonConfiguration

/**
 * Enum class defining configurations for the header component.
 *
 * @property left Pair of [NavigationButtonConfiguration] and alpha value for the left navigation button.
 * @property center Alpha value for the application name in the center of the header.
 * @property right Pair of [NavigationButtonConfiguration] and alpha value for the right navigation button.
 */
enum class HeaderConfiguration(
    private val left: Pair<NavigationButtonConfiguration, Float>,
    private val center: Float,
    private val right: Pair<NavigationButtonConfiguration, Float>
) {
    UNREGISTERED_USER(
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

    /**
     * Retrieves the left navigation button configuration and its alpha value.
     */
    fun left(): Pair<NavigationButtonConfiguration, Float> {
        return left
    }

    /**
     * Retrieves the alpha value for the application name in the center of the header.
     */
    fun center(): Float {
        return center
    }

    /**
     * Retrieves the right navigation button configuration and its alpha value.
     */
    fun right(): Pair<NavigationButtonConfiguration, Float> {
        return right
    }
}

/**
 * Enum class defining configurations for the footer component.
 *
 * @property left Alpha value for the left footer button.
 * @property center Alpha value for the center footer button.
 * @property right Alpha value for the right footer button.
 */
enum class FooterConfiguration(
    private val left: Float,
    private val center: Float,
    private val right: Float,
) {
    EMPTY(0F, 0F, 0F),
    ONLY_BACK(1F, 0F, 0F),
    ONLY_NEXT(0F, 0F, 1F),
    BACK_AND_NEXT(1F, 0F, 1F),
    BACK_AND_HOME(1F, 1F, 0F),
    NEXT_AND_HOME(0F, 1F, 1F),
    ALL(1F, 1F, 1F);

    /**
     * Retrieves the alpha value for the left footer button.
     */
    fun left(): Float {
        return left
    }

    /**
     * Retrieves the alpha value for the center footer button.
     */
    fun center(): Float {
        return center
    }

    /**
     * Retrieves the alpha value for the right footer button.
     */
    fun right(): Float {
        return right
    }
}
