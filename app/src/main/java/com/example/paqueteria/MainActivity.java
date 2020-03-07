package com.example.paqueteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner continente, pais;
    TextView costo, costoEnvio;
    EditText peso;
    String stPeso, stCostoEnvio;
    Double nuPeso, nuCostoEnvio;
    Integer nuCosto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continente = (Spinner)findViewById(R.id.spContinente);
        pais = (Spinner)findViewById(R.id.spPais);
        costo = (TextView)findViewById(R.id.tvCosto);
        peso = (EditText)findViewById(R.id.etPeso);
        costoEnvio = (TextView) findViewById(R.id.tvC);

        String[] continentes = {"América del Norte", "América Central", "América del Sur", "Europa", "Asia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, continentes);
        continente.setAdapter(adapter);



        continente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        costo.setText("$3800");
                        nuCosto = 3800;
                        Pais("americaN");
                        break;
                    case 1:
                        costo.setText("$3100");
                        nuCosto = 3100;
                        Pais("americaC");
                        break;
                    case 2:
                        costo.setText("$2900");
                        nuCosto = 2900;
                        Pais("americaS");
                        break;
                    case 3:
                        costo.setText("$4200");
                        nuCosto = 4200;
                        Pais("europa");
                        break;
                    case 4:
                        costo.setText("$5300");
                        nuCosto = 5300;
                        Pais("asia");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void CalcularCosto(View view){

        stPeso = peso.getText().toString();
        nuPeso = Double.parseDouble(stPeso);

        nuCostoEnvio = nuPeso*nuCosto;
        stCostoEnvio = String.valueOf(nuCostoEnvio);


        if (nuPeso <= 5000){
            Toast.makeText(this, "Paquete procesado exitosamente", Toast.LENGTH_LONG).show();
            costoEnvio.setText(stCostoEnvio);
        } else {
            Toast.makeText(this, "Paquete superior a 5Kg, no puede ser transportado", Toast.LENGTH_LONG).show();
            stCostoEnvio = "";
            costoEnvio.setText(stCostoEnvio);
        }
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
}
