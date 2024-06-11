package com.sweet.iva.feature.login.phoneEntry.viewmodel

import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.test.rule.MainDispatcherRule
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryAction
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(JUnit4::class)
class PhoneEntryViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createViewModel(scheduler: TestCoroutineScheduler) =
        PhoneEntryViewModel(mockDispatcherProvider(scheduler))

    private fun mockDispatcherProvider(scheduler: TestCoroutineScheduler): DispatcherProvider {
        val dispatcher = StandardTestDispatcher(scheduler)
        return object : DispatcherProvider {
            override val ui: CoroutineDispatcher
                get() = dispatcher
            override val io: CoroutineDispatcher
                get() = dispatcher
            override val default: CoroutineDispatcher
                get() = dispatcher

        }
    }

    @Test
    fun `when phoneNumber changed with a valid value then phone number state should be changed`() {

        runTest {

            val viewModel = createViewModel(mockk())
            val phoneNumber = "09120425101"

            viewModel.process(PhoneEntryAction.OnPhoneNumberChanged(phoneNumber))

            advanceUntilIdle()

            assertEquals(
                phoneNumber,
                viewModel.currentState.phoneNumberModel.value
            )

        }

    }

    @Test
    fun `when phoneNumber changed with invalid phone number then error message should be shown`() {

        runTest {

            val viewModel = createViewModel(mockk())
            val phoneNumber = "02"

            viewModel.process(PhoneEntryAction.OnPhoneNumberChanged(phoneNumber))

            advanceUntilIdle()

            assertNotNull(
                viewModel.currentState.phoneNumberModel.errorMessage
            )

            assertEquals(
                phoneNumber,
                viewModel.currentState.phoneNumberModel.value
            )

        }

    }

}