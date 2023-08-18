package com.example.threadsclase2_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView contador;
    Integer valor=0;
    Button btn1;
    Button btnAct2;
    Button btnFactorial;
    Button btnHorse;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Thread contadorThread;
    private volatile boolean isRunning = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = findViewById(R.id.contador);
        btn1 = findViewById(R.id.btn1);
        btnAct2 = findViewById(R.id.btnAct2);
        btnFactorial = findViewById(R.id.btnFactorial);
        btnHorse = findViewById(R.id.btnHorse);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });

        contadorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (valor<700 && isRunning){
                    valor++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            contador.setText("Contador: "+ valor );
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        contadorThread.start();
        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        btnFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PalindromeActivity.class);
                startActivity(intent);
            }
        });
        btnHorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HorseRaceActivity.class);
                startActivity(intent);
            }
        });
    }
}