package com.example.thelabit.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.thelabit.MainActivity;

public class DBTheLabIT extends SQLiteOpenHelper {

    public String queryCrearTabla;
    public String queryInsert;


    public DBTheLabIT(@Nullable Context context) {
        super(context, "DBTheLabIT.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        queryCrearTabla = "CREATE TABLE LOGIN(USERNAME STRING PRIMARY KEY, PASSWORD STRING)";
        db.execSQL(queryCrearTabla);

        queryCrearTabla = "CREATE TABLE USUARIOS(USERNAME STRING PRIMARY KEY, NOMBRE STRING, FECHANACIMIENTO STRING, CIUDAD STRING, PAIS STRING, EMAIL STRING, COMENTARIO STRING)";
        db.execSQL(queryCrearTabla);

        queryCrearTabla = "CREATE TABLE ENTRENADORES(USERNAME STRING PRIMARY KEY, FORMACION STRING)";
        db.execSQL(queryCrearTabla);

        queryCrearTabla = "CREATE TABLE CORREDORES(USERNAME STRING PRIMARY KEY, PESO STRING, GENERO STRING, ALTURA STRING, FCREPOSO STRING, FCMAXIMA STRING, OBJETIVO STRING, TIEMPOESTIMADO STRING)";
        db.execSQL(queryCrearTabla);

        queryCrearTabla = "CREATE TABLE PLANES_ENTRENAMIENTOS(ID INTEGER PRIMARY KEY, NOMBRE STRING, DISTANCIA STRING, OBJETIVO STRING, COMENTARIO STRING)";
        db.execSQL(queryCrearTabla);

        queryCrearTabla = "CREATE TABLE ACTIVIDADES(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, SEMANA STRING, DIA STRING, TURNO STRING, DESCRIPCION STRING)";
        db.execSQL(queryCrearTabla);

        queryCrearTabla = "CREATE TABLE PLANES_DETALLE(IDENTRENADOR STRING, IDCORREDOR STRING, IDPLAN INT)";
        db.execSQL(queryCrearTabla);

        //INSERTO UNOS DATOS DE PRUEBA
        //INSERT DE USUARIOS Y PASSWORD
        queryInsert = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('1', 'pwfernando')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('2', 'pwadrian')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('3', 'pwjoaquin')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('4', 'pwmario')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('5', 'pwcarol')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO USUARIOS(USERNAME, NOMBRE, FECHANACIMIENTO, CIUDAD, PAIS, EMAIL, COMENTARIO ) VALUES ('2', 'FERNANDO2', '01/01/2021', 'MONTEVIDEO', 'URUGUAY', 'HOLA@HOLA.COM', '')";
        db.execSQL(queryInsert);
        queryInsert = "INSERT INTO USUARIOS(USERNAME, NOMBRE, FECHANACIMIENTO, CIUDAD, PAIS, EMAIL, COMENTARIO ) VALUES ('3', 'FERNANDO3', '01/01/2021', 'MONTEVIDEO', 'URUGUAY', 'HOLA@HOLA.COM', '')";
        db.execSQL(queryInsert);
        queryInsert = "INSERT INTO USUARIOS(USERNAME, NOMBRE, FECHANACIMIENTO, CIUDAD, PAIS, EMAIL, COMENTARIO ) VALUES ('4', 'FERNANDO4', '01/01/2021', 'MONTEVIDEO', 'URUGUAY', 'HOLA@HOLA.COM', '')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO ENTRENADORES(USERNAME, FORMACION) VALUES ('1', 'ED FISICA')";
        db.execSQL(queryInsert);
        queryInsert = "INSERT INTO ENTRENADORES(USERNAME, FORMACION) VALUES ('4', 'ED FISICA')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO CORREDORES(USERNAME, PESO, GENERO, ALTURA, FCREPOSO, FCMAXIMA, OBJETIVO, TIEMPOESTIMADO ) VALUES ('2', '70', 'MASCULINO2', '170', '60', '180', '10', '50')";
        db.execSQL(queryInsert);
        queryInsert = "INSERT INTO CORREDORES(USERNAME, PESO, GENERO, ALTURA, FCREPOSO, FCMAXIMA, OBJETIVO, TIEMPOESTIMADO ) VALUES ('3', '70', 'MASCULINO3', '170', '60', '180', '10', '50')";
        db.execSQL(queryInsert);
        queryInsert = "INSERT INTO CORREDORES(USERNAME, PESO, GENERO, ALTURA, FCREPOSO, FCMAXIMA, OBJETIVO, TIEMPOESTIMADO ) VALUES ('4', '70', 'MASCULINO4', '170', '60', '180', '10', '50')";
        db.execSQL(queryInsert);


        queryInsert = "INSERT INTO PLANES_ENTRENAMIENTOS(ID, NOMBRE, DISTANCIA, OBJETIVO , COMENTARIO) VALUES (1, 'PLAN 10K PRINCIPIANTE', '10K', '60 MIN', '8 SEMANAS')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO PLANES_ENTRENAMIENTOS(ID, NOMBRE, DISTANCIA, OBJETIVO , COMENTARIO) VALUES (2, 'PLAN 10K INTERMEDIO', '10K', '45 MIN', '12 SEMANAS')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO ACTIVIDADES(ID, SEMANA, DIA, TURNO , DESCRIPCION) VALUES (1, '1', '1', 'MATUTINO', '30 MIN TROTE SUAVE')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO ACTIVIDADES(ID, SEMANA, DIA, TURNO , DESCRIPCION) VALUES (2, '1', '2', 'VESPERTINO', '20 MIN TROTE + 10 CUESTAS')";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO PLANES_DETALLE(IDENTRENADOR, IDCORREDOR, IDPLAN) VALUES ('4', '2', 1)";
        db.execSQL(queryInsert);

        queryInsert = "INSERT INTO PLANES_DETALLE(IDENTRENADOR, IDCORREDOR, IDPLAN) VALUES ('4', '3', 2)";
        db.execSQL(queryInsert);
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

    public Boolean insertUsuario (String Tipo, Entrenador E, Corredor C, Login L){
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean usu = false;
        Boolean ent = false;
        Boolean corr = false;
        Boolean log = false;

        if (Tipo == "Entrenador") {

            ContentValues contenedor = new ContentValues();
            contenedor.put(ConstantesDB.TABLA_USUARIOS_USERNAME, E.getIdUsuario());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_NOMBRE, E.getNombre());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_FECHANACIMIENTO, E.getFechaNacimiento());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_CIUDAD, E.getCiudad());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_PAIS, E.getPais());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_EMAIL, E.getEmail());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_COMENTARIO, E.getComentario());

            ContentValues contenedor2 = new ContentValues();
            contenedor2.put(ConstantesDB.TABLA_USUARIOS_USERNAME, E.getIdUsuario());
            contenedor2.put(ConstantesDB.TABLA_ENTRENADORES_FORMACION, E.getFormacion());

            usu = db.insert("USUARIOS", null, contenedor) > 0;
            ent = db.insert("ENTRENADORES", null, contenedor2) > 0;

        }else {

            ContentValues contenedor = new ContentValues();
            contenedor.put(ConstantesDB.TABLA_USUARIOS_USERNAME,C.getIdUsuario());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_NOMBRE, C.getNombre());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_FECHANACIMIENTO, C.getFechaNacimiento());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_CIUDAD, C.getCiudad());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_PAIS, C.getPais());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_EMAIL, C.getEmail());
            contenedor.put(ConstantesDB.TABLA_USUARIOS_COMENTARIO, C.getComentario());

            ContentValues contenedor2 = new ContentValues();
            contenedor2.put(ConstantesDB.TABLA_USUARIOS_USERNAME, C.getIdUsuario());
            contenedor2.put(ConstantesDB.TABLA_CORREDORES_PESO,C.getPeso());
            contenedor2.put(ConstantesDB.TABLA_CORREDORES_GENERO,C.getGenero());
            contenedor2.put(ConstantesDB.TABLA_CORREDORES_ALTURA,C.getAltura());
            contenedor2.put(ConstantesDB.TABLA_CORREDORES_FCREPOSO,C.getFCReposo());
            contenedor2.put(ConstantesDB.TABLA_CORREDORES_FCMAXIMA,C.getFCMaxima());
            contenedor2.put(ConstantesDB.TABLA_CORREDORES_OBJETIVO,C.getDistanciaObjetivo());
            contenedor2.put(ConstantesDB.TABLA_CORREDORES_TIEMPOESTIMADO,C.getTiempoEstimado());

            usu = db.insert("USUARIOS", null, contenedor) > 0;
            corr = db.insert("CORREDORES", null, contenedor2) > 0;
        }
        ContentValues contenedor3 = new ContentValues();
        contenedor3.put(ConstantesDB.TABLA_USUARIOS_USERNAME, L.getUsername());
        contenedor3.put(ConstantesDB.TABLA_LOGIN_PASSWORD, L.getPassword());

        log = db.insert("LOGIN", null, contenedor3) > 0;
        db.close();

        if (Tipo == "Entrenador" & usu == true & ent == true & log == true){
            return true;
        }else if (Tipo == "Corredor" & usu == true & corr == true & log == true){
            return true;
        }else {
            return false;
        }

    }

    public void inicio(){ }

    public Cursor obtenerCorredores(String ent){
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT USERNAME FROM USUARIOS", null);
        return cursor;
    }

    public Cursor obtenerPlanes(String ent){
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT T1.NOMBRE AS NOMBRE   " +
                        " FROM PLANES_ENTRENAMIENTOS T1 " +
                        "JOIN PLANES_DETALLE T2 ON T1.ID = T2.IDPLAN " +
                        "WHERE T2.IDENTRENADOR = ?",new String[]{ent});
        int hola = cursor.getCount();
        return cursor;
    }

    public Boolean insertNuevoPlan (PlanEntrenamiento P, String ent){
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean plan = false;

        ContentValues contenedor = new ContentValues();
        contenedor.put(ConstantesDB.TABLA_PLANES_ID, P.getId());
        contenedor.put(ConstantesDB.TABLA_PLANES_NOMBRE, P.getNombre());
        contenedor.put(ConstantesDB.TABLA_PLANES_DISTANCIA, P.getDistancia());
        contenedor.put(ConstantesDB.TABLA_PLANES_OBJETIVO, P.getObjetivo());
        contenedor.put(ConstantesDB.TABLA_PLANES_COMENTARIO, P.getComentario());

        plan = db.insert("PLANES_ENTRENAMIENTOS", null, contenedor) > 0;
        db.close();

        return plan;
    }

    public Boolean insertActividad (Actividad A){
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean actividad = false;

        ContentValues contenedor = new ContentValues();
        contenedor.put(ConstantesDB.TABLA_ACTIVIDADES_SEMANA, A.getSemana());
        contenedor.put(ConstantesDB.TABLA_ACTIVIDADES_DIA, A.getDia());
        contenedor.put(ConstantesDB.TABLA_ACTIVIDADES_TURNO, A.getTurno());
        contenedor.put(ConstantesDB.TABLA_ACTIVIDADES_DESCRIPCION, A.getDescripcion());

        actividad = db.insert("ACTIVIDADES", null, contenedor) > 0;
        db.close();

        return actividad;
    }

    public Boolean insertDetallePlan (String idEntrenador, String idCorredor, Integer idPlan){
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean detalleplan = false;

        ContentValues contenedor = new ContentValues();
        contenedor.put(ConstantesDB.TABLA_PLANESDETALLE_IDENT, idEntrenador);
        contenedor.put(ConstantesDB.TABLA_PLANESDETALLE_IDCORR, idCorredor);
        contenedor.put(ConstantesDB.TABLA_PLANESDETALLE_IDPLAN, idPlan);

        detalleplan = db.insert("PLANES_DETALLE", null, contenedor) > 0;
        db.close();

        return detalleplan;
    }
}
