package com.example.latihan3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TambahMusikActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_musik)

        val txtTitle        = findViewById<EditText>(R.id.txt_title)
        val txtArtist       = findViewById<EditText>(R.id.txt_name)
        val txtAlbum        = findViewById<EditText>(R.id.txt_album)
        val txtGenre        = findViewById<EditText>(R.id.txt_genre)
        val txtYear_release = findViewById<EditText>(R.id.txt_year_release)

        val btnSave         = findViewById<Button>(R.id.btn_save)

        btnSave.setOnClickListener{
            val dbSaya = BaruMyDBHelper(this)

            dbSaya.tambahMusic(
                txtTitle.text.toString().trim(),
                txtArtist.text.toString().trim(),
                txtAlbum.text.toString().trim(),
                txtGenre.text.toString().trim(),
                Integer.valueOf(txtYear_release.text.toString().trim())
            )
        }
    }
}