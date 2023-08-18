package com.example.threadsclase2_8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity2 extends AppCompatActivity {

    Handler handler;
    List<Runnable> list;
    int currentIndex = 0;
    TextView txt;
    int tiempo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        handler = new Handler(Looper.getMainLooper());
        list = new ArrayList<>();
        txt = findViewById(R.id.textView);

        // Agregar los 10 Runnable a la lista con un retraso de 4000ms entre cada uno
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            list.add(new Runnable() {
                @Override
                public void run() {

                    txt.setText("Proceso: "+finalI+"\n Tiempo: "+tiempo);
                    // Avanzar al siguiente Runnable en la lista circular
                    currentIndex = (currentIndex + 1) % list.size();
                    // Ejecutar el siguiente Runnable de la lista
                    tiempo++;
                    handler.postDelayed(list.get(currentIndex), 1000);
                }
            });
        }

        // Ejecutar el primer Runnable de la lista
        handler.postDelayed(list.get(currentIndex), 1000);
    }

    private void stopProcessWithHandler() {
        // Eliminar cualquier Runnable que aún pueda estar en la cola del Handler
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopProcessWithHandler();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

