package com.example.viewtocomposetheming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jcex.R
import com.example.jcex.databinding.FragmentSecondBinding
import com.google.android.material.composethemeadapter.MdcTheme

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        context?.theme?.applyStyle(R.style.Theme_ViewToComposeTheming2, true)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
//        val bind = DataBindingUtil.inflate<FragmentSecondBinding>(
//            inflater,
//            R.layout.fragment_second,
//            container,
//            false
//        )
            .apply {
                composeView.setContent {
//                    MdcTheme {
//                    Theme2 {
//                    CustomTheme {

//                    MyMdcTheme() {
                    SecondFragmentComposed()
//                    }

                }
            }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Composable
    fun SecondFragmentComposed() {
        var theme2Enabled by remember {
            mutableStateOf(false)
        }

        val onThemeChanged = { theme2Enabled = !theme2Enabled }

        if (theme2Enabled) {
            Theme2 {
                Scaffold(topBar = { SecondFragmentTopBar() }) {
                    SecondFragmentContent(onThemeChanged = onThemeChanged)
                }
            }
        } else {
            MdcTheme() {
                Scaffold(topBar = { SecondFragmentTopBar() }) {
                    SecondFragmentContent(onThemeChanged = onThemeChanged)
                }
            }
        }
    }

    @Composable
    fun SecondFragmentTopBar() {
        TopAppBar(
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            title = { Text(text = "Top bar") })
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SecondFragmentContent(
        onThemeChanged: () -> Unit,
//        onColorAdded: (String) -> Unit
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Column() {
                    Text(
                        text = "MaterialTheme primary = " + MaterialTheme.colors.primary.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Card(
                    onClick = { onThemeChanged() },
                    /**
                     * Sto usando il primary del MateruakTheme quindi del tema
                     * corrente!
                     */
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Text(
                        text = "Change theme",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.weight(1f)
//            ) {
//                TextInput(onColorAdded = onColorAdded())
//            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                /**
                 * Posso usare un tema diverso per ogni singolo componente
                 */
                Theme2() {
                    Button(onClick = { findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment) }) {
                        Text(text = stringResource(R.string.previous))
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun TextInput(onColorAdded: (String, String, String) -> Unit) {
        val red = remember { mutableStateOf("") }
        val green = remember { mutableStateOf("") }
        val blue = remember { mutableStateOf("") }

        Row() {

            TextField(value = red.value, onValueChange = { })
            TextField(value = green.value, onValueChange = { })
            TextField(value = blue.value, onValueChange = { })
            Card(
                onClick = { onColorAdded(red.value, green.value, blue.value) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Text(
                    text = "Change primaryColor of current theme",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }


    @Preview
    @Composable
    fun SecondFragmentContentPreview() {
//        Theme2() {
        MdcTheme() {
            SecondFragmentComposed()
        }
//        }
    }
}