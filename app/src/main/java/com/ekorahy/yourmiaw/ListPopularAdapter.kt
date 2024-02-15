package com.ekorahy.yourmiaw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPopularAdapter(private val listPopular: ArrayList<Popular>) :
    RecyclerView.Adapter<ListPopularAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, rating) = listPopular[position]
        holder.tvName.text = name
        holder.ivPhoto.setImageResource(photo)
        holder.tvRating.text = rating.toString()
    }

    override fun getItemCount(): Int = listPopular.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
    }
}