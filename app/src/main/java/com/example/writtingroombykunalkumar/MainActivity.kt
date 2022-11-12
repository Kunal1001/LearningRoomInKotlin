package com.example.writtingroombykunalkumar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), AdapterNotes.NotesAdaptor {
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val submit:Button = findViewById(R.id.button)
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adaptor = AdapterNotes(this,this)
        recyclerView.adapter = adaptor

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this, Observer {
            it?.let { adaptor.update(it)}
        })
        submit.setOnClickListener {
            clicked()
        }

    }

    override fun onItemClicked(notes: Notes) {

        viewModel.deleteNote(notes)
        Toast.makeText(this,"${notes.text} deleted",Toast.LENGTH_SHORT).show()

    }
    private fun clicked(){
        val editText:EditText = findViewById(R.id.input)
        val textNote = editText.text.toString()
        if(textNote.isNotEmpty() || textNote.isNotBlank()){
            viewModel.inserNote(Notes(textNote))
            Toast.makeText(this,"$textNote inserted",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"note Maybe blank",Toast.LENGTH_SHORT).show()
        }
    }
}