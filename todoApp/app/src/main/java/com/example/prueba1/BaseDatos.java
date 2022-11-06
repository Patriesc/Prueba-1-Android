package com.example.prueba1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Datos.class},version = 1,exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {

        private static BaseDatos db1;

        private static String DATABASE_NAME="database";

        public  synchronized static BaseDatos getInstance(Context context){
            if(db1 ==null){
                //when db is null
                //initialize dab
                db1 = Room.databaseBuilder(context.getApplicationContext(),BaseDatos.class,DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }

            return db1;
        }

        public abstract Crud crud();

    }
