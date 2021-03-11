package com.tagrohan.kanban.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tagrohan.kanban.models.TodoData

class SharedViewModel : ViewModel() {

    companion object {
        private const val TAG = "SharedViewModel"
    }

    init {
        Log.d(TAG, ": init called ")
    }


    val idea: MutableLiveData<ArrayList<TodoData>> = MutableLiveData()
    val todo: MutableLiveData<ArrayList<TodoData>> = MutableLiveData()
    val doing: MutableLiveData<ArrayList<TodoData>> = MutableLiveData()
    val done: MutableLiveData<ArrayList<TodoData>> = MutableLiveData()

    private val todoList = ArrayList<TodoData>()
    private val ideaList = ArrayList<TodoData>()
    private val doingList = ArrayList<TodoData>()
    private val doneList = ArrayList<TodoData>()

//    fun addInShared(item: TodoData) {
//        todoList.add(item)
//        todo.value = todoList
//        stageChecking(item)
//    }

    fun stageChecking(item: TodoData) {
        when (item.stage) {
            0 -> {
                ideaList.add(item)
                idea.value = ideaList
            }
            1 -> {
                todoList.add(item)
                todo.value = todoList
            }
            2 -> {
                doingList.add(item)
                doing.value = doingList
            }
            3 -> {
                doneList.add(item)
                done.value = doneList
            }
        }
    }
}