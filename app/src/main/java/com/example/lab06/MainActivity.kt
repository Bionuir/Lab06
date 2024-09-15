@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab06.ui.theme.Lab06Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab06Theme {
                val navController = rememberNavController()
                var clickCount by remember { mutableStateOf(0) }
                Scaffold(
                    topBar = { CustomTopBar(navController) },
                    floatingActionButton = {
                        CustomFAB(
                            onFabClick = {
                                clickCount++ // Incrementar el contador cuando se presiona el botón
                            }
                        )
                    },
                    bottomBar = { CustomBottomBar(navController) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    CustomContent(innerPadding)
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Mostrar el número de veces que se presionó el botón
                        Text(text = "Botón presionado $clickCount veces")
                    }
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {

                        }
                        composable("profile") {
                            UserProfileScreen(navController)
                        }
                        composable("build") {
                            BuildScreen()
                        }
                        composable("menu") {
                            MenuScreen()
                        }
                        composable("favorite") {
                            FavoriteScreen()
                        }
                        composable("delete") {
                            DeleteScreen()
                        }
                    }
                }
            }
        }
    }
}


// Pantallas adicionales para la navegación

@Composable
fun BuildScreen() {
    Text(text = "Build Screen", modifier = Modifier.fillMaxSize())
}

@Composable
fun MenuScreen() {
    Text(text = "Menu Screen", modifier = Modifier.fillMaxSize())
}

@Composable
fun FavoriteScreen() {
    Text(text = "Favorite Screen", modifier = Modifier.fillMaxSize())
}

@Composable
fun DeleteScreen() {
    Text(text = "Delete Screen", modifier = Modifier.fillMaxSize())
}

// TopBar con navegación

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* Manejar clic en el menú */ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menú")
            }
        },
        title = { Text(text = "Test Nav") },
        actions = {
            IconButton(onClick = { /* Manejar clic en búsqueda */ }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Buscar"
                )
            }
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Perfil de Usuario"
                )
            }
        }
    )
}

// BottomBar con navegación

@Composable
fun CustomBottomBar(navController: NavHostController) {
    // Obtener la ruta actual
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar {
        IconButton(
            onClick = { navController.navigate("build") },
            modifier = Modifier.weight(1f),
            // Cambiar color si estamos en la ruta "build"
            colors = if (currentRoute == "build") IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            ) else IconButtonDefaults.iconButtonColors()
        ) {
            Icon(Icons.Filled.Build, contentDescription = "Build description")
        }
        IconButton(
            onClick = { navController.navigate("menu") },
            modifier = Modifier.weight(1f),
            // Cambiar color si estamos en la ruta "menu"
            colors = if (currentRoute == "menu") IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            ) else IconButtonDefaults.iconButtonColors()
        ) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu description")
        }
        IconButton(
            onClick = { navController.navigate("favorite") },
            modifier = Modifier.weight(1f),
            // Cambiar color si estamos en la ruta "favorite"
            colors = if (currentRoute == "favorite") IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            ) else IconButtonDefaults.iconButtonColors()
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite description")
        }
        IconButton(
            onClick = { navController.navigate("delete") },
            modifier = Modifier.weight(1f),
            // Cambiar color si estamos en la ruta "delete"
            colors = if (currentRoute == "delete") IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            ) else IconButtonDefaults.iconButtonColors()
        ) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete description")
        }
    }
}

// Vista de perfil

@Composable
fun UserProfileScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Perfil de Usuario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Perfil de Usuario",
                style = MaterialTheme.typography.headlineMedium
            )
            // Agrega más contenido de perfil aquí
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomBottomBar() {
    val navController = rememberNavController()
    Lab06Theme {
        CustomBottomBar(navController)
    }
}


@Composable
fun CustomFAB(onFabClick: () -> Unit) {
    FloatingActionButton(
        onClick = onFabClick // Pasar la acción de clic
    ) {
        Text(
            fontSize = 24.sp, // Tamaño de fuente del texto del botón
            text = "+" // Texto del botón
        )
    }
}

@Composable
fun CustomContent(padding: PaddingValues) {
    Column(
        // Modificadores de estilo de la columna
        modifier = Modifier
            // Ocupar todo el espacio disponible
            .fillMaxSize()
            .padding(padding),

        // Contenido de la aplicación
        content = {
            Text(text = "My app content")
        }
    )
}
