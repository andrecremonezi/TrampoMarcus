package com.projects.com.br.myapplication.Model.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.projects.com.br.myapplication.Util.ApplicationUtil;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "despesamanager";
    private static final int DATABASE_VERSION = 1;


    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getIstance(){
        return new DataBaseHelper(ApplicationUtil.getApplicationContext());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Finance Manager", "Create Table Despesa");
        db.execSQL(DespesaContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
