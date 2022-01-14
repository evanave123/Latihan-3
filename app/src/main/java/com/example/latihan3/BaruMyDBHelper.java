package com.example.latihan3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BaruMyDBHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "MUSIC INFORMATION.db";
    private static final int DATABASE_VERSION = 1;

    private static final String  TABLE_NAME     = "music";
    private static final String  COLUMN_ID      = "_id";
    private static final String  COLUMN_TITLE   = "music_titleTEXT";
    private static final String  COLUMN_NAME    = "music_artistTEXT";
    private static final String  COLUMN_ALBUM   = "music_albumTEXT";
    private static final String  COLUMN_GENRE   = "music_genreTEXT";
    private static final String  COLUMN_RELEASE = "music_year_releaseINTEGER";


    public BaruMyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryKita = "CREATE TABLE " + TABLE_NAME +
                "(" +

                COLUMN_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_TITLE    + " TEXT     , " +
                COLUMN_NAME     + " TEXT     , " +
                COLUMN_ALBUM    + " TEXT     , " +
                COLUMN_GENRE    + " TEXT     , " +
                COLUMN_RELEASE  + " INTEGER    " +
                ");";
        db.execSQL(queryKita);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void tambahMusic(String title, String artist, String album, String genre, int year_release) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_NAME, artist);
        cv.put(COLUMN_ALBUM, album);
        cv.put(COLUMN_GENRE, genre);
        cv.put(COLUMN_RELEASE, year_release);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Failed !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successful Add !", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor bacaSemuaData() {
        String queryKita        = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dbKita   = this.getReadableDatabase();

        Cursor dataKita         = null;

        if (dbKita != null) {
            dataKita = dbKita.rawQuery(queryKita, null);
        }

        return  dataKita;
    }

    void ubahMusic(String music_id, String title, String artist, String album, String genre, String year_release) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataKita = new ContentValues();
        dataKita.put(COLUMN_ID, music_id);
        dataKita.put(COLUMN_TITLE, title);
        dataKita.put(COLUMN_NAME, artist);
        dataKita.put(COLUMN_ALBUM, album);
        dataKita.put(COLUMN_GENRE, genre);
        dataKita.put(COLUMN_RELEASE, year_release);

        long hasil = db.update(TABLE_NAME, dataKita,"_id=?", new String[]{music_id});

        if (hasil == -1) {
            Toast.makeText(context, "You have some mistake", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusMusic(String row_id) {
        SQLiteDatabase dbKita = getReadableDatabase();
        long result = dbKita.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "Delete Fail !", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Delete Success !", Toast.LENGTH_SHORT).show();
        }
    }

}
