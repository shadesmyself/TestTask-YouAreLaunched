package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.view.event.VendorEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

// These are my first tests, please do not judge strictly =)

@OptIn(ExperimentalCoroutinesApi::class)
class VendorsVMTest {

    private lateinit var viewModel: VendorsVM

    private val mainThreadSurrogate = UnconfinedTestDispatcher()

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
    }

    @BeforeEach
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `getVendors_ on event search and repository has results_data received`(): Unit =
        runTest(mainThreadSurrogate) {
            // Arrange
            val companyName = "Florists"

            val vendor = listOf(
                createVendor(1, "Sample Name", "kitty.png", area = "Kyiv", true, "SomeTag"),
                createVendor(2, "Florists", "flower.png", "Lviv", false, "LvivFlowers"),
            )

            val repository = mock<VendorsRepository>()
            `when`(repository.getVendors(companyName)).thenReturn(vendor)

            viewModel = VendorsVM(repository)

            // Act
            viewModel.onTriggerEvent(VendorEvents.Search(companyName))

            // Assert
            val result = viewModel.uiState.firstOrNull()
            assertNotNull(result)
            checkNotNull(result)
            assertTrue(!result.vendors.isNullOrEmpty())
            assertTrue(result.error.isNullOrEmpty())
        }

    @Test
    fun `getVendors_ test with error during data load`() = runTest(mainThreadSurrogate) {
        // Arrange
        val companyName = "ErrorCompany"
        val errorMessage = "Unknown error occurred"
        val repository = mock<VendorsRepository>()
        viewModel = VendorsVM(repository)

        `when`(repository.getVendors(companyName)).thenThrow(RuntimeException(errorMessage))

        // Act
        viewModel.onTriggerEvent(VendorEvents.Search(companyName))

        // Assert
        val result = viewModel.uiState.firstOrNull()
        assertNotNull(result)
        checkNotNull(result)
        assertTrue(result.vendors.isNullOrEmpty())
        assertTrue(!result.error.isNullOrEmpty())
        assertEquals(result.error, errorMessage)
    }

    private fun createVendor(id: Int, companyName: String, coverPhoto: String, area: String, favorite: Boolean,  tags: String): Vendor {
        return Vendor(id, companyName = companyName, coverPhoto = coverPhoto, area = area, favorite = favorite, null, tags = tags)
    }
}








