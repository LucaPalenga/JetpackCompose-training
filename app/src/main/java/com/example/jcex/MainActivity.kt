package com.example.jcex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // region components

            //  MessageCard(Message("Ciao", "Pippo"))
            //  Conversation(msgs = SampleData.conversationSample)

            //  TextContent()

            //  JCExTheme() {
            //      ImageCard()
            //  }

            //  ComposeButton()

            // endregion components

            // region layouts

            //  SimpleList()
            //  LazyList()
            //  LazyScrollingList()
            //  CustomColumnLayoutLab()
            //  StraggeredGridLab()
            //  StraggeredGridWithCustomModifierLab()
            //  ConstraintLayoutContent()
            //  DecoupledConstraintLayout()
            //  TwoTexts(text1 = "Hi", text2 = "there")

            // endregion layouts


        }
    }

}