package skp.com.sundargutka.data;

import android.content.ContentValues;
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
                ChapterEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChapterEntry.COLUMN_CHAPTER_NAME + " TEXT, " +
                ChapterEntry.COLUMN_TOTAL_SUB_CHAPTERS + " INTEGER);";


        final String SQL_CREATE_DETAIL_TABLE = "CREATE TABLE " + DetailEntry.TABLE_NAME + " (" +
                DetailEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DetailEntry.COLUMN_CH_KEY + " INTEGER, " +
                DetailEntry.COLUMN_SCH_NAME + " TEXT, " +
                DetailEntry.COLUMN_SCH_TEXT + "TEXT, " +
                " FOREIGN KEY ( " + DetailEntry.COLUMN_CH_KEY + ") REFERENCES " +
                ChapterEntry.TABLE_NAME + " (" + ChapterEntry._ID + ")" +
                ");";

        db.execSQL(SQL_CREATE_CHAPTER_TABLE);
        db.execSQL(SQL_CREATE_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ChapterEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DetailEntry.TABLE_NAME);

        onCreate(db);
    }

    public boolean insertChapter(String chapterName, int totalChapters)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ChapterEntry.COLUMN_CHAPTER_NAME, chapterName);
        contentValues.put(ChapterEntry.COLUMN_TOTAL_SUB_CHAPTERS, totalChapters);
        db.insert(ChapterEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertDetail(int position, String subChapterName, String subChapterText)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DetailEntry.COLUMN_CH_KEY, position);
        contentValues.put(DetailEntry.COLUMN_SCH_NAME, subChapterName);
        contentValues.put(DetailEntry.COLUMN_SCH_TEXT, subChapterText);
        if(db!=null)
            db.close();
        return true;
    }
}
