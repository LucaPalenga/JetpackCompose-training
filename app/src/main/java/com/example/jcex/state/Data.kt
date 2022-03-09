package com.example.jcex.state

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jcex.R
import java.util.*
import kotlin.random.Random.Default.nextFloat

data class TodoItem(
    val task: String,
    val icon: TodoIcon = TodoIcon.Default,
    // since the user may generate identical tasks, give them each a unique ID
    val id: UUID = UUID.randomUUID()
)

enum class TodoIcon(val imageVector: ImageVector, @StringRes val contentDescription: Int) {
    Square(Icons.Default.Home, R.string.cd_expand),
    Done(Icons.Default.Done, R.string.cd_done),
    Event(Icons.Default.KeyboardArrowUp, R.string.cd_event),
    Privacy(Icons.Default.Person, R.string.cd_privacy),
    Trash(Icons.Default.Delete, R.string.cd_restore);

    companion object {
        val Default = Square
    }
}

val todoItemMessages = listOf(
    "Learn compose",
    "Learn state",
    "Build dynamic UIs",
    "Learn Unidirectional Data Flow",
    "Integrate LiveData",
    "Integrate ViewModel",
    "Remember to savedState!",
    "Build stateless composables",
    "Use state from stateless composables"
)

fun generateRandomTodoItem(): TodoItem {
    val message = todoItemMessages.random()
    val icon = TodoIcon.values().random()
    return TodoItem(message, icon)
}

fun randomTint(): Float {
    return nextFloat().coerceIn(0.3f, 0.9f)
}
