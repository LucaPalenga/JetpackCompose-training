package com.example.jcex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jcex.ui.theme.ColorProvider
import com.example.jcex.ui.theme.DynamicTheme
import com.example.jcex.ui.theme.appColorScheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicTheme(colorProvider = ColorProvider(appColorScheme)) {
//                MessageCard(Message("Ciao", "Pippo"))
                Conversation(msgs = SampleData.conversationSample)

//                TextContent()

//                JCExTheme() {
//                    ImageCard()
//                }

//                ComposeButton()

//                SimpleList()
//                LazyList()
//                LazyScrollingList()
//                CustomColumnLayoutLab()
            }
        }
    }
}