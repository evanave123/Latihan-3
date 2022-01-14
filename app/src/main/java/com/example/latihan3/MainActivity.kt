package com.example.latihan3

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    val music_id: ArrayList<String>                     = arrayListOf()
    val music_titleTEXT: ArrayList<String>              = arrayListOf()
    val music_artistTEXT: ArrayList<String>             = arrayListOf()
    val music_albumTEXT: ArrayList<String>              = arrayListOf()
    val music_genreTEXT: ArrayList<String>              = arrayListOf()
    val music_year_releaseINTEGER: ArrayList<String>    = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<FloatingActionButton>(R.id.tom_add)
        btnTambah.setOnClickListener {
            val intentKita = Intent(this, TambahMusikActivity::class.java)
            startActivity(intentKita)
        }

        simpanDataDiArray()

        val rv_music = findViewById<RecyclerView>(R.id.rv_books)
        val bukuAdapter = BukuAdapter(
            this, music_id, music_titleTEXT, music_artistTEXT, music_albumTEXT,
            music_genreTEXT, music_year_releaseINTEGER
        )

        rv_music.adapter = bukuAdapter
        rv_music.layoutManager = LinearLayoutManager(this)

    }

    fun simpanDataDiArray(){
        val dbKita              = BaruMyDBHelper(this)
        val dataKita: Cursor    = dbKita.bacaSemuaData()

        if (dataKita.count == 0) {
            Toast.makeText(this,"Data Tidak Ada !", Toast.LENGTH_SHORT).show()
        }
        else {
            while (dataKita.moveToNext()) {

                music_id.add(dataKita.getString(0))
                music_titleTEXT.add(dataKita.getString(1))
                music_artistTEXT.add(dataKita.getString(2))
                music_albumTEXT.add(dataKita.getString(3))
                music_genreTEXT.add(dataKita.getString(4))
                music_year_releaseINTEGER.add(dataKita.getString(5))
            }
        }

    }
}