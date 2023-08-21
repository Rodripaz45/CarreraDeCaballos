package com.example.threadsclase2_8.models;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.threadsclase2_8.HorseRaceActivity;
import com.example.threadsclase2_8.R;


public class Horse implements Runnable {
    private final ImageView horseImage;
    public TextView txtWinner;
    public LinearLayout horsesLayout;
    public TextView progressTextView;
    public TextView txtNombre;
    private Singleton singletonInstance = Singleton.getInstance();
    private volatile boolean shouldStop = false;
    private static final int MAX_DISTANCE = 1000;
    private static final int[] HORSE_IMAGE_RESOURCES = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10
    };
    private HorseRaceActivity horseRaceActivity;
    public int distanceTraveled = 0;

    public static final String[] HORSE_NAME_RESOURCES = {
            "Ronaldinho",
            "Jugador",
            "Caballito",
            "Iniesta",
            "Iron Man",
            "Messi 1",
            "Messi 2",
            "O Rei Pele",
            "Mujer",
            "Zizou"
    };

    @SuppressLint("CutPasteId")
    public Horse(View view, int horseImageIndex, TextView txtWinner, HorseRaceActivity horseRaceActivity) {
        horseImage = view.findViewById(R.id.horseImage);
        horsesLayout = view.findViewById(R.id.racetrackLayout);
        progressTextView = view.findViewById(R.id.progressTextView);
        txtNombre = view.findViewById(R.id.txtNombre);
        this.horseRaceActivity = horseRaceActivity;
        this.txtWinner = txtWinner;

        if (horseImageIndex >= 0 && horseImageIndex < HORSE_IMAGE_RESOURCES.length) {
            horseImage.setImageResource(HORSE_IMAGE_RESOURCES[horseImageIndex]);
            txtNombre.setText(HORSE_NAME_RESOURCES[horseImageIndex]);
        } else {
            // Índice inválido, establece una imagen predeterminada o maneja el error
            horseImage.setImageResource(R.drawable.image1);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void run() {
        int distance = 0;
        while (distance < MAX_DISTANCE && !shouldStop) {
            try {
                Thread.sleep((long) (Math.random() * 200)); // Simula el tiempo que tarda el caballo en avanzar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            distance += (int) (Math.random() * 10);
            distanceTraveled = distance;
            int finalDistance = Math.min(distance, MAX_DISTANCE);
            float progress = (float) finalDistance / MAX_DISTANCE;

            // Actualiza el porcentaje en el TextView en la interfaz gráfica
            progressTextView.post(() -> {
                int percentage = (int) (progress * 100);
                progressTextView.setText(percentage + "%");
            });

            // Actualiza la posición de la imagen del caballo
            horseImage.post(() -> {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) horseImage.getLayoutParams();
                params.leftMargin = (int) (progress * (horsesLayout.getWidth() - horseImage.getWidth()));
                horseImage.setLayoutParams(params);
                txtNombre.setLayoutParams(params);
            });

        }
        if (!singletonInstance.isWinner()) {
            horseRaceActivity.runOnUiThread(() -> {
                horsesLayout.setBackgroundColor(R.color.green);
                txtWinner.setText("El ganador es: " + txtNombre.getText().toString());
            });
            singletonInstance.setWinner(true);
            horseRaceActivity.stopAll();
        }

    }

    public void stop(){
        shouldStop = true;
    }
}
