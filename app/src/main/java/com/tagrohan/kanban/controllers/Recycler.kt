package com.tagrohan.kanban.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tagrohan.kanban.databinding.CardLayoutBinding
import com.tagrohan.kanban.models.TodoData

class Recycler(todos: ArrayList<TodoData>) : RecyclerView.Adapter<Recycler.ViewHol>() {

    private val todoData = todos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHol {
        return ViewHol(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHol, position: Int) {
        holder.binding.cardTitle.text = todoData[position].title
    }

    override fun getItemCount(): Int {
        return todoData.size
    }

    class ViewHol(itemView: CardLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }

//    fun addData(title: String, desc: String) {
//        todoData.add(TodoData(title, desc))
//        notifyItemInserted(todoData.size)
//    }
}