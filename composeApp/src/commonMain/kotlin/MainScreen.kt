import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.MainIntent

@Composable
fun MyApp(viewModel: MainViewModel) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                is NavigationEvent.FirstPage -> {
                    navController.navigate("first")
                }
                is NavigationEvent.SecondPage -> {
                    navController.navigate("second")
                }
            }
        }
    }

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(viewModel) }
        composable("first") { FirstPage() }
        composable("second") { SecondPage() }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.handleIntent(MainIntent2.ClickFirstPage) }) {
            Text("Go to First Page")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.handleIntent(MainIntent2.ClickSecondPage) }) {
            Text("Go to Second Page")
        }
    }
}

@Composable
fun FirstPage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("This is the First Page", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun SecondPage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("This is the Second Page", style = MaterialTheme.typography.h4)
    }
}