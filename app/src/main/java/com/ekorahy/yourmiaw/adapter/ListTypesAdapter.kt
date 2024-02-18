package com.ekorahy.yourmiaw.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ekorahy.yourmiaw.databinding.ItemTypesBinding
import com.ekorahy.yourmiaw.model.types.Types
import com.ekorahy.yourmiaw.view.detail.category.DetailCategoryActivity
import com.ekorahy.yourmiaw.view.detail.category.DetailCategoryActivity.Companion.EXTRA_DATA_CATEGORY

class ListTypesAdapter(private val listTypes: ArrayList<Types>) :
    RecyclerView.Adapter<ListTypesAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTypesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, desc) = listTypes[position]
        holder.binding.tvName.text = name
        Glide.with(holder.itemView.context)
            .load(photo)
            .circleCrop()
            .into(holder.binding.ivPhoto)
        holder.itemView.setOnClickListener {
            val category = Types(
                name,
                photo,
                desc
            )
            val intent = Intent(holder.itemView.context, DetailCategoryActivity::class.java)
            intent.putExtra(EXTRA_DATA_CATEGORY, category)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listTypes.size

    class ListViewHolder(var binding: ItemTypesBinding) : RecyclerView.ViewHolder(binding.root)

}