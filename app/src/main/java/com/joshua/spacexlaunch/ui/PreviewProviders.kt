package com.joshua.spacexlaunch.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TitleProvider: PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "Launches"
    )
}

class UrlProvider: PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "https://github.com/r-spacex/SpaceX-API",
        "https://google.com"
    )
}