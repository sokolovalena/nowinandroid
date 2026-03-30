package lesson15.homeworks

import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

object SearchScreen: ComposeScreen<SearchScreen>() {
    val textField = child<KNode>{
        hasTestTag("searchTextField")
    }

    val iconSearch = child<KNode>{
        hasTestTag("iconSearch")
    }

    val backButton = child<KNode>{
        hasTestTag("backButton")
    }
}