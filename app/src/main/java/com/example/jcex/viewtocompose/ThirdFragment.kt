package com.example.jcex.viewtocompose

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jcex.R
import com.example.jcex.databinding.FragmentThirdBinding
import com.google.android.material.composethemeadapter.MdcTheme


class ThirdFragment : Fragment() {

    private lateinit var _binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
            .apply {
                composeView.setContent {
                    MdcTheme {
                        ThirdFragContent()
                    }
                }
            }
        return _binding.root
    }


    @Composable
    fun ThirdFragContent() {
        var styleApplied by remember { mutableStateOf(false) }

        if (styleApplied) {
            LocalContext.current.theme.applyStyle(R.style.Theme_ViewToComposeTheming3, true)
        }
//        else {
//            LocalContext.current.theme.applyStyle(R.style.Theme_ViewToComposeTheming, true)
//        }

        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth()) {
                Button(onClick = { styleApplied = !styleApplied }) {
                    Text(text = "applyStyleTheme3")
                }
                Text(modifier = Modifier.padding(8.dp), text = "Typography")
                Card() {
                    Text(modifier = Modifier.padding(16.dp), text = "Shape")
                }
            }
            TextButton(onClick = { findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment) }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "prev")
                Text(text = stringResource(id = R.string.previous))
            }
        }
    }

    @Preview
    @Composable
    fun ThirdFragPreview() {
        MdcTheme() {
            ThirdFragContent()
        }
    }
}