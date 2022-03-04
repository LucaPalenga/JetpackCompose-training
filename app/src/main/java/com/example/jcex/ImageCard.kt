package com.example.jcex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcex.ui.theme.JCExTheme


@Composable
fun ImageCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {})
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cairoli),
                contentDescription = "cairoli"
            )
        }
        Spacer(Modifier.width(4.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = "Ciao",
                fontWeight = FontWeight.Bold
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "description",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
fun ImageCardPreview() {
    JCExTheme() {
        ImageCard()
    }
}
