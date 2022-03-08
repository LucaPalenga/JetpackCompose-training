package com.example.jcex.state

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcex.layouts.padding


/**
 * componente stateless (tecnica dello state hoisting)
 * il composable non modifica il dato (lo stato), ma può
 * soltanto inviare delle richieste (eventi) che verranno gestiti
 * da un oggetto apposito (ViewMode)
 *
 * @param items lista dei TodoItem da visualizzare
 * @param onAddItem evento aggiunta elemento
 * @param onRemoveItem evento rimozione elemento
 */
@Composable
fun TodoScreen(
    items: List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit
) {
    Column() {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items) {
                TodoRow(
                    todoItem = it,
                    onItemClicked = { onRemoveItem(it) },
                    modifier = Modifier.fillParentMaxWidth()
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onAddItem(generateRandomTodoItem()) }) {
            Text(text = "Add new random item")
        }
    }
}


/**
 * Considerazioni su Remember:
 * utilizzare remember in una LazyColumn
 * (o un qualsiasi altro oggetto di questo tipo che si comporta come una recyclerview
 * rimuovendo gli oggetti quando non sono più visibili a schermo)
 * è rischioso, infatti i valori salvati da remember per quel composable verranno
 * dimenticati quando quel composable verrà rimosso!

 * OSS: per provarlo basta aggiungere abbastanza rows e scrollare in basso facendo
 * sparire le prime rows e poi tornare all'inizio, vedrai che le rows scomparse
 * (cioè quelle che sono state ricreate) avranno icon alpha diverso da quello iniziale
 */
@Composable
fun TodoRow(
    todoItem: TodoItem,
    onItemClicked: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
    //è anche possibile spostarlo nei parametri in modo tale che il chiamante possa controllarlo
    iconAlpha: Float = remember(todoItem.id) { randomTint() }
) {
    //remember mi permette di memorizzare una funzione associandole una key
    //  val iconAlpha: Float = remember(todoItem.id) { randomTint() }

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onItemClicked(todoItem) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = todoItem.task)
        Icon(
            imageVector = todoItem.icon.imageVector,
            contentDescription = stringResource(id = todoItem.icon.contentDescription),

            /**
             *  Causa un cambiamento di alpha delle icone ogni volta che la lista cambia!
             *  Questo a causa della RECOMPOSITION la quale riesegue tutto l'albero dei
             *  composable da TodoScreen perchè i dati sono cambiati. A sua volta
             *  TodoScreen eseguirà i suoi TodoRows generando nuovi randomTint.

             *  NB: Durante la recomposition vengono aggiornati solo i composable che
             *  sono stati modificati (in questo caso TodoScreen e TodoRow perchè dipendono
             *  dalla lista di TodoItems) e non l'intero albero
             */
            //  tint = LocalContentColor.current.copy(alpha = randomTint())

            /**
             * un valore calcolato con REMEMBER viene salvato nell'albero della composition
             * e viene ricalcolato solo se la sua key cambia (in questo caso todoItem.id)
             * OSS: puoi associare il comportamento di remember a quello di una property
             * private val di un oggetto
             */
            tint = LocalContentColor.current.copy(alpha = iconAlpha)
        )
    }
}


//@Preview
//@Composable
//fun TodoRowPreview() {
//    TodoRow(todoItem = generateRandomTodoItem(), onItemClicked = {})
//}

@Preview
@Composable
fun TodoScreenPreview() {
    val items = listOf(
        generateRandomTodoItem(),
        generateRandomTodoItem(),
        generateRandomTodoItem(),
        generateRandomTodoItem()
    )
    TodoScreen(items = items, onAddItem = {}, onRemoveItem = {})
}