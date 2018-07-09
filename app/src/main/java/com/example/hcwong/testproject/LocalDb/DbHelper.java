package com.example.hcwong.testproject.LocalDb;
/**
 * Created by Hau Cherng on 6/3/2016.
 */
import java.util.ArrayList;
import java.util.Arrays;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hcwong.testproject.Model.Article;


public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final int DATABASE_VERSION = 2;


    public static final String ARTICLE_TABLE_NAME = "article";
    public static final String ARTICLE_COLUMN_TITLE = "title";
    public static final String ARTICLE_COLUMN_DESCRIPTION = "description";
    public static final String ARTICLE_PUBLISHED_DATE = "published_date";
    public static final String ARTICLE_COLUMN_AUTHOR = "author";


    private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + ARTICLE_TABLE_NAME + "(" +
            ARTICLE_COLUMN_TITLE + " TEXT," + ARTICLE_COLUMN_DESCRIPTION + " TEXT,"
            + ARTICLE_COLUMN_AUTHOR + " TEXT,"+ARTICLE_PUBLISHED_DATE + " TEXT" + ")";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CUSTOMER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ARTICLE_TABLE_NAME);
        onCreate(db);
    }

    /**
     * *
     *
     * @param article
     */

    public Article insertArticle(Article article) {
        SQLiteDatabase da = this.getReadableDatabase();
        Cursor dbCursor = da.query(ARTICLE_TABLE_NAME, null, null, null, null, null, null);
        String[] colNames = dbCursor.getColumnNames();
        Log.d("columnsss", (Arrays.toString(colNames)));
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTICLE_COLUMN_TITLE, article.getTitle());
        contentValues.put(ARTICLE_COLUMN_DESCRIPTION, article.getDescription());
        contentValues.put(ARTICLE_PUBLISHED_DATE, article.getPublishedAt());
        contentValues.put(ARTICLE_COLUMN_AUTHOR,article.getAuthor());

        db.insertOrThrow(ARTICLE_TABLE_NAME, null, contentValues);
        return article;
    }


    public Article getArticle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + ARTICLE_TABLE_NAME + " WHERE " + ARTICLE_COLUMN_TITLE + " = \"" + title + "\"";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();

        Article customer = new Article();
        customer.setTitle(c.getString(c.getColumnIndex(ARTICLE_COLUMN_TITLE)));
        customer.setDescription(c.getString(c.getColumnIndex(ARTICLE_COLUMN_DESCRIPTION)));
        customer.setPublishedAt(c.getString(c.getColumnIndex(ARTICLE_PUBLISHED_DATE)));
        customer.setAuthor(c.getString(c.getColumnIndex(ARTICLE_COLUMN_AUTHOR)));
        return customer;

    }


    public ArrayList<Article> getAllArticle() {
        ArrayList<Article> allCustomer = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + ARTICLE_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Article customer = new Article();
                customer.setTitle(c.getString(c.getColumnIndex(ARTICLE_COLUMN_TITLE)));
                customer.setDescription(c.getString(c.getColumnIndex(ARTICLE_COLUMN_DESCRIPTION)));
                customer.setPublishedAt(c.getString(c.getColumnIndex(ARTICLE_PUBLISHED_DATE)));
                customer.setAuthor(c.getString(c.getColumnIndex(ARTICLE_COLUMN_AUTHOR)));
                allCustomer.add(customer);
            } while (c.moveToNext());
        }
        return allCustomer;
    }

    public int updateArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTICLE_COLUMN_TITLE, article.getTitle());
        contentValues.put(ARTICLE_COLUMN_DESCRIPTION, article.getDescription());
        contentValues.put(ARTICLE_PUBLISHED_DATE, article.getPublishedAt());
        contentValues.put(ARTICLE_COLUMN_AUTHOR,article.getAuthor());

        //updating row
        return db.update(ARTICLE_TABLE_NAME, contentValues, ARTICLE_COLUMN_TITLE + " = ?",
                new String[]{article.getTitle()});
    }

    public void deleteArticle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ARTICLE_TABLE_NAME, ARTICLE_COLUMN_TITLE + " = ?", new String[]{title});
    }
}