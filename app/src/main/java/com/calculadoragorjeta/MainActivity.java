package com.calculadoragorjeta;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText textInputValor;
    private TextView textPorcentagem, textValorGorjeta, textValorTotal;
    private SeekBar seekBarPorcentagem;

    private double porcentagem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Configurar cor statusbar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.ciano));

        textInputValor = findViewById(R.id.textInputValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textValorGorjeta = findViewById(R.id.textValorGorjeta);
        textValorTotal = findViewById(R.id.textValorTotal);
        seekBarPorcentagem = findViewById(R.id.seekBarPorcentagem);

        seekBarPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textPorcentagem.setText(progress + "%");
                porcentagem = progress;
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){
        String valor = textInputValor.getText().toString();
        Double gorjeta, total;

        if(valor == null || valor.isEmpty()){
            Toast.makeText(getApplicationContext(), "Digite um valor primeiro", Toast.LENGTH_SHORT).show();
        }else{
            //Converter string pra double
            Double valorDouble = Double.parseDouble(valor);

            //Calculo
            gorjeta = valorDouble * (porcentagem/100);
            total = gorjeta + valorDouble;


            textValorGorjeta.setText("R$" + Math.round(gorjeta));
            textValorTotal.setText("R$" + Math.round(total));
        }
    }
}