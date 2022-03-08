package com.example.jcex.state

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInputTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit,
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

    val keyboardController = LocalSoftwareKeyboardController.current
    val imeActionVisible = text.isNotBlank()

    TextField(
        value = text, onValueChange = onTextChange,
        //keyboardOptions -> Mostra il Done IME Action
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (imeActionVisible) {
                ImeAction.Done
            } else {
                ImeAction.None
            }
        ),
        //keyboardActions -> Azione da eseguire su specifica IME action
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        maxLines = 1, modifier = modifier
    )
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
    val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }
    val iconVisible = text.isNotBlank()

    // creo una funzione submit per poterla usare sia all'onClick del bottone, sia come ImeAction
    val submit = {
        onItemComplete(TodoItem(text, icon)) // EVENT UP
        setIcon(TodoIcon.Default)
        setText("") // azzero il textfield
    }

    Column(Modifier.padding(horizontal = 16.dp)) {
        Row(
            Modifier.padding(top = 16.dp)
        ) {
            TodoInputTextField(
                text = text,
                onTextChange = setText,
                onImeAction = submit,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            TodoEditButton(
                onClick = submit,
                text = "Add",
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotBlank()
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            /**
             * There is no "visibility" property in compose.
             *  Since compose can dynamically change the composition, you do not need to set visibility gone.
             *  Instead, remove composables from the composition.
             */
            if (iconVisible) {
                //anche qui state hoisting, passo le variabili di stato e un evento con cui poter richiedere di modificarle al figlio
                IconRow(todoIcon = icon, onIconSelected = setIcon)
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun IconRow(
    todoIcon: TodoIcon,
    onIconSelected: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier
) {
    Row() {
        for (icon in TodoIcon.values()) {
            SelectableIconButton(
                iconImageVector = icon.imageVector,
                iconContentDescription = icon.contentDescription,
                onIconSelected = { onIconSelected(icon) },
                isSelected = icon == todoIcon
            )
        }
    }
}

@Composable
fun SelectableIconButton(
    iconImageVector: ImageVector,
    @StringRes iconContentDescription: Int,
    onIconSelected: () -> Unit,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    val tint = if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    TextButton(onClick = onIconSelected) {
        Icon(
            imageVector = iconImageVector,
            tint = tint,
            contentDescription = stringResource(id = iconContentDescription)
        )
    }
}

@Preview
@Composable
fun TodoItemInputPreview() {
    TodoItemInput(onItemComplete = {})
}