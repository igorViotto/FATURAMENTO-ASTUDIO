package br.unifaj.poo.aps_computacao_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static String usuario;

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
    }

    // Método para LOGIN
    public void entrar(View v){
        EditText usuarioET = findViewById(R.id.Main_EditText_Usuario);
        EditText senhaET = findViewById(R.id.Main_EditText_Senha);
        String usuario = usuarioET.getText().toString();
        String senha = senhaET.getText().toString();

        if (!usuario.isBlank() && !senha.isBlank()) {
            Intent i = new Intent(this, MenuActivity.class); // Função que autentica e vai para próxima tela.
            startActivity(i);
            MainActivity.usuario = usuario;
            }
        else {
            Toast.makeText(this, "Usuário e senha devem ser preenchidos", Toast.LENGTH_LONG).show();
        }
    }

}