package com.gtappdevelopers.newsapp

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class WallpaperActivity : AppCompatActivity() {

    lateinit var imgUrl: String
    lateinit var wallpaperIV: ImageView
    lateinit var setWallpaperBtn: Button
    lateinit var wallpaperManager: WallpaperManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        imgUrl = intent.getStringExtra("imgUrl").toString();
        wallpaperIV = findViewById(R.id.idIVWallpaper);
        setWallpaperBtn = findViewById(R.id.idBtnSetWallpaper);
        Picasso.get().load(imgUrl).into(wallpaperIV);
        setWallpaperBtn.setOnClickListener {
            val bitmap = (wallpaperIV.getDrawable() as BitmapDrawable).bitmap
            val wallpaperManager = WallpaperManager.getInstance(baseContext)
            wallpaperManager.setBitmap(bitmap)
            Toast.makeText(this, "Wallpaper set!", Toast.LENGTH_SHORT).show()
        }


    }
}