package `in`.techrebounce.todonotes.adapter

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.data.remote.model.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BlogAdapter(val list: List<Data>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = list[position]
        holder.textViewTitle.setText(blog.title)
        holder.textViewDescription.setText(blog.description)
        Glide.with(holder.itemView).load(blog.img_url).into(holder.imageview)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val imageview: ImageView = itemView.findViewById(R.id.imageView)
    }
}