package com.bruno.futbol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "AppFutbol";

    public Banco(Context contexto) {
        super(contexto,NOME,null,VERSAO);
    }

    @Override
    public  void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS time (" +
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS jogador(" +
                "idjogador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nomejogador TEXT," +
                "posicao TEXT," +
                "numerocamiseta INTEGER,"+
                "idtime INTEGER," +
                "FOREIGN KEY (idtime) REFERENCES time(id));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase , int i , int il){


    }
}
















