package com.example.writtingroombykunalkumar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterNotes(private val context: Context, private val listener:NotesAdaptor) : RecyclerView.Adapter<AdapterNotes.NoteViewHolder>() {
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.findViewById(R.id.textView)
        val deleteButton:ImageView= itemView.findViewById(R.id.imageView)
    }

    private val allNotes = ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notes,parent,false))

        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curNote = allNotes[position]
        holder.textView.text = curNote.text
    }
    fun update(newList:List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
    interface NotesAdaptor{
        fun onItemClicked(notes: Notes)
    }
}