package com.ekorahy.yourmiaw.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ekorahy.yourmiaw.R
import com.ekorahy.yourmiaw.model.types.Types
import com.ekorahy.yourmiaw.view.detail.category.DetailCategoryActivity
import com.ekorahy.yourmiaw.view.detail.category.DetailCategoryActivity.Companion.DATA_CATEGORY

class ListTypesAdapter(private val listTypes: ArrayList<Types>): RecyclerView.Adapter<ListTypesAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_types, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, desc) = listTypes[position]
        holder.tvName.text = name
        Glide.with(holder.itemView.context)
            .load(photo)
            .circleCrop()
            .into(holder.ivPhoto)
        holder.itemView.setOnClickListener {
            val category = Types(
                name,
                photo,
                desc
            )
            val intent = Intent(holder.itemView.context, DetailCategoryActivity::class.java)
            intent.putExtra(DATA_CATEGORY, category)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listTypes.size

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
    }

}