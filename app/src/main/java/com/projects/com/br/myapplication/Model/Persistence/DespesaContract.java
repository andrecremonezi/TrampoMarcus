package com.projects.com.br.myapplication.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.projects.com.br.myapplication.Model.Entities.Despesa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class DespesaContract {

    public static final String TABLE        = "despesa";
    public static final String ID           = "id";
    public static final String DESCRICAO  = "descricao";
    public static final String VALOR        = "valor";
    public static final String TIPO         = "tipo";

    public static final String[] COLUNS = {ID, DESCRICAO, VALOR, TIPO};

    private DespesaContract(){
        super();
    }

    public static String getCreateTableScript(){
        final StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(DESCRICAO + " TEXT NOT NULL, ");
        create.append(VALOR + " FLOAT NOT NULL, ");
        create.append(TIPO + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Despesa despesa) {
        ContentValues values = new ContentValues();

        values.put(DespesaContract.ID, despesa.getId());
        values.put(DespesaContract.DESCRICAO, despesa.getDescricao());
        values.put(DespesaContract.VALOR, despesa.getValor());
        values.put(DespesaContract.TIPO, despesa.getTipo());

        return values;
    }

    public static Despesa getDespesa(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Despesa despesa = new Despesa();

            despesa.setId(cursor.getLong(cursor.getColumnIndex(DespesaContract.ID)));
            despesa.setDescricao(cursor.getString(cursor.getColumnIndex(DespesaContract.DESCRICAO)));
            despesa.setValor(cursor.getDouble(cursor.getColumnIndex(DespesaContract.VALOR)));
            despesa.setTipo(cursor.getString(cursor.getColumnIndex(DespesaContract.TIPO)));

            return despesa;
        }
        return null;
    }

    public static List<Despesa> getDespesas(Cursor cursor) {

        List<Despesa> expenses = new ArrayList<>();
        while (cursor.moveToNext()) {
            expenses.add(getDespesa(cursor));
        }
        return expenses;
    }

}
