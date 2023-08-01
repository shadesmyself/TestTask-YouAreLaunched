package com.youarelaunched.challenge.base

import androidx.lifecycle.ViewModel
import com.youarelaunched.challenge.event.Event

abstract class BaseViewModel<E: Event>: ViewModel() {
    abstract fun onTriggerEvent(event: E)
}