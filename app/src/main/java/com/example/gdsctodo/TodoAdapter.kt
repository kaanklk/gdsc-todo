package com.example.gdsctodo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val todoList : MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)

    private lateinit var tvTodoTitle : TextView
    private lateinit var cbDone : CheckBox

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_todo,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        tvTodoTitle = holder.itemView.findViewById(R.id.tvTodoTitle)
        cbDone = holder.itemView.findViewById(R.id.cbDone)

        val curTodo = todoList[position]

        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked

            toggleStrikeThrogh(tvTodoTitle,curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrogh(tvTodoTitle,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }

        }

    }

    private fun toggleStrikeThrogh(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun addTodo(todo : Todo) {
        todoList.add(todo)
        notifyItemInserted(todoList.size - 1 )
    }

    fun deleteTodo() {
        todoList.removeAll {
            todo -> todo.isChecked
        }
    }
}