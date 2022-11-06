package com.example.prueba1;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Datos implements Serializable {

    @Entity(tableName = "table_name")
    public class MainData implements Serializable {

        // Definimos el modelo de datos
        @PrimaryKey(autoGenerate = true)
        private int ID;

        @ColumnInfo(name = "texto")
        private String texto;


        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }
    }
}
