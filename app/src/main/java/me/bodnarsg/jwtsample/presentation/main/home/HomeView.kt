package me.bodnarsg.jwtsample.presentation.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.bodnarsg.jwtsample.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
)
{
    val user by viewModel.user.collectAsState()
    val error by viewModel.error
    val isRefreshing by viewModel.isRefresh

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Main")},
                actions = {
                    TextButton(
                        onClick = {
                            viewModel.logout()
                            navController.navigate(Routes.AUTH_GRAPH) {
                                popUpTo(Routes.MAIN_GRAPH) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Text("Logout")
                    }
            })
        }
    ){innerPadding ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { viewModel.fetch() },
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                user?.let {
                    Text(it.uuid.toString())
                    Text(it.email)
                    Text(it.name)
                }

                Text(error)
            }
        }
    }
}