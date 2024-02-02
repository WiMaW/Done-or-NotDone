package com.wioletamwrobel.doneornotdone.ui.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wioletamwrobel.doneornotdone.data.ToDo
import com.wioletamwrobel.doneornotdone.databinding.ItemTodoBinding

class ToDoAdapter(private val toDo: List<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    override fun getItemCount() = toDo.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(toDo[position])
    }


    inner class ViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo) {
            binding.textViewTitle.text = toDo.title
            binding.textViewDetails.text = toDo.description
        }
    }
}