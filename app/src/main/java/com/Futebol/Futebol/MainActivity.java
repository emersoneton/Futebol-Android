package com.bruno.futbol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvtimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvtimes= findViewById(R.id.lvTimes);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TIMES.class);
                startActivity( intent );
            }
        });
        //ao clique abre o painel de todos jogadores cadastrado
        lvtimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, Jogadores.class);

            //pega o objeto que esta na posição  que foi selecionado no ListView e joga no objeto de Time .
            Time  time= (Time) adapterView.getItemAtPosition(i);

            // esta eviando via putestra o id do Time cadastrado.
            intent.putExtra("idtime",time.getId());
            startActivity( intent );
        }
    });

        lvtimes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( (Time) adapterView.getItemAtPosition(i));
                return true;
            }
        });
    }





    // metodo para abrir activity formulario jogador
    private void abrirTelaJogador(){

        Intent intent = new Intent(MainActivity.this, Formulario_JogadorActivity.class);
        startActivity( intent );

    }


    private void excluir(final Time time){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Time");
        alerta.setMessage("Confirma a exclusão do Time"
                + time.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TimeDAO.excluirTime(MainActivity.this, time.getId());
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
        List<Time> lista = TimeDAO.getTime(this);

        if ( lista.size() == 0 ){
            lvtimes.setEnabled( false );
            Time fake = new Time();
            fake.setId(0);
            fake.setNome("Lista Vazia!");
            lista.add( fake );
        }else {
            lvtimes.setEnabled( true );
        }

        ArrayAdapter<Time> adapter = new ArrayAdapter(
               this, android.R.layout.simple_list_item_1,
        lista);

       // AdapterProduto adapter = new AdapterProduto(this, lista);

        lvtimes.setAdapter( adapter );

    }



}
