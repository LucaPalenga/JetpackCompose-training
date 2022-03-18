package com.example.jcex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.jcex.accessibility.JetnewsApplication
import com.example.jcex.accessibility.data.ui.JetnewsApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // region dynamic theme

            //  DynamicTheme(colorProvider = ColorProvider(appColorScheme)) {
            //      MessageCard(Message("Ciao", "Pippo"))
            //      Conversation(msgs = SampleData.conversationSample)
            //  }

            //endregion dynamictheme

            // region components

            //  MessageCard(Message("Ciao", "Pippo"))
            //  Conversation(msgs = SampleData.conversationSample)
//                TextContent()

//                JCExTheme() {
//                    ImageCard()
//                }

//                ComposeButton()

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

            // region animations

//            AnimationCodelabTheme {
//                HomeAnimations()
//            }

            // endregion animations

            // region accessibility

            WindowCompat.setDecorFitsSystemWindows(window, false)

            val appContainer = (application as JetnewsApplication).container
            setContent {
                JetnewsApp(appContainer)
            }

            // endregion accessibility
        }
    }
}