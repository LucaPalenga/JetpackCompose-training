package com.example.jcex.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.jcex.ui.theme.JCExTheme


/**
 *  Composse & ViewModel
 */

class ActivityWithViewModel : ComponentActivity() {

    private val viewModel by viewModels<TodoVewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JCExTheme() {
                Surface() {
                    TodoActivityScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
private fun TodoActivityScreen(viewModel: TodoVewModel) {

    /**
     * observeAsState -> "osserva" il LiveData<T> convertendolo in uno State<T>
     * in modo tale che Compose può reagire ai cambiamenti di quei valori
     *
     * listOf() -> gestisce il null e mi permette di non dover usare il ? ogni volta
     * che andrò a richiamare items
     *
     * by -> permette di unwrappare automaticamente State<T> da observeAsState
     * in un T (in questo caso State<List<TodoItem>> in List<TodoItem>)
     */
    val items: List<TodoItem> by viewModel.todoItems.observeAsState(listOf())
    //  val items: State<List<TodoItem>> = viewModel.todoItems.observeAsState(listOf())

    /**
     * Al componente passo l'oggetto e gli eventi del ViewModel, perchè è il ViewModel che
     * si occuperà di modificare i dati e gestire gli eventi (quindi di gestire lo stato),
     * l'oggetto composable (TodoScreen) si occuperà soltanto di visualizzarne lo STATO e inviare
     * delle richieste tramite EVENTI al ViewModel
     *
     * Composable --evento-> ViewModel
     * ViewModel --stato-> Composable
     */
    TodoScreen(
        items = items,
//        onAddItem = { viewModel.addItem(it) },
        onAddItem = viewModel::addItem,     //Altro modo di scrivere una lambda che chiama un metodo
        onRemoveItem = { viewModel.removeItem(it) })
}
