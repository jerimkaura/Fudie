package com.fudie.core.screens.country.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.fudie.core.screens.utils.SectionHeader
import com.fudie.domain.model.Country
import com.fudie.core.R


@Composable
fun SelectedCountry(
    selectedCountry: MutableState<Country?>
) {
    val imageHeight = (0.25 * LocalConfiguration.current.screenHeightDp).dp
    if (selectedCountry.value == null){

    }
    Column {
        Box(
            Modifier
                .padding(vertical = 0.dp, horizontal = 0.dp)
                .height(imageHeight)
                .background(Color.Blue.copy(0.1f))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(selectedCountry.value?.flagUrl)
                    .crossfade(true).diskCachePolicy(CachePolicy.ENABLED).build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xff000000).copy(alpha = 0.30F))
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionHeader(heading = "Select country", false, onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}