package com.example.jcex.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TodoInputTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
//fun TodoInputTextField(modifier: Modifier = Modifier) {
    /**
     * You declare a MutableState object in a composable three ways:
     * val state = remember { mutableStateOf(default) }
     * var value by remember { mutableStateOf(default) }
     * val (value, setValue) = remember { mutableStateOf(default) }
     *
     * NB: MutableState<T> similar to MutableLiveData<T>, but integrated with the compose runtime.
     * Since it's observable, it will tell compose whenever it's updated so compose can recompose any composables that read it.
     */
    //  val (text, setText) = remember { mutableStateOf("") }

    /**
     *  Se voglio aggiungere un TodoItem dal click del button
     *  adesso non posso farlo perchè non ho accesso al text dato che si trova qui nel
     *  TodoInputTextField!
     *  Dato che l'alberatura di questa composition è la sequente:
     *  TodoItemInput
     *      |_ TodoInputTextField (text,setText)
     *      |_ TodoEditButton
     *
     *  Applico lo STATE HOISTING su questo stato, ciò significa che va portato
     *  su nell'albero (al parent) rendendo questo componente stateless.
     *  Quindi diventerà:
     *  TodoItemInput (text,setText)
     *      |_ TodoInputTextField
     *      |_ TodoEditButton
     */

    TextField(value = text, onValueChange = onTextChange, modifier = modifier)
}

@Composable
fun TodoEditButton(
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.clip(CircleShape),
        enabled = enabled
    ) {
        Text(text = text)
    }
}

/**
 * State hoisting is a pattern of moving state up to make a component stateless.
 *   When applied to composables, this often means introducing two parameters to the composable.
 *      value: T – the current value to display
 *     onValueChange: (T) -> Unit – an event that requests the value to change
 *
 *  Vantaggi che si hanno applicando lo state hoisting:
 *  - Solo TodoItemInput può modificare text, ma gli altri composable possono inviare eventi
 *  - Lo stato "hoisted" può essere condiviso come valore immutabile con tutti i child di TodoItemInput
 *  - TodoInputTextField, essendo ora stateless, può essere usato ovunque senza essere modificato
 */

@Composable
fun TodoItemInput(onItemComplete: (TodoItem) -> Unit) {
    // Sposto qui le variabili di stato
    val (text, setText) = remember { mutableStateOf("") }

    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(vertical = 16.dp)
        ) {
            TodoInputTextField(
                text = text,
                onTextChange = setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            TodoEditButton(
                onClick = {
                    onItemComplete(TodoItem(text)) // EVENT UP
                    setText("") // azzero il textfield
                },
                text = "Add",
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotBlank()
            )
        }
    }
}

@Preview
@Composable
fun TodoItemInputPreview() {
    TodoItemInput(onItemComplete = {})
}