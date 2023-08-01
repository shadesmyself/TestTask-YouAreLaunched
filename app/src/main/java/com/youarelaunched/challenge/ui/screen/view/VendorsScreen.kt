package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.youarelaunched.challenge.ui.screen.view.content.VendorContent
import com.youarelaunched.challenge.ui.screen.view.event.VendorEvents

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    VendorsScreen(viewModel)
}

@Composable
fun VendorsScreen(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()
    VendorContent(
        vendors = uiState.vendors,
        onSearch = { viewModel.onTriggerEvent(VendorEvents.Search(it)) }
    )
}