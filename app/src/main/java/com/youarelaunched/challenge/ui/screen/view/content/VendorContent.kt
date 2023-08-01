package com.youarelaunched.challenge.ui.screen.view.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.view.components.NoSearchResultsView
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun VendorContent(
    vendors: List<Vendor>?,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VendorAppTheme.colors.background)
    ) {
        var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var isTextFieldEmpty by remember { mutableStateOf(true) }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = textFieldValue,
            onValueChange = { value ->
                val currentText = textFieldValue.text
                textFieldValue = value
                isTextFieldEmpty = value.text.isEmpty()
                if (currentText != value.text) {
                    onSearch(value.text)
                }
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    textAlign = TextAlign.Left,
                    color = VendorAppTheme.colors.textDark,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                )
            },
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                if (!isTextFieldEmpty) {
                    IconButton(onClick = {
                        textFieldValue = TextFieldValue()
                        isTextFieldEmpty = true
                        onSearch(textFieldValue.text)
                    }) {
                        Icon(
                            Icons.Default.Clear,
                            tint = VendorAppTheme.colors.textDark,
                            contentDescription = null
                        )
                    }
                } else {
                    Icon(
                        Icons.Default.Search,
                        tint = VendorAppTheme.colors.textDark,
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = VendorAppTheme.colors.textDark,
                cursorColor = VendorAppTheme.colors.textDark,
                backgroundColor = VendorAppTheme.colors.paleGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
        )
        if (!vendors.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding()
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    vertical = 24.dp, horizontal = 16.dp
                )
            ) {
                items(vendors) { vendor ->
                    VendorItem(
                        vendor = vendor
                    )
                }
            }
        } else if (vendors?.isEmpty() == true) {
            NoSearchResultsView()
        }
    }
}