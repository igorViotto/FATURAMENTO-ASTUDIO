package br.unifaj.poo.aps_computacao_a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPref =
                getSharedPreferences(MainActivity.usuario, Context.MODE_PRIVATE);

        TextView usernameTV = findViewById(R.id.Menu_TextView_User);
        String username = ("BEM VINDO(A),\n" + MainActivity.usuario);
        usernameTV.setText(username);
    }

    public void visualizarView(View v){
        Intent i = new Intent(this, VisualizarActivity.class);
        startActivity(i);
    }

    public void cargasView(View v){
        Intent i = new Intent(this, CargasCadastradasActivity.class);
        startActivity(i);
    }

    public void faturamentoView(View v){
        Intent i = new Intent(this, FaturamentoActivity.class);
        startActivity(i);
    }

    public void sair(View v){
        finish();
    }
}