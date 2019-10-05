package com.bruno.futbol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Formulario_JogadorActivity extends AppCompatActivity {

    boolean respost = false;
    private EditText  etNomeJogador;
    private EditText  etPosicaoJogador;
    private EditText  etNumeroCamiseta;
    private Button  btnSalvarJogador;
    private int idTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__jogador);

        etNomeJogador = (EditText) findViewById(R.id.txtjogadornome);
        etPosicaoJogador = (EditText) findViewById(R.id.txtjogadorposicao);
        etNumeroCamiseta = (EditText) findViewById(R.id.txtnumerojogador);

        btnSalvarJogador = (Button) findViewById(R.id.btnsalvarjogador);

        idTime = getIntent().getExtras().getInt("idtime");

        btnSalvarJogador.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarJogador();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Toast.makeText(this, "Botao SAIR", Toast.LENGTH_SHORT).show();
        switch (id){

            case R.id.action_settings:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Há campos Inválidos ou em Branco");
                dlg.setNeutralButton("OK", null);
                dlg.show();
                break;

            case R.id.action_cancelar:
                Toast.makeText(this, "Botao SAIR", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    private void ValidaCampos(){

        boolean res = false;

        String nome_jogador = etNomeJogador.getText().toString();
        String posicao_jogador = etPosicaoJogador.getText().toString();
        String numero_jogador = etNumeroCamiseta.getText().toString();


        if (isCampoVazio(nome_jogador)){
            etNomeJogador.requestFocus();
            res = true;
        }else if(isCampoVazio(posicao_jogador)){
            etPosicaoJogador.requestFocus();
            res = true;
        }else if(isCampoVazio(numero_jogador)){
            etNumeroCamiseta.requestFocus();
            res = true;
        }


            if (res){
                respost = true;
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Há campos Inválidos ou em Branco");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }else{
                respost = false;
            }



    }


    private boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;

    }


    private void salvarJogador(){

        ValidaCampos();




        if (respost == true){


        }else if(respost == false){


            String nomejogador = etNomeJogador.getText().toString();
            String posicao = etPosicaoJogador.getText().toString();
            int numerocamiseta = Integer.parseInt(etNumeroCamiseta.getText().toString());


            Jogador jogador = new Jogador();

            jogador.setNomejogador(nomejogador);
            jogador.setPosicao(posicao);
            jogador.setNumerocamiseta(numerocamiseta);
            jogador.setIdtime(idTime);


            JogadorDAO.inserirJogador(this,jogador);

            this.finish();
        }



    }


    private void Excluir(){

        Time time = new Time();

        time.setNome("");

    }



}

