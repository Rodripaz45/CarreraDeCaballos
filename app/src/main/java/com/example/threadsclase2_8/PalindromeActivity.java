package com.example.threadsclase2_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PalindromeActivity extends AppCompatActivity {

    Handler handler;
    EditText numberEditText;
    Button btnCalculate;
    TextView txtResult;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);
        numberEditText = findViewById(R.id.numberEditText);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);
        handler = new Handler(Looper.getMainLooper());

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numberEditText.getText().toString();
                PalindromeChecker palindromeChecker = new PalindromeChecker(input);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<String> futureResult = executorService.submit(palindromeChecker);
                executorService.shutdown();
                handleResult(futureResult);
            }
        });
    }

    private void handleResult(Future<String> futureResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String palindromeResult = futureResult.get();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtResult.setText(palindromeResult);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}