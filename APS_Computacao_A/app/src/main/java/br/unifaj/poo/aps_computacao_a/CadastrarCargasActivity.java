package br.unifaj.poo.aps_computacao_a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;

import java.util.ArrayList;

public class CadastrarCargasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrarcargas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static ArrayList<String> itensDaLista = new ArrayList<>();

    public void salvar(View v) {
        EditText ordemET = findViewById(R.id.CadastrarCarga_EditText_Ordem);
        EditText pesoET = findViewById(R.id.CadastrarCarga_EditText_Peso);
        EditText valorET = findViewById(R.id.CadastrarCarga_EditText_ValorCarga);

        String ordem = ordemET.getText().toString();
        String pesoST = pesoET.getText().toString();
        String valorST = valorET.getText().toString();

        if (!ordem.isBlank() && !pesoST.isBlank() && !valorST.isBlank()) {
            float pesoFLT = Float.parseFloat(pesoST);
            float valorFLT = Float.parseFloat(pesoST);


            SharedPreferences sharedPref =
                    getSharedPreferences(ValoresCargas.arquivo, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            String concatenada = ("ID: " + ordem + " | " + pesoST + "KG | R$: " + valorST);
            itensDaLista.add(concatenada);

            JSONArray cargasJson = new JSONArray(itensDaLista);
            editor.putString(ValoresCargas.arquivo, cargasJson.toString());

            editor.commit();

            Toast.makeText(this, "Informações salvas com sucesso", Toast.LENGTH_LONG).show();
            ordemET.setText("");
            pesoET.setText("");
            valorET.setText("");
            }
        Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
    }


    public void sair(View v){
        finish();
    }
}