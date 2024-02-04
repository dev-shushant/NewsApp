package dev.shushant.newsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.shushant.dashboard.DashboardRoute
import dev.shushant.designsystem.theme.NewsAppTheme
import dev.shushant.newsapp.R

/**
 * @[MainActivity] for the NewsApp. This activity serves as the main entry point for the application.
 * It hosts the entire application's UI and functionality, including the navigation, top app bar,
 * and the main dashboard content.
 *
 */

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created. It sets up the splash screen, initializes the
     * content view, and configures the overall application theme.
     *
     * @param savedInstanceState The saved state of the activity (if any).
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen for a smooth launch experience
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT,
                )
            )
            NewsAppTheme {
                // Initialize SnackBarHostState to manage SnackBars
                val snackBarHostState = remember { SnackbarHostState() }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    // Create a centered TopAppBar with the app name and icon
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = stringResource(id = R.string.app_name))
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                        contentDescription = stringResource(id = R.string.app_name),
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                        )
                    }) { padding ->
                    // Display the main dashboard content using DashboardRoute composable

                    DashboardRoute(modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                        onShowSnackBar = { message, actionText ->
                            snackBarHostState.showSnackbar(message, actionText)
                        })
                }
            }
        }
    }
}