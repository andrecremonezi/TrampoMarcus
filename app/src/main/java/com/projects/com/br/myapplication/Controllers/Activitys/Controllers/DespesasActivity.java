package com.projects.com.br.myapplication.Controllers.Activitys.Controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.com.br.myapplication.Model.Entities.Despesa;
import com.projects.com.br.myapplication.Controllers.Activitys.Adapters.DespesaAdapter;
import com.projects.com.br.myapplication.Model.Services.DespesaBussinessService;
import com.projects.com.br.myapplication.R;

import java.util.List;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class DespesasActivity extends AppCompatActivity {
    private TextView valorTotal;
    private ListView listViewDespesa;
    private Despesa selectedDespesa;
    private List<Despesa> getDespesas;
    private Toolbar toolbar;


    public DespesasActivity(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despesas_activity);
        bindListaDesepsa();
        bindToolbar();
        bindFields();
    }

    private void bindToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.label_lista);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
    }

    private void bindFields() {
        valorTotal =(TextView)findViewById(R.id.textViewValorTotal);
    }


    private void updateData() {
        Double totalDespesa = DespesaBussinessService.somaValues();
        valorTotal.setText(getString(R.string.total) + " " + String.valueOf(totalDespesa));
    }


    private void bindListaDesepsa() {
        listViewDespesa = (ListView) findViewById(R.id.listViewDespesas);
        registerForContextMenu(listViewDespesa);

        listViewDespesa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DespesaAdapter adapter = (DespesaAdapter) listViewDespesa.getAdapter();
                selectedDespesa = (Despesa) adapter.getItem(position);
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        onUpdateList();
        updateData();
        super.onResume();
    }

    private void onUpdateList() {
        List<Despesa> values = DespesaBussinessService.findAll();
        listViewDespesa.setAdapter(new DespesaAdapter(this, values));
        DespesaAdapter adapter = (DespesaAdapter) listViewDespesa.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.adicionar:
                click();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                onMenuDeleteClick();
                break;
            case R.id.menu_editar:
                onMenuUpdateClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void click() {
        Intent irParaLa = new Intent(DespesasActivity.this, FormularioActivity.class);
        startActivity(irParaLa);
    }

    private void onMenuUpdateClick() {
        Intent goToNewExpense = new Intent(DespesasActivity.this, FormularioActivity.class);
        goToNewExpense.putExtra(FormularioActivity.PARAM_TASK, selectedDespesa);
        startActivity(goToNewExpense);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(DespesasActivity.this)
                .setTitle(R.string.lbl_confirm_delet_expense)
                .setMessage(R.string.msg_confirm_delete_expense)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DespesaBussinessService.delete(selectedDespesa);
                        selectedDespesa = null;
                        String message = getString(R.string.msg_delete_sucessfull);
                        onUpdateList();
                        updateData();
                        Toast.makeText(DespesasActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }

}
