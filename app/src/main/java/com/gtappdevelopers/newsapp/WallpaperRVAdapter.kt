package com.gtappdevelopers.newsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class WallpaperRVAdapter(private val wallpaperList: List<String>, private val context: Context) :
    RecyclerView.Adapter<WallpaperRVAdapter.NewsRVItemViewHolder>() {


    class NewsRVItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wallpaperIV: ImageView = itemView.findViewById(R.id.idIVWallpaper)
        val wallpaperCV: CardView = itemView.findViewById(R.id.idCVWallpaper)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRVItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.wallpaper_rv_item,
            parent, false
        )
        return NewsRVItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsRVItemViewHolder, position: Int) {
        val wallpaperItem = wallpaperList[position]
        Picasso.get().load(wallpaperItem).into(holder.wallpaperIV)
        holder.wallpaperCV.setOnClickListener {
            val intent = Intent(context, WallpaperActivity::class.java);
            intent.putExtra("imgUrl", wallpaperList.get(position));
            context.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }


}