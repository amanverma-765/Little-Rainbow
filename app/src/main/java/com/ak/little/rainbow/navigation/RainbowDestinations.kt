package com.ak.little.rainbow.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed interface RainbowDestinations {

    @Serializable
    data object AdminLogin: RainbowDestinations

    @Serializable
    data object Dashboard: RainbowDestinations

}
