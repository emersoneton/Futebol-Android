package com.bruno.futbol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {



public static void inserirJogador (Context contexto, Jogador jogador){

    Banco banco = new Banco(contexto);
    SQLiteDatabase db = banco.getWritableDatabase();

    ContentValues valores = new ContentValues();
    valores.put("nomejogador",jogador.getNomejogador());
    valores.put("Posicao",jogador.getPosicao());
    valores.put("numerocamiseta",jogador.getNumerocamiseta());
    valores.put("idtime",jogador.getIdtime());
     db.insert("jogador",null,valores);
}

public static void editarJogador(Context contexto , Jogador jogador){

    Banco banco = new Banco(contexto);
    SQLiteDatabase db = banco.getWritableDatabase();
    ContentValues valores = new ContentValues();

    valores.put("nomejogador",jogador.getNomejogador());
    valores.put("`Posicao",jogador.getPosicao());
    valores.put("numeroccamiseta",jogador.getNumerocamiseta());
    valores.put("idtime",jogador.getIdtime());

    db.update("jogador",valores,"idjogador="+jogador.getIdjogador(),null);
}

public static void excluirJogador(Context contexto , int idjogador){

    Banco banco = new Banco(contexto);
    SQLiteDatabase db = banco.getWritableDatabase();

    db.delete("jogador","idjogador="+idjogador,null);


}

public static List<Jogador> getjogador(Context contexto, int idtime){

    List<Jogador> listajogador = new ArrayList<>();

    Banco banco = new Banco(contexto);
    SQLiteDatabase db = banco.getWritableDatabase();

    Cursor cursor = db.rawQuery("SELECT * FROM jogador WHERE idtime="+idtime,null);

    if(cursor.getCount()>0){
        cursor.moveToFirst();

        do {
            Jogador jogador = new Jogador();
            jogador.setIdjogador(cursor.getInt(0));
            jogador.setNomejogador(cursor.getString(1));
            jogador.setPosicao(cursor.getString(2));
            jogador.setNumerocamiseta(cursor.getInt(3));
            jogador.setIdtime(cursor.getInt(4));
            listajogador.add(jogador);

        }while(cursor.moveToNext());
    }
    return listajogador;

}

public static Jogador getJogadorById(Context contexto, int idjogador){

    Banco banco = new Banco(contexto);
    SQLiteDatabase db = banco.getWritableDatabase();

    Cursor cursor = db.rawQuery("SELECT * FROM jogador WHERE idjogador="+idjogador,null);

    if(cursor.getCount()>0){

        cursor.moveToFirst();
        Jogador jogador = new Jogador();
        jogador.setIdjogador(cursor.getInt(0));
        jogador.setNomejogador(cursor.getString(1));
        jogador.setPosicao(cursor.getString(2));
        jogador.setNumerocamiseta(cursor.getInt(3));
        jogador.setIdtime(cursor.getInt(4));

        return jogador;
    }else{
        return null;
    }
}


}
