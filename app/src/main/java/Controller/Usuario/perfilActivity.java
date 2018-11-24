package Controller.Usuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.br.fatec.sos_professores.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class perfilActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView mNome;//Chamando o campo da view
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilaluno);
        mNome = (TextView) findViewById(R.id.txt_nome);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        email = currentUser.getEmail();
        carregarCampos();
    }

    public void carregarCampos(){
        mNome.setText(email);
    }
}
