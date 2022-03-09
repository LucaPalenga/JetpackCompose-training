package com.example.jcex.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class TodoVewModel : ViewModel() {

    /**
     *  state
     *  live data che rappresenta un certo stato che è observed dalla ui
     *  (ViewModel -> Activity)
     */
    //  private var _todoItems = MutableLiveData(listOf<TodoItem>())
    //  val todoItems: LiveData<List<TodoItem>> = _todoItems

    /**
     *  Si può semplificare il codice utilizzando mutableStateListOf invece di LiveData.
     *  mutableStateListOf permette di create un'istanza di MutableList (che è observable)
     *  facilitando il lavoro rispetto al LiveData.
     *  OSS: guarda come si semplificano i metodi di evento
     *  NB: mutableStateListOf (e MutableState) sono previsti solo per Compose! Se questo viewmodel
     *  deve essere usato anche per le classiche View, per quest'ultime è meglio continuare ad
     *  utilizzare LiveData
     */
    var todoItems = mutableStateListOf<TodoItem>()
        private set // <- Indica che il setter è privato, quindi visibile solo in questa classe

    /**
     *  Un composable che userà currentEditItem osserverà automaticamente i cambiamenti di TodoItems
     *  e currentEditPosition, questo perchè sono entrambi degli State<T>
     *  NB: Se avessimo definito così currentEditPosition = -1
     *  allora i composables non avrebbero potuto osservarne i cambiamenti
     */
    private var currentEditPosition by mutableStateOf(-1)
    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditPosition)


    fun onEditItemSelected(item: TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    fun onEditItemChange(item: TodoItem) {
        require(requireNotNull(currentEditItem).id == item.id) {

        }
        todoItems[currentEditPosition] = item
    }

    /**
     *  events
     *  eventi che possono essere invocati dalla ui
     *  (Activity -> ViewModel)
     */
    fun addItem(item: TodoItem) {
        //  _todoItems.value = _todoItems.value?.plus(item)
        //  _todoItems.value = _todoItems.value!! + item
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
        //  _todoItems.value = _todoItems.value?.minus(item)
        //  _todoItems.value = _todoItems.value!! - item
        todoItems.remove(item)
        onEditDone()
    }
}