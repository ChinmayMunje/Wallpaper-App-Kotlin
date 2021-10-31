package com.gtappdevelopers.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    lateinit var wallpaperRV: RecyclerView
    lateinit var loadingPB: ProgressBar
    lateinit var wallpaperList: List<String>
    lateinit var wallpaperRVAdapter: WallpaperRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        wallpaperRV = findViewById(R.id.idRVNews)
        loadingPB = findViewById(R.id.idPBLoading)
        wallpaperList = ArrayList<String>()
        getNewsDetails()
    }

    private fun getNewsDetails() {
        val apiURL =
            "https://api.pexels.com/v1/curated?per_page=30&page=1"
            val queue = Volley.newRequestQueue(this)

        val request = object: JsonObjectRequest(Request.Method.GET, apiURL,null,
            Response.Listener<JSONObject> { response ->
                loadingPB.visibility = View.GONE
                try {
                    val photoArray = response.getJSONArray("photos")
                    if (photoArray.length().equals(0)) {
                        Toast.makeText(this, "No Wallpapers Found", Toast.LENGTH_SHORT).show()
                    }
                    for (i in 0 until photoArray.length()) {
                        val photoObj = photoArray.getJSONObject(i)
                        val imgUrl: String = photoObj.getJSONObject("src").getString("portrait")
                         wallpaperList = wallpaperList + imgUrl
                    }
                    wallpaperRVAdapter = WallpaperRVAdapter(wallpaperList,this)
                    wallpaperRV.layoutManager = GridLayoutManager(this,2)
                    wallpaperRV.adapter = wallpaperRVAdapter
                    wallpaperRVAdapter.notifyDataSetChanged()

                }catch (e:JSONException){
                    e.printStackTrace();
                }
            },
            Response.ErrorListener {
                Toast.makeText(this@HomeActivity, "Fail to get response", Toast.LENGTH_SHORT)
                    .show()
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "563492ad6f917000010000010e57141f30b2437cb7746c1519d63ee5"
                return headers
            }
        }
        queue.add(request)
    }
}