package com.cookpad.core.screens.utils

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.*

@Composable
fun LottieAnime(size: Dp, lottieFile: Int, speed: Float) {
    val composition: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(lottieFile)
    )

    val progressAnimation by animateLottieCompositionAsState(
        composition = composition.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = speed
    )

    LottieAnimation(
        modifier = Modifier.size(size),
        composition = composition.value,
        progress = progressAnimation
    )
}