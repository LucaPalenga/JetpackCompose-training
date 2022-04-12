package com.example.jcex.drawermenu

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jcex.ViewToComposeActivity
import com.example.jcex.layouts.padding
import com.example.jcex.material3.Material3Activity
import com.example.jcex.navigation.RallyActivity

enum class DrawerItem(val label: String, val intentTo: Activity) {
    ViewToCompose("View To Compose", intentTo = ViewToComposeActivity()),
    Rally("Rally Ativity", intentTo = RallyActivity()),
    Material3DynamicTheme("Material 3 Dynamic Theme", intentTo = Material3Activity())
}

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Apps,
            contentDescription = "",
            tint = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Drawer menu",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun DrawerBody(modifier: Modifier = Modifier, onItemClick: (DrawerItem) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(DrawerItem.values()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(it) }
                .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                Text(text = it.label)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 8.dp)
                    .background(color = MaterialTheme.colors.primary)
            )
        }
    }
}
