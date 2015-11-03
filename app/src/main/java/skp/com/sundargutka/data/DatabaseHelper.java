package skp.com.sundargutka.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import skp.com.sundargutka.data.DatabaseContract.ChapterEntry;
import skp.com.sundargutka.data.DatabaseContract.DetailEntry;

/**
 * Created by Sateesh Kumar on 10/25/2015.
 * This class is used to create and maintain database
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "bani.db";
    //if you change the database schema must change the version of the database;
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CHAPTER_TABLE = "CREATE TABLE " + ChapterEntry.TABLE_NAME + " (" +
                ChapterEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChapterEntry.COLUMN_CHAPTER_NAME + " TEXT NOT NULL, " +
                ChapterEntry.COLUMN_TOTAL_SUB_CHAPTERS + " INTEGER NOT NULL);";


        final String SQLI_CREATE_DETAIL_TABLE = "CREATE TABLE " + DetailEntry.TABLE_NAME + " (" +
                DetailEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DetailEntry.COLUMN_CH_KEY + " INTEGER NOT NULL, " +
                DetailEntry.COLUMN_SCH_NAME + " TEXT NOT NULL, " +
                DetailEntry.COLUMN_SCH_TEXT + "TEXT NOT NULL, " +
                " FOREIGN KEY ( " + DetailEntry.COLUMN_CH_KEY + ") REFERENCES " +
                ChapterEntry.TABLE_NAME + " (" + ChapterEntry._ID + ")" +
                ");";

        db.execSQL(SQL_CREATE_CHAPTER_TABLE);
        db.execSQL(SQLI_CREATE_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ChapterEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DetailEntry.TABLE_NAME);

        onCreate(db);
    }
}
