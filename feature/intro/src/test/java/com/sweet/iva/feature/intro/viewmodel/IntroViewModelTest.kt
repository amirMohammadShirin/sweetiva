package com.sweet.iva.feature.intro.viewmodel

import app.cash.turbine.test
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.test.rule.MainDispatcherRule
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.feature.intro.model.IntroAction
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class IntroViewModelTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createDispatcherProvider(dispatcher: TestDispatcher): DispatcherProvider {
        return  object : DispatcherProvider{
            override val ui: CoroutineDispatcher
                get() = dispatcher
            override val io: CoroutineDispatcher
                get() = dispatcher
            override val default: CoroutineDispatcher
                get() = dispatcher
        }
    }

    @Test
    fun `when enter button clicked process then app should navigate to login graph`() {

        runTest {

            val testDispatcher = StandardTestDispatcher(testScheduler)

            val viewModel = spyk(
                IntroViewModel(
                    createDispatcherProvider(testDispatcher)
                )
            )

            viewModel.process(IntroAction.EntryButtonClicked)

            viewModel.navigationFlow.test {

                val command = awaitItem()
                assertEquals(
                    ApplicationRoutes.loginGraphRoute,
                    command.route
                )

            }

        }

    }



}