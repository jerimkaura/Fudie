package com.cookpad.core.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cookpad.core.R
import com.cookpad.core.ui.theme.montserrat
import com.cookpad.domain.model.Country

@Composable
fun MealCountrySection(countries: List<Country>, navController: NavController) {
    LazyRow {
        items(countries.size) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 25.dp, horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(countries[it].flagUrl)
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)// it's the same even removing comments
                        .build(),
                    placeholder = painterResource(R.drawable.lily),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .width(80.dp)
                        .height(50.dp)
                        .clickable { },
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = countries[it].strArea, style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                    )
                )
            }
        }
    }
}

