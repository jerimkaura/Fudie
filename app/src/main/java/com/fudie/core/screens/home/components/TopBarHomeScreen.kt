package com.fudie.core.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import color_box_border_dark
import color_box_border_light
import color_box_light
import color_surface_dark
import com.fudie.core.R
import com.fudie.core.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHomeScreen(
    text: String,
    onTextChange: (String) -> Unit,
    onClearClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    val boxColor = if (isSystemInDarkTheme()) color_surface_dark else color_box_light
    val borderColor =
        if (isSystemInDarkTheme()) color_box_border_dark else color_box_border_light
    val bgColor = if (isSystemInDarkTheme())  Color(0XFF2F2E41) else   Color(0xFFF8F6F8)

    TopAppBar(
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Search...",
                            style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp, color = Color.Gray
                            )
                        )
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .padding(0.dp),
                    value = text,
                    onValueChange = {
                        onTextChange(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSearchClicked(text)
                    }),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = boxColor,
                        unfocusedIndicatorColor = borderColor,
                        focusedIndicatorColor = borderColor
                    ),
                    textStyle = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier
                                .background(boxColor, CircleShape)
                                .size(30.dp)
                                .clip(CircleShape),
                            onClick = {
                                onClearClicked()
                            }) {

                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_circle_x),
                                contentDescription = "Search Icon",
                                tint = Color.Gray
                            )
                        }
                    }
                )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        actions = {
            IconButton(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .background(boxColor, CircleShape)
                    .size(35.dp)
                    .clip(CircleShape),
                onClick = {
                    onSearchClicked(text)
                }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                )
            }
        }
    )

}