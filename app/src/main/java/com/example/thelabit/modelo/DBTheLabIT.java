package com.example.thelabit.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBTheLabIT extends SQLiteOpenHelper {

    public static final String DBNAME = "DBTheLabIT.db";
    public String queryTablaLogin;
    public String queryInsert;


    public DBTheLabIT(@Nullable Context context) {
        super(context, "DBTheLabIT.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        queryTablaLogin = "CREATE TABLE LOGIN(USERNAME STRING PRIMARY KEY, PASSWORD STRING)";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "CREATE TABLE USUARIOS(USERNAME STRING PRIMARY KEY, NOMBRE STRING, FECHANACIMIENTO DATE, CIUDAD STRING, PAIS STRING, EMAIL STRING, COMENTARIO STRING)";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "CREATE TABLE ENTRENADORES(USERNAME STRING PRIMARY KEY, FORMACION STRING)";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "CREATE TABLE CORREDORES(USERNAME STRING PRIMARY KEY, EDAD INTEGER)";
        db.execSQL(queryTablaLogin);

        //INSERTO UNOS DATOS DE PRUEBA
        //INSERT DE USUARIOS Y PASSWORD
        queryTablaLogin = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('1', 'pwfernando')";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('2', 'pwadrian')";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('3', 'pwjoaquin')";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('4', 'pwmario')";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('5', 'pwcarol')";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "INSERT INTO USUARIOS(USERNAME, NOMBRE, FECHANACIMIENTO, CIUDAD, PAIS, EMAIL, COMENTARIO ) VALUES (1, 'FERNANDO', '01/01/2021', 'MONTEVIDEO', 'URUGUAY', 'HOLA@HOLA.COM', '')";
        db.execSQL(queryTablaLogin);

        queryTablaLogin = "INSERT INTO ENTRENADORES(USERNAME, FORMACION) VALUES ('1', 'ED FISICA')";
        db.execSQL(queryTablaLogin);

<<<<<<< HEAD
        queryTablaLogin = "INSERT INTO CORREDORES(USERNAME, EDAD) VALUES ('2', 35)";
=======
        queryTablaLogin = "INSERT INTO CORREDORES(USERNAME, EDAD ) VALUES ('2', '70', 'MASCULINO', '170', '60', '180', '10', '50')";
>>>>>>> parent of 10f336a (ejemplo de listado de usuarios)
        db.execSQL(queryTablaLogin);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS LOGIN");
        db.execSQL("DROP TABLE IF EXISTS USUARIOS");
        db.execSQL("DROP TABLE IF EXISTS ENTRENADORES");
        db.execSQL("DROP TABLE IF EXISTS CORREDORES");
    }


    public String chequearUsuarioPassword(Login login){
        String username = login.getUsername().toString();
        String password = login.getPassword().toString();

        SQLiteDatabase db =  this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM LOGIN WHERE USERNAME = ? and PASSWORD = ?", new String[]{username, password});
        Cursor cursor_entrenador = db.rawQuery("SELECT * FROM ENTRENADORES WHERE USERNAME = ?", new String[]{username});
        Cursor cursor_corredor= db.rawQuery("SELECT * FROM CORREDORES WHERE USERNAME = ?", new String[]{username});

        if(cursor.getCount() > 0 & cursor_corredor.getCount() > 0)
            return "corredor";
        else if (cursor.getCount() > 0 & cursor_entrenador.getCount() > 0)
            return "entrenador";
        else
            return "sin_categoria";
    }

    public void inicio(){ }
}
