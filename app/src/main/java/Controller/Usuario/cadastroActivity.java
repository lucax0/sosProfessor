package Controller.Usuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.br.fatec.sos_professores.R;
import com.google.firebase.auth.FirebaseAuth;

public class cadastroActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mNomeView;
    private RadioGroup mSexoView;
    private EditText mDataView;
    private EditText mTelefoneView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        mNomeView = findViewById(R.id.txt_nome);
        mEmailView =  findViewById(R.id.txt_email);
        mPasswordView =  findViewById(R.id.txt_senha);
        mSexoView = findViewById(R.id.rdn_grupS);
        mDataView = findViewById(R.id.txt_nasc);
        mTelefoneView = findViewById(R.id.txt_num);

    }
}
