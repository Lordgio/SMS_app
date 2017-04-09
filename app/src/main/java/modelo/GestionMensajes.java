package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ejercicio36.a36_aplicacion_sms.ListadoActivity;

/**
 * Created by Jorge on 08/04/2017.
 */

public class GestionMensajes {
    String nombredb;
    Ayudante helper;
    SQLiteDatabase db;
    public GestionMensajes(Context ctx, String nombreBd){
        this.nombredb=nombreBd;
        helper=new Ayudante(ctx,nombredb);
        db=helper.getWritableDatabase();
    }
    public GestionMensajes(Context ctx){
        helper=new Ayudante(ctx,"mensajes");
        db=helper.getWritableDatabase();
    }

    public void guardarMensaje(Mensaje m){
        ContentValues valores=new ContentValues();
        valores.put("origen",m.getOrigen());
        valores.put("mensaje",m.getMensaje());
        db.insert(nombredb,null,valores);
    }

    public Cursor mostrarDatosCursor(){
        Cursor c=db.query(nombredb,null,null,null,null,null,null);
        return c;
    }

    public ArrayList<Mensaje> mostrarDatosArray(){
        ArrayList<Mensaje> mens=new ArrayList<>();
        Cursor c=db.query(nombredb,null,null,null,null,null,null);
        while(c.moveToNext()){
            Mensaje m=new Mensaje(c.getString(1),c.getString(2));
            mens.add(m);
        }
        return mens;
    }

    public void cerrar(){
        helper.close();
    }

}
