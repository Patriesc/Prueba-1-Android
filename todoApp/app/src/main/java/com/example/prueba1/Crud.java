package com.example.prueba1;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Crud {
    @Insert(onConflict = REPLACE)
    void insert(Datos datosMain);

    // Borrar
    @Delete
    void delete(Datos datosMain);

    // Borrar todo
    @Delete
    void reset(List<Datos> datosMain);

    //Actualizar
    @Query("UPDATE Table_name SET texto =:sText WHERE ID = :sID")
    void upate(int sID,String sText);

    // Devolver todos los datos
    @Query("SELECT * FROM table_name")
    List<Datos> getAll();
}
