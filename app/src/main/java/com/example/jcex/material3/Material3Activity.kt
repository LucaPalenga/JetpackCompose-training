package com.example.jcex.material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcex.R
import com.example.jcex.layouts.padding
import com.example.jcex.ui.theme.Material3DynamicTheme


class Material3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3DynamicTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MyAppScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppScreen() {
    androidx.compose.material.Scaffold() {

    }
    Scaffold(
        topBar = { MyTopAppBar() },
//        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painterResource(id = R.drawable.cairoli), contentDescription = "img")
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Text Button",
//                    style = AppTypography.bodyLarge.copy(MaterialTheme.colorScheme.onTertiaryContainer)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.material3.Button(
                modifier = Modifier.padding(8.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Ciao", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}


@Composable
fun MyTopAppBar() {
    //TopAppBar is not material3!
//    TopAppBar(
//        modifier = Modifier
//            .fillMaxWidth(),
////        backgroundColor = MaterialTheme.colorScheme.primaryContainer
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
//        ) {
//            Icon(Icons.Default.Phone, tint = MaterialTheme.colorScheme.tertiary, contentDescription = null, modifier = Modifier.size(40.dp))
//            Spacer(Modifier.width(16.dp))
//            Text(
//                text = "Pronto ?",
////                style = AppTypography.titleLarge.copy(
////                    MaterialTheme.colorScheme.onPrimaryContainer
////                ),
//                modifier = Modifier.weight(1f)
//            )
//        }
//    }
//    MediumTopAppBar(
//        navigationIcon = {Icon(
//            painterResource(id = R.drawable.heart),
//            contentDescription = null
//        )},
//    )
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.Phone,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = "Pronto ?",
//                style = AppTypography.titleLarge.copy(
//                    MaterialTheme.colorScheme.onPrimaryContainer
//                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun MyTopBarPreview() {
    Material3DynamicTheme() {
        MyTopAppBar()
    }
}

@Preview
@Composable
fun ScreenPreview() {
    Material3DynamicTheme {
        MyAppScreen()
    }
}