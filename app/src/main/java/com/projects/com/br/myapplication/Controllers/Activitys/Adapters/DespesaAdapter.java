package com.projects.com.br.myapplication.Controllers.Activitys.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projects.com.br.myapplication.Model.Entities.Despesa;
import com.projects.com.br.myapplication.R;

import java.util.List;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class DespesaAdapter extends BaseAdapter{
    private List<Despesa> despesaList;
    private Activity context;


    public DespesaAdapter(Activity context, List<Despesa> despesaList) {
        this.despesaList = despesaList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return despesaList.size();
    }

    @Override
    public Object getItem(int position) {
        return despesaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Despesa despesa = (Despesa) getItem(position);

        View expenseListView = context.getLayoutInflater().inflate(R.layout.list_item_despesa, parent, false);

        TextView textViewDescricao = (TextView) expenseListView.findViewById(R.id.textViewDescricao);
        textViewDescricao.setText(despesa.getDescricao());

        TextView textViewValor = (TextView) expenseListView.findViewById(R.id.textViewValor);
        textViewValor.setText(despesa.getValor().toString());

        TextView textViewTipo = (TextView) expenseListView.findViewById(R.id.textViewTipo);
        textViewTipo.setText(despesa.getTipo().toString());

        return expenseListView;
    }

    public void setDataValues(List<Despesa> values) {
        despesaList.clear();
        despesaList.addAll(values);
    }
}
