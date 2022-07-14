package br.ufjf.dcc196.fabricioguidine.avistamentos;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdicionaEspecie extends AppCompatActivity {

    private Button buttonSalvar;
    private Button buttonCancelar;

    private EditText editTextNome;
    private EditText editTextEspecie;

    private String nome;
    private String especie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_especie);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonCancelar = findViewById(R.id.buttonCancelar);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEspecie = findViewById(R.id.editTextEspecie);
    }

    public void buttonSalvarClick(View view){

        nome = editTextNome.getText().toString();
        especie = editTextEspecie.getText().toString();

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(especie)){
            editTextNome.selectAll();
            editTextNome.requestFocus();

            Toast.makeText(this, "Favor inserir nome e especie!", Toast.LENGTH_SHORT).show();
            return;

        } else {
            Intent novaEspecie = new Intent();
            novaEspecie.putExtra("nome", nome);
            novaEspecie.putExtra("especie", especie);
            setResult(1, novaEspecie);
            finish();
        }

    }
    public void buttonCancelarClick(View view){
        finish();
    }
}