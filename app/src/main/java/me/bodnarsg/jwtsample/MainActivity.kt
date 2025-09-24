package me.bodnarsg.jwtsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import me.bodnarsg.jwtsample.navigation.Routes
import me.bodnarsg.jwtsample.ui.theme.JwtSampleTheme
import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tokenStorageRepository: TokenStorageRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val hasAccessToken = !tokenStorageRepository.getAccessToken().isNullOrBlank()
        val startDestination = if (hasAccessToken) Routes.MAIN_GRAPH else Routes.AUTH_GRAPH

        setContent {
            JwtSampleTheme {
                AppNavHost(startDestination = startDestination)
            }
        }
    }
}