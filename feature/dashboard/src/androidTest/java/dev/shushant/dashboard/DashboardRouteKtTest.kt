package dev.shushant.dashboard

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class DashboardRouteKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testDashboardScreen_with_Loader() {
        composeTestRule.setContent {
            DashboardScreen(
                modifier = Modifier,
                dashboardState = DashboardUIState(),
                onPostClick = { },
                onArrangementClick = {},
                onRefresh = {}
            )
        }
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.loader))
            .assertExists()
    }

    @Test
    fun test_dashboard_screen_items() {
        composeTestRule.setContent {
            composeTestRule.setContent {
                DashboardScreen(
                    modifier = Modifier,
                    dashboardState = DashboardUIState(isLoading = false),
                    onPostClick = { },
                    onArrangementClick = {},
                    onRefresh = {}
                )
            }
        }
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.loader))
            .assertDoesNotExist()
    }

}