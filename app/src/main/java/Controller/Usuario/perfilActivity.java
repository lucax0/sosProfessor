package Controller.Usuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.br.fatec.sos_professores.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class perfilActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmail;//Chamando a View
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iperfilaluno);
        mEmail = (EditText) findViewById(R.id.txt_email);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        email = currentUser.getEmail();
    }

    public void carregarCampos(){
        
    }
}
