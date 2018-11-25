package Controller.Usuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.br.fatec.sos_professores.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import Model.Usuario.Usuario;


public class cadastroActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mNomeView;
    private RadioGroup mSexoView;
    private EditText mDataView;
    private EditText mTelefoneView;


    private boolean existeUsuario;
    String identificacaoUsuario;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        incialiazarCampos();
        Button btn_salvar = findViewById(R.id.btn_submit);
            btn_salvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    criarModel();
                }
            });
    }

    public void incialiazarCampos(){
        mNomeView = findViewById(R.id.txt_nome);
        mEmailView =  findViewById(R.id.txt_email);
        mPasswordView =  findViewById(R.id.txt_senha);
        mSexoView = findViewById(R.id.rdn_grupS);
        mDataView = findViewById(R.id.txt_nasc);
        mTelefoneView = findViewById(R.id.txt_num);
    }

    protected void criarModel(){
        Usuario usuario = new Usuario() {
        };
            usuario.setEmail(mEmailView.getText().toString());
            usuario.setNome(mNomeView.getText().toString());
            usuario.setCel(mTelefoneView.getText().toString());
            usuario.setEmail(mEmailView.getText().toString());
            usuario.setCep("09810360");
            usuario.setDtNasc(new Date());
            usuario.setSenha(mPasswordView.getText().toString());
            criar(usuario);

    }

    protected void criar(final Usuario usuario){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Passando infos banco
                        usuario.setId(mAuth.getCurrentUser().getUid());
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference ref = database.getReference("usuarios").child(mAuth.getCurrentUser().getUid());
                        ref.child("uid").setValue(mAuth.getCurrentUser().getUid());
                        ref.child("nome").setValue(usuario.getNome());
                        ref.child("email").setValue(usuario.getEmail());
                        ref.child("senha").setValue(usuario.getSenha());
                        ref.child("telefone").setValue(usuario.getCel());
                        ref.child("cep").setValue(usuario.getCep());
                        ref.child("dataNasc").setValue(usuario.getDtNasc());
                        //Chamando view perfil
                        Intent it = new Intent(cadastroActivity.this, perfilActivity.class);
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(it);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("login", "signInWithEmail:failure", task.getException());
                    }
                }
            });
    }
}
