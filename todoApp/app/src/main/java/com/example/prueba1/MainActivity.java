package com.example.prueba1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button botonAñadir, botonReset;
    RecyclerView recyclerView;


    List<Datos> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    BaseDatos database;

    Adaptador mainAdapter;

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        botonAñadir = findViewById(R.id.bt_add);
        botonReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        // Iniciamos la base de datos
        database = BaseDatos.getInstance(this);
        dataList = database.crud().getAll();

        // Iniciamos Layout
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Iniciamos adaptador
        mainAdapter = new Adaptador(dataList, MainActivity.this);
        //set adapter
        recyclerView.setAdapter(mainAdapter);

        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sText = editText.getText().toString().trim();
                if (!sText.equals("")) {
                    Datos data = new Datos();
                    data.setTexto(sText);
                    database.crud().insert(data);


                    editText.setText("");

                    dataList.clear();
                    Toast.makeText(MainActivity.this, "¡Tarea añadida!", Toast.LENGTH_LONG).show();

                    dataList.addAll(database.crud().getAll());
                    mainAdapter.notifyDataSetChanged();

                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("¡Debes introducir texto!")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Alerta de acción no válida");
                    alert.show();
                }
            }
        });
        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(v.getContext());
                //Setting message manually and performing action on button click
                builder.setMessage("¿Seguro que quieres borrar todas las tareas?")
                        .setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                database.crud().reset(dataList);
                                dataList.clear();
                                dataList.addAll(database.crud().getAll());
                                mainAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Confirmación de Reset");
                alert.show();

            }
        });
    }
}