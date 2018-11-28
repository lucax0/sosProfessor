package Controller.Usuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.br.fatec.sos_professores.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import Model.Usuario.Usuario;
import Utils.MaskEditUtil;


public class cadastroActivity extends AppCompatActivity {

    private EditText mEmailView ,mPasswordView, mNomeView,mDataView, mTelefoneView;

    private boolean existeUsuario;
    String identificacaoUsuario;
    //instancia banco
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icadastro);
        incialiazarCampos();
        Button btn_salvar = findViewById(R.id.btn_submit);
            btn_salvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    criarModel();
                }
            });
         mTelefoneView.addTextChangedListener(MaskEditUtil.mask(mTelefoneView, MaskEditUtil.FORMAT_FONE));
    }

    public void incialiazarCampos(){
        mNomeView = findViewById(R.id.txt_nome);
        mEmailView =  findViewById(R.id.txt_email);
        mPasswordView =  findViewById(R.id.txt_senha);
        mDataView = findViewById(R.id.txt_nasc);
        mTelefoneView = findViewById(R.id.txt_num);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {return password.length() >= 6;
    }

    protected Boolean isCamposValidos() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            return false;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            return false;
        }
        if (TextUtils.isEmpty((password))) {
            mPasswordView.setError(getString(R.string.error_field_required));
            mPasswordView.setText("");
            return false;

        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            mPasswordView.setText("");
            return false;
        }
        return true;
    }

    protected void criarModel(){
        Usuario usuario = new Usuario() {
        };
        if (isCamposValidos()) {
            usuario.setEmail(mEmailView.getText().toString());
            usuario.setNome(mNomeView.getText().toString());
            usuario.setCel(mTelefoneView.getText().toString());
            usuario.setEmail(mEmailView.getText().toString());
            usuario.setCep("09810-360");
            usuario.setSexo("Masculino");
            usuario.setDtNasc(new Date());
            usuario.setSenha(mPasswordView.getText().toString());
            criarUsuario(usuario);
        }
    }

    protected void criarUsuario(final Usuario usuario){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Passando infos banco
                        usuario.setId(mAuth.getCurrentUser().getUid());
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference DB = database.getReference("usuarios").child(mAuth.getCurrentUser().getUid());
                        DB.child("id").setValue(mAuth.getCurrentUser().getUid());
                        DB.child("email").setValue(usuario.getEmail());
                        DB.child("senha").setValue(usuario.getSenha());
                        DB.child("nome").setValue(usuario.getNome());
                        DB.child("dtNasc").setValue(usuario.getDtNasc());
                        DB.child("sexo").setValue(usuario.getSexo());
                        DB.child("cep").setValue(usuario.getCep());
                        DB.child("cel").setValue(usuario.getCel());
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
