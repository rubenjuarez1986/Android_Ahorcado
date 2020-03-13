package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahorcado.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String palabraOculta = eligePalabra();
    int numeroDeFallos = 0;
    boolean partidaTerminada = false;
    String palabraGuiones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.ventanaJuego, new VentanaAhorcado()).commit();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        palabraGuiones="";
        for (int i = 0; i < palabraOculta.length(); i++) {
            if(palabraOculta.charAt(i)!=' ')palabraGuiones += "_ ";
            else {
                palabraGuiones+="  ";
            }
        }
        ((TextView)findViewById(R.id.palabraConGuiones)).setText(palabraGuiones);

    }
    //con esta clase hacemos que que tenga mas palabras donde elegir
    private String eligePalabra() {
        String[] listaPalabras = {"clase", "nombre", "ruben","pilotes","croquetas","coronavirus"};
        Random aleatorio = new Random();
        int pos = aleatorio.nextInt(listaPalabras.length);
        return listaPalabras[pos].toUpperCase();
    }


    public void botonPulsado(View vista) {
        Button boton = findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);
        chequeaLetra(boton.getText().toString());
    }


    private void chequeaLetra(String letra) {


        if (!partidaTerminada) {
            ImageView imagenAhorcado = ((ImageView) findViewById(R.id.imagenAhorcado));
            TextView textoConGuiones = ((TextView) findViewById(R.id.palabraConGuiones));

            boolean acierto = false;

            for (int i = 0; i < palabraOculta.length(); i++) {
                if (palabraOculta.charAt(i) == letra.charAt(0)) {
                    palabraGuiones = palabraGuiones.substring(0, 2 * i) + letra + palabraGuiones.substring(2 * i + 1);
                    acierto = true;
                }
            }
            if (!palabraGuiones.contains("_")) {
                imagenAhorcado.setImageResource(R.drawable.acertastetodo);
                partidaTerminada=true;
            }

            textoConGuiones.setText(palabraGuiones);

            if (!acierto) {
                numeroDeFallos++;
                switch (numeroDeFallos) {
                    case 0:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_0);
                        break;
                    case 1:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_1);
                        break;
                    case 2:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_2);
                        break;
                    case 3:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_3);
                        break;
                    case 4:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_4);
                        break;
                    case 5:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_5);
                        break;
                    case 6:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_fin);
                        partidaTerminada=true;
                        break;
                }
            }

        }


    }
}