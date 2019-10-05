package com.bruno.futbol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.PublicKey;

public class TIMES extends AppCompatActivity {

    private EditText etNomeTime;

    private Button btnSalvar;

    private Integer idtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__time);

        etNomeTime = (EditText) findViewById(R.id.txtnometime);

        btnSalvar = (Button) findViewById(R.id.btnsalvartime);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }


    private void ValidaCampos(){

        boolean res = false;

        String nometime = etNomeTime.getText().toString();

        if (isCampoVazio(nometime)){
            etNomeTime.requestFocus();
            res = true;
        }

        if (res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos Inválidos ou em Branco");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

    }

    private boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;


    }

    private void salvar(){

      String nometime = etNomeTime.getText().toString();

        if( nometime.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o Time.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else {

            Time time = new Time();

            time.setNome(nometime);

            TimeDAO.inserirTime( this, time );

            this.finish();

        }

    }

}
