package com.example.assessment_pvdt_bangalore

import LaunchView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assessment_pvdt_bangalore.ui.theme.Assessment_pvdt_bangaloreTheme
import com.example.assessment_pvdt_bangalore.util.ConstantsValues
import com.example.assessment_pvdt_bangalore.viewmodel.PvavdtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assessment_pvdt_bangaloreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHost = rememberNavController()
                    val viewModel :PvavdtViewModel = hiltViewModel()
                    NavHost(navController = navHost, startDestination = "launchScreen") {
                        composable(route = "launchScreen") {
                            viewModel.loadApiContents()
                             LaunchView(viemodel = viewModel)
                        }
                    }
                }
            }
        }
    }
}



