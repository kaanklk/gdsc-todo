package com.example.gdsctodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var addTodoButton : FloatingActionButton
    private lateinit var deleteTodoButton: FloatingActionButton
    private lateinit var todoEditText : EditText

    private lateinit var rvTodoItems : RecyclerView
    private lateinit var todoAdapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addTodoButton = findViewById(R.id.fabAddTodo)
        deleteTodoButton = findViewById(R.id.fabRemoveTodo)
        todoEditText = EditText(this)

        rvTodoItems = findViewById(R.id.rvTodoItems)
        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Add todo")
            .setMessage("Please write your todo")
            .setView(todoEditText)
            .setPositiveButton("Add") {_,_ ->
                val todo = Todo(todoEditText.text.toString())
                todoAdapter.addTodo(todo)
            }
            .setNegativeButton("Cancel") {_,_ ->
                Toast.makeText(this,"Operation cancelled",Toast.LENGTH_SHORT).show()
            }
            .create()

        addTodoButton.setOnClickListener {
            alertDialog.show()
        }

        deleteTodoButton.setOnClickListener {
            todoAdapter.deleteTodo()
            todoAdapter.notifyDataSetChanged()
        }

    }
}