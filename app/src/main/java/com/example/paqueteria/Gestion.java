package com.example.paqueteria;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Gestion extends AppCompatActivity {

    TextView tvContinente, tvPais;
    EditText nombre, peso, costoEnvio;
    Spinner continente, pais;
    AdminSQLiteOpenHelper admin;
    ContentValues registro;
    SQLiteDatabase bd;
    String stNombre, stPeso, stContinente, stPais, stCostoEnvio;
    int nuContinente, nuPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion);

        nombre = (EditText)findViewById(R.id.etNombre);
        peso = (EditText)findViewById(R.id.etPeso);
        costoEnvio = (EditText)findViewById(R.id.etCostoEnvio);
        continente = (Spinner)findViewById(R.id.spContinente);
        pais = (Spinner)findViewById(R.id.spPais);

        tvContinente = (TextView)findViewById(R.id.tvContinente);
        tvPais = (TextView)findViewById(R.id.tvPais);



        String[] continentes = {"América del Norte", "América Central", "América del Sur", "Europa", "Asia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, continentes);
        continente.setAdapter(adapter);

        continente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                switch (position){
                    case 0:
                        Pais("americaN");
                        break;
                    case 1:
                        Pais("americaC");
                        break;
                    case 2:
                        Pais("americaS");
                        break;
                    case 3:
                        Pais("europa");
                        break;
                    case 4:
                        Pais("asia");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


    }

    public void Pais(String continente){

        if(continente.equals("americaN")){
            String[] paises = {"Canada","Estados Unidos", "Mexico"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, paises);
            pais.setAdapter(adapter1);
        }
        if(continente.equals("americaC")){
            String[] paises = {"Belice", "Costa Rica", "El Salvador", "Guatemala", "Honduras", "Nicaragua", "Panamá"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, paises);
            pais.setAdapter(adapter1);
        }
        if(continente.equals("americaS")){
            String[] paises = {"Argentina", "Bolivia", "Brasil", "Chile", "Ecuador", "Guyana", "Paraguay", "Perú", "Surinam", "Uruguay", "Venezuela"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, paises);
            pais.setAdapter(adapter1);
        }
        if(continente.equals("europa")){
            String[] paises = {"Albania", "Alemania", "Andorra", "Armenia", "Austria", "Azerbaiyán", "Belgica","Bielorrusia", "Bosnia", "Bulgaria", "Chipre",
                    "Ciudad del Vaticano", "Croacia", "Dinamarca", "Eslovaquia", "Eslovenia", "España", "Estonia", "Federación Rusa", "Finlandia", "Francia",
                    "Georgia", "Grecia", "Hungría", "Irlanda", "Islandia", "Italia", "Kazajistán", "Letonia", "Liechtenstein", "Lituania", "Luxemburgo",
                    "Macedonia del Norte", "Malta", "Moldova","Mónaco", "Montenegro", "Noruega", "Países Bajos", "Polonia", "Portugal", "Reino Unido",
                    "República Checa", "Rumania", "Rusia", "San Marino", "Serbia", "Suecia", "Suiza", "Turquía","Ucrania"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, paises);
            pais.setAdapter(adapter1);
        }
        if(continente.equals("asia")){
            String[] paises = {"Afganistán", "Arabia Saudita", "Bangladés", "Baréin", "Birmania", "Brunéi", "Bután", "Camboya", "Catar", "China", "Corea del Norte",
                    "Corea del Sur", "Emiratos Árabes Unidos", "Filipinas", "India", "Indonesia", "Irak", "Irán", "Israel", "Japón", "Jordania", "Kirguistán", "Kuwait",
                    "Laos", "Líbano", "Malasia", "Maldivas", "Mongolia", "Nepal", "Omán", "Pakistán", "Singapur", "Siria", "Sri Lanka", "Tailandia", "Tayikistán",
                    "Timor Oriental", "Turkmenistán", "Turquía", "Uzbekistán", "Vietnam", "Yemen"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, paises);
            pais.setAdapter(adapter1);
        }

    }

    public void insertar(View view){

        admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        bd = admin.getWritableDatabase();

        stNombre = nombre.getText().toString();
        stPeso = peso.getText().toString();
        nuContinente = continente.getSelectedItemPosition();
        stContinente = String.valueOf(nuContinente);

        /*nuPais = pais.getSelectedItemPosition();
        stPais = String.valueOf(nuPais);
        stCostoEnvio = costoEnvio.getText().toString();*/

        stPais = pais.getSelectedItem().toString();


        registro = new ContentValues();
        registro.put("nombre", stNombre);
        registro.put("peso", stPeso);
        registro.put("continente", stContinente);
        registro.put("pais", stPais);
        registro.put("costoEnvio", stCostoEnvio);

        bd.insert("envios", null, registro);
        bd.close();

        Toast.makeText(this, "Se han guardado los datos del paquete " + stNombre+ " con Exito", Toast.LENGTH_SHORT).show();

        nombre.setText("");
        peso.setText("");
        costoEnvio.setText("");
    }

    public void Consulta(View view){
        admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        bd = admin.getWritableDatabase();
        stNombre = nombre.getText().toString();
        Cursor fila = bd.rawQuery("select peso, continente, pais, costoEnvio from envios where nombre=" + stNombre, null);
        if(fila.moveToFirst()){
            peso.setText(fila.getString(0));
            tvContinente.setText(fila.getString(1));
            stContinente = fila.getString(1);
            nuContinente = Integer.parseInt(stContinente);
            continente.setSelection(nuContinente);
            tvPais.setText(fila.getString(2));
            costoEnvio.setText(fila.getString(3));
        } else
            Toast.makeText(this,"No se encontrarón registros", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void Ipais(){
        if (continente != null){
            pais.setSelection(nuPais);
        }
    }

    public void Modificar(View view){
        admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        bd = admin.getWritableDatabase();

        stNombre = nombre.getText().toString();
        stPeso = peso.getText().toString();
        nuContinente = continente.getSelectedItemPosition();
        stContinente = String.valueOf(nuContinente);
        nuPais = pais.getSelectedItemPosition();
        stPais = String.valueOf(nuPais);
        stCostoEnvio = costoEnvio.getText().toString();

        registro = new ContentValues();
        registro.put("peso", stPeso);
        registro.put("continente", stContinente);
        registro.put("pais", stPais);
        registro.put("costoEnvio", stCostoEnvio);

        int cant = bd.update("envios", registro,"nombre=" + stNombre, null);
        bd.close();
        if (cant == 1){
            Toast.makeText(this, "Se modificarón los datos exitosamente", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            peso.setText("");
            costoEnvio.setText("");
        } else {
            Toast.makeText(this, "Nombre no encontrada", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View view){
        admin = new AdminSQLiteOpenHelper(this,"administracion", null,1);
        bd = admin.getWritableDatabase();
        stNombre = nombre.getText().toString();
        int cant = bd.delete("envios", "nombre=" + stNombre, null);

        if (cant == 1){
            Toast.makeText(this, "Los datos registrados con la cedula " +stNombre +" fueron eliminados", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            peso.setText("");
            costoEnvio.setText("");
        } else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_LONG).show();
        }
    }


}
