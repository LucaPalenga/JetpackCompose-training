package com.example.jcex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// region State&Compose

@Composable
fun TextContent() {
    /**
     * remember -> preserva lo stato della variabile nelle ricomposizioni,
     * ma NON durante i cambiamenti di configurazione (tipo cambio tema)
     */
    //  var name: String by remember { mutableStateOf("") }

    /**
     * rememberSaveable -> preserva lo stato della variabile anche durante
     * i cambiamenti di configurazione perchè salva lo stato in un bundle
     */
    var name: String by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Ciao $name",
            style = MaterialTheme.typography.h5
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Your name") }
        )
    }
}

// region state hoisting
/**
 * Lo State Hoisting è un pattern che rende più riusabile e testabile un composable
 */

//in questo caso
// lo stato (name) va in TextContent2
// l'evento (onNameChanged) va su in TextScreen
@Composable
fun TextScreen() {
    var name: String by rememberSaveable { mutableStateOf("") }
    TextContent2(name = name, onNameChanged = { name = it })
}

//modificato aggiungendo in input
// name - parametro immutabile da rappresentare
// lambda - evento chiamato quando lo stato di name cambia
@Composable
fun TextContent2(name: String, onNameChanged: (String) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Ciao $name",
            style = MaterialTheme.typography.h5
        )

        OutlinedTextField(
            value = name,
            onValueChange = onNameChanged,
            label = { Text(text = "Your name") }
        )
    }
}

// endregion state hoisting

// region ViewModel

//TODO
//    class HelloViewModel : ViewModel() {
//        private val _name: MutableLiveData<String!> = MutableLiveData("")
//        val name: LiveData<String> = _name
//
//    }
//
//    @Composable
//    fun HelloScreen(helloViewModel: HelloViewModel = viewModel()) {
//        val name:String by helloViewModel.name.observeAsState("")
//
//    }

// endregion ViewModel

// endregion State&Compose
