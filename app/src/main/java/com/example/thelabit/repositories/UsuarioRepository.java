package com.example.thelabit.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.thelabit.database.AppDatabase;
import com.example.thelabit.modelo.daos.UsuarioDao;
import com.example.thelabit.modelo.entities.Usuario;

import java.util.List;

public class UsuarioRepository {
    private UsuarioDao usuarioDao;

    private LiveData<List<Usuario>> usuarios;

    public UsuarioRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        usuarioDao = db.UsuarioDao();
        usuarios = usuarioDao.getAll();
    }

    public LiveData<List<Usuario>> getUsuarios(){
        return usuarios;
    }

    public void insert(Usuario usuario){
        usuarioDao.insert(usuario);
    }
}
