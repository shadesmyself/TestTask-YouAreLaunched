package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NoSearchResultsView() {
       Column(modifier = Modifier
           .background(VendorAppTheme.colors.background)
           .fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally)
            {
           Text(text = stringResource(id = R.string.no_result), color = VendorAppTheme.colors.darkGreen, textAlign = TextAlign.Center, fontSize = 28.sp)

           Text(text = stringResource(id = R.string.try_different_search), color = VendorAppTheme.colors.textDark, textAlign = TextAlign.Center, fontSize = 16.sp)

       }
}