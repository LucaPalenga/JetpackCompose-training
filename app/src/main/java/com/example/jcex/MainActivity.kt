package com.example.jcex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //  MessageCard(Message("Ciao", "Pippo"))
            //  Conversation(msgs = SampleData.conversationSample)

            TextContent()
        }
    }

    // region State&Compose

    @Composable
    fun TextContent() {
        //remember -> preserva lo stato della variabile nelle ricomposizioni,
        // ma NON durante i cambiamenti di configurazione (tipo cambio tema)
        //  var name: String by remember { mutableStateOf("") }

        //rememberSaveable -> preserva lo stato della variabile anche durante
        // i cambiamenti di configurazione perchè salva lo stato in un bundle
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
    //  pattern che rende più riusabile un composable

    @Composable
    fun TextScreen() {
        var name: String by rememberSaveable { mutableStateOf("") }
        TextContent2(name = name, onNameChanged = { name = it })

        //in questo caso
        // lo stato (name) va in TextContent2
        // l'evento (onNameChanged) va su in TextScreen
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

    // region Conversation

    @Composable
    private fun Conversation(msgs: List<Message>) {
        LazyColumn() {
            items(msgs.size) { index ->
                MessageCard(msg = msgs[index])
            }
        }
    }

    //    @Preview
    @Composable
    fun PreviewConversation() {
        Conversation(msgs = SampleData.conversationSample)
    }

    // endregion Conversation

    // region Message

    class Message(val title: String, val body: String)

    @Composable
    private fun MessageCard(msg: Message) {
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            targetValue =
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
        )

        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "immagine",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.title,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h4
                )
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 2.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize()
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.body1,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
                    )
                }
            }
        }
    }

    //    @Preview("Light Mode")
//    @Preview(
//        uiMode = Configuration.UI_MODE_NIGHT_YES,
//        showBackground = true,
//        name = "Dark Mode"
//    )
    @Composable
    private fun PreviewMessageCard() {
        MessageCard(
            Message(
                "Titolo",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
            )
        )
    }

    // endregion Message


}