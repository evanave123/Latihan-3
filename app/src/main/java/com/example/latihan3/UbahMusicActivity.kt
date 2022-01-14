package com.example.latihan3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UbahMusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_music)

        val actionBar = supportActionBar
        if (intent.hasExtra("title")) {
            actionBar?.title = intent.getStringExtra("title")
        }

        val btnChange             = findViewById<Button>(R.id.btn_change)

        getIntentData()

        btnChange.setOnClickListener {
            val dbKita = BaruMyDBHelper(this)

            val idMusic = intent.getStringExtra("id")
            val titleMusic = findViewById<EditText>(R.id.txt_edit_title).text.toString()
            val artistMusic = findViewById<EditText>(R.id.txt_edit_artist).text.toString()
            val almbumMusic = findViewById<EditText>(R.id.txt_edit_title).text.toString()
            val year_releaseMusic = findViewById<EditText>(R.id.txt_edit_year_release).text.toString()
            val genreMusic = findViewById<EditText>(R.id.txt_edit_genre).text.toString()

            dbKita.ubahMusic(idMusic, titleMusic, artistMusic, almbumMusic, genreMusic, year_releaseMusic)
        }

        val btnDelete = findViewById<Button>(R.id.btn_delete)
        btnDelete.setOnClickListener{
            dialogKonfirmasi()
        }
    }
    fun getIntentData() {
        if (
                intent.hasExtra("id") && intent.hasExtra("title") && intent.hasExtra("artist")
                && intent.hasExtra("album") && intent.hasExtra("genre") && intent.hasExtra("year_release")
            ){

                val idMusic                = intent.getStringExtra("id")
                val titleMusic             = intent.getStringExtra("title")
                val artistMusic            = intent.getStringExtra("artist")
                val albumMusic             = intent.getStringExtra("album")
                val genreMusic             = intent.getStringExtra("genre")
                val year_releaseMusic      = intent.getStringExtra("year_release")

                val txtTitle               = findViewById<EditText>(R.id.txt_edit_title)
                val txtArtist              = findViewById<EditText>(R.id.txt_edit_artist)
                val txtAlbum               = findViewById<EditText>(R.id.txt_edit_album)
                val txtGenre               = findViewById<EditText>(R.id.txt_edit_genre)
                val txtYear_release        = findViewById<EditText>(R.id.txt_edit_year_release)

                txtTitle.setText(titleMusic)
                txtArtist.setText(artistMusic)
                txtAlbum.setText(albumMusic)
                txtGenre.setText(genreMusic)
                txtYear_release.setText(year_releaseMusic)


            }else {
                Toast.makeText(this, "No Data !", Toast.LENGTH_SHORT).show()
            }

        }

        fun dialogKonfirmasi() {
            val idMusic     = intent.getStringExtra("id")
            val titleMusic  = intent.getStringExtra("title")

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Delete " + titleMusic + " ?")
            alertDialog.setMessage("Do you sure want to delete " + titleMusic + " ?")

            alertDialog.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                val dbKita = BaruMyDBHelper(this)
                dbKita.hapusMusic(idMusic)
                startActivity(Intent(this,MainActivity::class.java))

            })
            alertDialog.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->  })
            alertDialog.create().show()

        }
    }
