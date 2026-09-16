package br.edu.ifsp.scl.sc3018237.intentscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
                        HomeScreen(navController)
                    }

                    composable(
                        "add_work/{textoParaEnviar}"
                    ) {
                        backStackEntry ->
                        val textoParaEnviar = backStackEntry.arguments?.getString("textoParaEnviar")?:""
                        AddWordScreen(textoParaEnviar,navController)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController){
    var stringAtual by remember {
        mutableStateOf("")
    }

    val novaPalavra by navController
        .currentBackStackEntry!!
        .savedStateHandle
        .getStateFlow("novaPalavra","")
        .collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        OutlinedTextField(
            value = stringAtual,
            onValueChange = {},
            readOnly = true,
            label ={
                Text("String atual")
            }
        )
        Button(
            onClick = {
                navController.navigate("add_work/$stringAtual")

            }
        ) {
            Text(
                text = "Adicionar Palavra",
                fontSize = 20.sp
            )

        }
    }

}

@Composable
fun AddWordScreen(textoParaEnviar: String, navController: NavController) {

    var novaPalavra by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = textoParaEnviar,
            onValueChange = {},
            readOnly = true,
            label = {
                Text("String atual")
            }
        )

        OutlinedTextField(
            value = novaPalavra,
            onValueChange = {
                novaPalavra = it
            },
            label = {
                Text("Nova palavra")
            }
        )
        Button(
            onClick = {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("novaPalavra",novaPalavra)
                navController.popBackStack() //volta pra HomeScreen
            }
        ) {
            Text(
                text = "Concatenar"
            )
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IntentsComposeTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}

