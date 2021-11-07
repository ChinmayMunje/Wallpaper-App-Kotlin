package com.gtappdevelopers.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {

    lateinit var bookDesc: String
    lateinit var bookImg: String
    lateinit var bookTitle: String
    lateinit var infoLink: String
    lateinit var pageCount: String
    lateinit var previewLink: String
    lateinit var publishDate: String
    lateinit var publisher: String

    lateinit var bookDescTV: TextView
    lateinit var bookImgIV: ImageView
    lateinit var bookTitleTV: TextView
    lateinit var pageCountTV: TextView
    lateinit var publishDateTV: TextView
    lateinit var publisherTV: TextView
    lateinit var viewBookBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        bookDesc = intent.getStringExtra("bookDesc").toString();
        bookImg = intent.getStringExtra("bookImg").toString();
        bookTitle = intent.getStringExtra("bookTitle").toString();
        infoLink = intent.getStringExtra("infoLink").toString();
        pageCount = intent.getStringExtra("pageCount").toString();
        previewLink = intent.getStringExtra("previewLink").toString();
        publishDate = intent.getStringExtra("publishDate").toString();
        publisher= intent.getStringExtra("publisher").toString();

        bookTitleTV = findViewById(R.id.idTVBookTitle);
        publisherTV = findViewById(R.id.idTVPublisher);
        pageCountTV = findViewById(R.id.idTVPageCount);
        bookImgIV = findViewById(R.id.idIVbook);
        publishDateTV = findViewById(R.id.idTVPublishedDate);
        bookDescTV = findViewById(R.id.idTVDescription);
        viewBookBtn = findViewById(R.id.idBtnViewBook);
        bookTitleTV.text = bookTitle;
        publisherTV.text = "Publisher : "+publisher;
        pageCountTV.text = "Pages : "+pageCount;
        Picasso.get().load(bookImg).into(bookImgIV);
        publishDateTV.text = "Published On : "+publishDate;
        bookDescTV.text = bookDesc;
        viewBookBtn.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(infoLink)
            startActivity(openURL)

        }
    }
}