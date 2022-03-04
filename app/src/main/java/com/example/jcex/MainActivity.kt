package com.example.jcex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //  MessageCard(Message("Ciao", "Pippo"))
            //  Conversation(msgs = SampleData.conversationSample)

            //  TextContent()

            //  JCExTheme() {
            //      ImageCard()
            //  }

            //  ComposeButton()

            //  SimpleList()
            //  LazyList()
            //  LazyScrollingList()
            CustomColumnLayoutLab()
        }
    }

}