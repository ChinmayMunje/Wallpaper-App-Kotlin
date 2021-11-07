package com.gtappdevelopers.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoryRVAdapter(
    private val categoryList: List<CategoriesRVModal>,
    private val context: Context,
    private val categoryClickInterface: CategoryClickInterface
) :
    RecyclerView.Adapter<CategoryRVAdapter.CategoryItemViewHolder>() {

    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryIV: ImageView = itemView.findViewById(R.id.idIVCategory)
        val categoryTV: TextView = itemView.findViewById(R.id.idTVCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.category_rv_item,
            parent, false
        )
        return CategoryRVAdapter.CategoryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val categoryItem = categoryList[position]
        holder.categoryTV.text = categoryItem.categoryName
        Picasso.get().load(categoryItem.categoryImage).placeholder(R.drawable.ic_launcher_background)
            .into(holder.categoryIV)
        holder.itemView.setOnClickListener {
            categoryClickInterface.onCategoryClick(position)
        }
    }

    override fun getItemCount(): Int {
        return  categoryList.size
    }
}

interface CategoryClickInterface {
    fun onCategoryClick(position: Int)
}
