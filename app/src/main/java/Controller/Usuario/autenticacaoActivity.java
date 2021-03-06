package Controller.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.br.fatec.sos_professores.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class autenticacaoActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iautenticacao);

        //CARREGANDO VIEW
        mEmailView =  findViewById(R.id.txt_email);
        mPasswordView = findViewById(R.id.txt_senha);
        mAuth = FirebaseAuth.getInstance();

        //BOTOES
       Button btn_entrar =  findViewById(R.id.btn_entar);
        btn_entrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
       Button btn_cad =  findViewById(R.id.btn_cadastro);
        btn_cad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastro();
            }
        });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    protected void login(){
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if(TextUtils.isEmpty((password))){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            mPasswordView.setText("");
            cancel = true;
        } else if (!isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            mPasswordView.setText("");
            cancel = true;
        }

        if(!cancel){
            //Procedimentos de login
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent it = new Intent(autenticacaoActivity.this, menuActivity.class);
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(it);
                            } else {
                                mPasswordView.setError(getString(R.string.erro_generico));
                                mPasswordView.setText("");
                                mEmailView.setError(getString(R.string.erro_generico));
                                mEmailView.requestFocus();

                                Log.d("login", "signInWithEmail:failure", task.getException());
                            }

                        }
                    });
        }
    }

    protected void cadastro(){
        Intent it = new Intent(autenticacaoActivity.this, cadastroActivity.class);
        startActivity(it);
    }
}
