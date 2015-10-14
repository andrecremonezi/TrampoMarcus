package com.projects.com.br.myapplication.Controllers.Activitys.Controllers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projects.com.br.myapplication.Model.Entities.Despesa;
import com.projects.com.br.myapplication.Model.Services.DespesaBussinessService;
import com.projects.com.br.myapplication.Util.FormHelper;
import com.projects.com.br.myapplication.R;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class FormularioActivity extends Activity {
    private String[] tipos = new String[] {"Credito","Débito"};
    private EditText editTextDescricao;
    private EditText editTextvalor;
    private Spinner  spinnerTipo;
    private Despesa despesa;
    private Button buttonSalvar;
    public static final String PARAM_TASK = "PARAM_TASK";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity);
        initDespesa();
        bindFields();
        bindSpinnerTipo();
        bindButtonSalvar();
    }

    private void initDespesa() {
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.despesa = (Despesa) extras.getParcelable(PARAM_TASK);
        }

        this.despesa = this.despesa == null ? new Despesa() : this.despesa;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void bindButtonSalvar() {
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String requiredMessage = FormularioActivity.this.getString(R.string.msg_campo_necessario);

                if (!FormHelper.validateRequired(requiredMessage, editTextvalor, editTextDescricao)) {
                    bindDespesa();
                    DespesaBussinessService.save(despesa);
                    Toast.makeText(FormularioActivity.this, getString(R.string.msg_despesa_criada), Toast.LENGTH_LONG).show();
                    FormularioActivity.this.finish();
                }
            }
        });
    }

    private void bindDespesa() {
        despesa.setDescricao(editTextDescricao.getText().toString());
        despesa.setValor(Double.parseDouble(editTextvalor.getText().toString()));
        despesa.setTipo(spinnerTipo.getSelectedItem().toString());
    }

    private void bindFields() {
        editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);
        editTextvalor     = (EditText) findViewById(R.id.editTextValor);
    }

    private void bindSpinnerTipo(){
        ArrayAdapter<String> adapterInstallments = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipos);
        adapterInstallments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo = (Spinner)  findViewById(R.id.spinnerTipo);
        spinnerTipo.setAdapter(adapterInstallments);
    }


}
