package br.unifaj.poo.aps_computacao_a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CargasCadastradasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cargas_cadastradas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Criei um ArrayAdapter, vai ser colocado no ListView, trocando o adapter dele.
        //Fazendo isso pq o adapter fornecido é estático, esse é dinamico.
        ArrayList<String> lista = carregarDados(null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, lista);
        ListView listView = findViewById(R.id.CargasCadastradas_ListView_Lista);
        listView.setAdapter(adapter);
        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //listView.setAdapter(adapter);
    }

    private ArrayList<String> carregarDados(View v) {
        SharedPreferences sharedPref = getSharedPreferences("meus_dados", Context.MODE_PRIVATE);
        String json = sharedPref.getString("minha_lista_json", null);
        ArrayList<String> lista = new ArrayList<>();

        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    lista.add(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }



    /* public void carregar(View v) {
        ListView listView = findViewById(R.id.CargasCadastradas_ListView_Lista);

        // Referencia ao input de dados.
        ArrayList<String> itens = new ArrayList<>();


        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        adapter.clear();

    }*/
    private void salvarDados(ArrayList<String> lista) {
        JSONArray cargasJson = new JSONArray(lista);
        SharedPreferences sharedPref = getSharedPreferences(ValoresCargas.arquivo, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ValoresCargas.arquivo, cargasJson.toString());
        editor.apply();
    }

    public void excluir(View v){
        ListView listView = findViewById(R.id.CargasCadastradas_ListView_Lista);
        ArrayList<String> lista = carregarDados(null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, lista);
        SparseBooleanArray itensSelecionados = listView.getCheckedItemPositions();
        ArrayList<String> novaLista = new ArrayList<>();

        List<String> selecionados = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (!itensSelecionados.get(i)) {
                novaLista.add(lista.get(i));
            }
        }
        salvarDados(novaLista); // Atualiza os dados salvos
        lista.clear();
        lista.addAll(novaLista);
        adapter.notifyDataSetChanged();

        listView.clearChoices();
    }

    public void adicionar(View v){
        Intent i = new Intent(this, CadastrarCargasActivity.class);
        startActivity(i);
    }

    public void voltar(View v){
        finish();
    }





}