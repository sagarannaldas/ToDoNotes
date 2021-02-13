package `in`.techrebounce.todonotes.adapter

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.clicklisteners.ItemClickListener
import `in`.techrebounce.todonotes.model.Notes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val list: List<Notes>,val itemClickListener: ItemClickListener ) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notes = list[position] // list.get(position)
        val title = notes.title
        val description = notes.description
        holder.textViewTitle.text = title
        holder.textViewDescription.text = description
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                itemClickListener.onClick(notes)
            }

        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textViewTitle : TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription : TextView =  itemView.findViewById(R.id.textViewDescription)
    }
}