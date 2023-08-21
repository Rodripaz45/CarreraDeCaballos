package com.example.threadsclase2_8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.threadsclase2_8.models.Horse;
import com.example.threadsclase2_8.models.Singleton;

public class HorseRaceActivity extends AppCompatActivity {
    private static final int NUM_CABALLOS = 10;
    private Horse[] horses = new Horse[NUM_CABALLOS];
    private Singleton singletonInstance = Singleton.getInstance();
    TextView txtWinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horse_race);
        LinearLayout layout = findViewById(R.id.horsesLayout);
        txtWinner = findViewById(R.id.txtWinner);
        for (int i = 0; i < NUM_CABALLOS; i++) {
            View horseView = getLayoutInflater().inflate(R.layout.horse_layout, layout, false);
            horses[i] = new Horse(horseView, i, txtWinner, this);
            layout.addView(horseView);
        }

        Button startRaceButton = findViewById(R.id.startRaceButton);
        startRaceButton.setOnClickListener(v -> startRace());
        Spinner horseSpinner = findViewById(R.id.horseSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        for (int i = 0; i < NUM_CABALLOS; i++) {
            spinnerAdapter.add(Horse.HORSE_NAME_RESOURCES[i]);
        }
        horseSpinner.setAdapter(spinnerAdapter);

        Button stopRaceButton = findViewById(R.id.stopRaceButton);
        stopRaceButton.setOnClickListener(v -> {
            int selectedHorseIndex = horseSpinner.getSelectedItemPosition();
            stopRace(selectedHorseIndex);
        });
    }

    private void startRace() {
        for (Horse horse : horses) {
            new Thread(horse).start();
        }
    }
    private void stopRace(int selectedHorseIndex) {
        horses[selectedHorseIndex].stop();
    }
    @SuppressLint("ResourceAsColor")
    public void stopAll(){
        int minDistance = 1001;
        Horse losingHorse = null;
        for (Horse hors : horses) {
            hors.stop();
            if (hors.distanceTraveled < minDistance) {
                minDistance = hors.distanceTraveled;
                losingHorse = hors;
            }
        }
        if (losingHorse != null) {
            System.out.println("el caballo perdedor es " + losingHorse.txtNombre.getText().toString());
        }
    }
}