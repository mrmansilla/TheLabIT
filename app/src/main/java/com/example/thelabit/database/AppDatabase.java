package com.example.thelabit.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.thelabit.modelo.daos.UsuarioDao;
import com.example.thelabit.modelo.entities.Usuario;

@Database(entities = {Usuario.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao UsuarioDao();
}
