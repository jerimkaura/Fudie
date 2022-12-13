package com.cookpad.core.screens.country.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.screens.utils.SectionHeader
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Country

@Composable
fun SelectedCountry(
    selectedCountry: MutableState<Country?>
) {
    Column {
        Box(
            Modifier
                .padding(vertical = 0.dp, horizontal = 0.dp)
                .height(200.dp)
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
            Button(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
                shape = RoundedCornerShape(5.dp),
                onClick = {

                }) {
                Text(
                    text = "TRY ${selectedCountry.value?.strArea?.uppercase()}", style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionHeader(heading = "Have fun selecting", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}