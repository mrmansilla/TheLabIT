package com.example.thelabit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.thelabit.modelo.daos.UsuarioDao;
import com.example.thelabit.modelo.entities.Usuario;

@Database(entities = {Usuario.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UsuarioDao UsuarioDao();

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance( final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, name:"runupbbdd").build();
        }
        return instance;
    }
}
