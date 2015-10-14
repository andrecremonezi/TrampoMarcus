package com.projects.com.br.myapplication.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projects.com.br.myapplication.Model.Entities.Despesa;

import java.util.List;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class DespesaRepository {

    public DespesaRepository(){
        super();
    }

    public static void save(Despesa despesa) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = DespesaContract.getContentValues(despesa);

        if (despesa.getId() == null) {

            db.insert(DespesaContract.TABLE, null, values);

        } else {

            String where = DespesaContract.ID + " = ? ";
            String[] params = {despesa.getId().toString()};
            db.update(DespesaContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = DespesaContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(DespesaContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static List<Despesa> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DespesaContract.TABLE, DespesaContract.COLUNS, null, null, null, null,DespesaContract.ID);
        List<Despesa> values = DespesaContract.getDespesas(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

}
