package com.example.wsqlitedanielpadilla;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    daoContacto dao;
    Adaptador adapter;
    ArrayList<Contacto>lista;
    Contacto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao= new daoContacto(MainActivity.this);
        lista=dao.verTodo();
        adapter=new Adaptador(lista, dao, this);
        ListView list = findViewById(R.id.listUsuario);
        Button insertar = findViewById(R.id.btnInsertar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText nombre = dialog.findViewById(R.id.txtNombre);
                final EditText apellido = dialog.findViewById(R.id.txtApellido);
                final EditText email = dialog.findViewById(R.id.txtEmail);
                final EditText telefono = dialog.findViewById(R.id.txtTelefono);
                final EditText ciudad = dialog.findViewById(R.id.txtCiudad);
                Button guardar = dialog.findViewById(R.id.btnAgregar);
                guardar.setText("Agregar");
                Button cancelar = dialog.findViewById(R.id.btnCancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            c = new Contacto(-1,nombre.getText().toString(),
                                    apellido.getText().toString(),
                                    email.getText().toString(),
                                    telefono.getText().toString(),
                                    ciudad.getText().toString());
                            dao.insertar(c);
                            lista=dao.verTodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }


}