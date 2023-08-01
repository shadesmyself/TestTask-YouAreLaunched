package com.youarelaunched.challenge.ui.screen.view.event

import com.youarelaunched.challenge.event.Event

sealed class VendorEvents: Event {
    data class Search(val query: String) : VendorEvents()
}
