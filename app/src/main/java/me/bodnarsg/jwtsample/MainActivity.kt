package me.bodnarsg.jwtsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import me.bodnarsg.jwtsample.ui.theme.JwtSampleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //TODO: Change start destination to "home" after implementing authentication flow
        val startDestination = "auth"

        setContent {
            JwtSampleTheme {
                AppNavHost(startDestination = startDestination)
            }
        }
    }
}