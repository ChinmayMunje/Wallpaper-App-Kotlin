package com.gtappdevelopers.newsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class LibraryRVAdapter(
    private val libraryList: List<LibraryRVModal>,
    private val context: Context
) :
    RecyclerView.Adapter<LibraryRVAdapter.LibraryItemViewHolder>() {

    class LibraryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookIV: ImageView = itemView.findViewById(R.id.idIVbook)
        val bookTitleTV: TextView = itemView.findViewById(R.id.idTVBookTitle)
        val publisherTV: TextView = itemView.findViewById(R.id.idTVpublisher)
        val publishedOnTV: TextView = itemView.findViewById(R.id.idTVPublishedDate)
        val pageCountTV: TextView = itemView.findViewById(R.id.idTVPageCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.library_rv_item,
            parent, false
        )
        return LibraryRVAdapter.LibraryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibraryItemViewHolder, position: Int) {
        val libraryRVItem = libraryList[position]
        holder.bookTitleTV.text = libraryRVItem.bookTitle
        holder.pageCountTV.text = "Number of Pages : " + libraryRVItem.pageCount.toString()
        holder.publisherTV.text = "Publisher : " + libraryRVItem.publisher
        holder.publishedOnTV.text = "Published On : "+libraryRVItem.publishDate

        Picasso.get().load(libraryRVItem.bookImg).placeholder(R.drawable.ic_launcher_background)
            .into(holder.bookIV)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailActivity::class.java);
            intent.putExtra("bookDesc", libraryList.get(position).bookDesc);
            intent.putExtra("bookImg", libraryList.get(position).bookImg);
            intent.putExtra("bookTitle", libraryList.get(position).bookTitle);
            intent.putExtra("infoLink", libraryList.get(position).infoLink);
            intent.putExtra("pageCount", libraryList.get(position).pageCount.toString());
            intent.putExtra("previewLink", libraryList.get(position).previewLink);
            intent.putExtra("publishDate", libraryList.get(position).publishDate);
            intent.putExtra("publisher", libraryList.get(position).publisher);
            context.startActivity(intent);
        }


    }

    override fun getItemCount(): Int {
        return libraryList.size
    }
}