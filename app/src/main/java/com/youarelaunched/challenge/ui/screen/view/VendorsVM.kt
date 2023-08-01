package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.base.BaseViewModel
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.event.VendorEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : BaseViewModel<VendorEvents>() {

    private val _uiState = MutableStateFlow(
        VendorsScreenUiState(
            vendors = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getVendors()
    }

    override fun onTriggerEvent(event: VendorEvents) {
        when (event) {
            is VendorEvents.Search -> getVendors(event.query)
        }
    }

    private fun getVendors(companyName: String? = "") {
        viewModelScope.launch {
            try {
                val vendors = repository.getVendors(companyName)
                _uiState.update {
                    it.copy(vendors = vendors)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Some error occurred"
                    )
                }
            }
        }
    }
}