package com.example.thelabit.modelo.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thelabit.modelo.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query('SELECT * FROM usuario')
    LiveData<List<Usuario>> getAll();

    @Insert
    void insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Query('SELECT * FROM usuario where nombre like :nombre')
    Usuario findByNombre(String nombre);

    @Query('SELECT * FROM usuario where id = :id')
    Usuario findById(int id);
}
