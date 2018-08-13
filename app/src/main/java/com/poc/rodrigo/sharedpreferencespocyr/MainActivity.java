package com.poc.rodrigo.sharedpreferencespocyr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String PREF_NAME = "USER_PREFS";
    private static final String KEY_WORD = "USER_PREFS_KEY";
    private SharedPreferences mSharedPreferences;
    private Set<String> userSet;
    private Button btnSalvar;
    private EditText etNome;
    private EditText etClasse;
    private TextView tvPrefs;
    private Button btnNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnNome = findViewById(R.id.btnNome);
        etNome = findViewById(R.id.etNome);
        etClasse = findViewById(R.id.etClasse);
        tvPrefs =findViewById(R.id.tvPrefs);
        mSharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPreferences();

            }
        });
        btnNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = mSharedPreferences.getString(KEY_WORD,null);
                Jogador jogador1 = gson.fromJson(json,Jogador.class);
                Toast.makeText(MainActivity.this, jogador1.nome, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, Integer.toString(jogador1.nivel), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void salvarPreferences() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        Gson gson = new Gson();
        Jogador jogador = new Jogador();
        jogador.nome = etNome.getText().toString();
        jogador.classe = etClasse.getText().toString();
        jogador.nivel = 1;
        jogador.experiencia = 0;
        String jogadorString = gson.toJson(jogador);
        editor.putString(KEY_WORD,jogadorString);
        editor.apply();
    }
}
