package com.bruno.futbol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Jogadores extends AppCompatActivity {

    private Integer idtime;
     private ListView lvJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //pega o valor da tela MainActivity  pelo putestras
        idtime = getIntent().getExtras().getInt("idtime");

        lvJogadores= findViewById(R.id.lvJogadores);

        FloatingActionButton fab = findViewById(R.id.fabjogadores);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Jogadores.this, Formulario_JogadorActivity.class);
                // esta eviando via putestra o id do Time cadastrado.
                intent.putExtra("idtime",idtime);
                startActivity( intent );
            }
        });

        lvJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( (Jogador) adapterView.getItemAtPosition(i));
                return true;
            }
        });

        lvJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( (Jogador) adapterView.getItemAtPosition(i));
                return true;
            }
        });
    }




    private void excluir(final Jogador jogador){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Time");
        alerta.setMessage("Confirma a exclusão do Jogador"
                + jogador.getNomejogador() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                JogadorDAO.excluirJogador(Jogadores.this, jogador.getIdjogador());
                carregarLista();
            }
        });
        alerta.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void carregarLista(){
        List<Jogador> listaJogador = JogadorDAO.getjogador(this,idtime);

        if ( listaJogador.size() == 0 ){
            lvJogadores.setEnabled( false );
            Jogador fake = new Jogador();
            fake.setIdjogador(0);
            fake.setNomejogador("Lista Vazia!");
            fake.setPosicao("");
            fake.setNumerocamiseta(0);
            fake.setIdtime(0);
            listaJogador.add( fake );
        }else {
            lvJogadores.setEnabled( true );
        }

        ArrayAdapter<Jogador> adapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                listaJogador);

        // AdapterProduto adapter = new AdapterProduto(this, lista);

        lvJogadores.setAdapter( adapter );

    }

}
