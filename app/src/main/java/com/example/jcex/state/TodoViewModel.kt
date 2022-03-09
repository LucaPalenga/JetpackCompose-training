package com.example.jcex.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class TodoVewModel : ViewModel() {

    /**
     *  state
     *  live data che rappresenta un certo stato che è observed dalla ui
     *  (ViewModel -> Activity)
     */
    private var _todoItems = MutableLiveData(listOf<TodoItem>())
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    /**
     *  events
     *  eventi che possono essere invocati dalla ui
     *  (Activity -> ViewModel)
     */
    fun addItem(item: TodoItem) {
        _todoItems.value = _todoItems.value?.plus(item)
//        _todoItems.value = _todoItems.value!! + item
    }

    fun removeItem(item: TodoItem) {
        _todoItems.value = _todoItems.value?.minus(item)
//        _todoItems.value = _todoItems.value!! - item
    }
}