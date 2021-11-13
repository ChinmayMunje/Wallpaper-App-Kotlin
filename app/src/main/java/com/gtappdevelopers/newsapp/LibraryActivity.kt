package com.gtappdevelopers.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class LibraryActivity : AppCompatActivity() {
    lateinit var libraryRV: RecyclerView
    lateinit var loadingPB: ProgressBar
    lateinit var bookList: List<LibraryRVModal>
    lateinit var libraryRVAdapter: LibraryRVAdapter
    lateinit var searchEdt: EditText
    lateinit var searchIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        libraryRV = findViewById(R.id.idRVBooks)
        loadingPB = findViewById(R.id.idPBLoading)
        searchEdt = findViewById(R.id.idEdtSearch)
        searchIV = findViewById(R.id.idIVSearch)

        getBooks("engineering")
        searchIV.setOnClickListener {
            val query:String = searchEdt.text.toString()
            if(query.isNotEmpty()){
                getBooks(query)
            }
        }
    }


    private fun getBooks(query: String){
        val url =
            "https://www.googleapis.com/books/v1/volumes?q=" + query
        val queue = Volley.newRequestQueue(this@LibraryActivity)
        bookList = ArrayList<LibraryRVModal>()

        loadingPB.visibility = View.VISIBLE
        val request =
            JsonObjectRequest(Request.Method.GET, url, null, { response ->
                loadingPB.setVisibility(View.GONE)
                try {
                    val itemsArray = response.getJSONArray("items")
                    if (itemsArray.length().equals(0)) {
                        Toast.makeText(this, "No Books Found", Toast.LENGTH_SHORT).show()
                    }
                    for (i in 0 until itemsArray.length()) {
                        val itemObj = itemsArray.getJSONObject(i)
                        val bookTitle: String =
                            itemObj.getJSONObject("volumeInfo").optString("title")

                        val bookImg: String =
                            itemObj.getJSONObject("volumeInfo").getJSONObject("imageLinks").optString("thumbnail")
                        val publisher: String =
                            itemObj.getJSONObject("volumeInfo").optString("publisher")
                        val publisherDate: String =
                            itemObj.getJSONObject("volumeInfo").optString("publishedDate")
                        val previewLink: String =
                            itemObj.getJSONObject("volumeInfo").optString("previewLink")
                        val bookDesc: String =
                            itemObj.getJSONObject("volumeInfo").optString("description")
                        val infoLink: String =
                            itemObj.getJSONObject("volumeInfo").optString("infoLink")
                        val pageCount: Int = itemObj.getJSONObject("volumeInfo").optInt("pageCount")

                        val bookObj = LibraryRVModal(
                            bookTitle,
                            bookImg,
                            publisher,
                            publisherDate,
                            previewLink,
                            bookDesc,
                            infoLink,
                            pageCount
                        )
                        bookList = bookList + bookObj
                    }

                    libraryRVAdapter = LibraryRVAdapter(bookList, this)
                    libraryRV.layoutManager = LinearLayoutManager(this)
                    libraryRV.adapter = libraryRVAdapter
                    libraryRVAdapter.notifyDataSetChanged()

                    Log.e("TAG","PRINTED LIST IS \n\n\n "+bookList);

                } catch (e: JSONException) {
                    e.printStackTrace();
                }


            },
                { error ->
                    Log.e("TAG", "RESPONSE IS $error")
                    Toast.makeText(this@LibraryActivity, "Fail to get response", Toast.LENGTH_SHORT)
                        .show()
                })
        queue.add(request)
    }

}