package com.google.samples.apps.nowinandroid.ui.lesson15.homeworks

import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

object ForyouScreen : ComposeScreen<ForyouScreen>() {

    val toolbarTitle = child<KNode> {
        hasTestTag("toolbarTitle")
    }

    val toolbarSearchIcon = child<KNode> {
        hasTestTag("toolbarSearchIcon")
    }

    val toolbarGearIcon = child<KNode>{
        hasTestTag("toolbarGearIcon")
    }

    val title = child<KNode>{
        hasTestTag("title")
    }

    val subtitle = child<KNode>{
        hasTestTag("subtitle")
    }
}