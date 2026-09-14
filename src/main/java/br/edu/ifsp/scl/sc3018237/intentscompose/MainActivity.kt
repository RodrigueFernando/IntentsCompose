package br.edu.ifsp.scl.sc3018237.intentscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3018237.intentscompose.ui.theme.IntentsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination =  "home"
                ){
                    composable("home") {
                        HomeScreen()
                    }

                    composable("add_work") {
                        AddWordScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(){
    Text("Home")
}

@Composable
fun AddWordScreen(){
    Text("Adicionar Palavra")
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentsComposeTheme {
        Greeting("Android")
    }
}

 */