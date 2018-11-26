package Controller.Usuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import Model.Usuario.Professor;
import Model.Usuario.Usuario;

public class cadastroProfActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private Query databaseReference;
    private EditText mGraduacao;
    private Usuario usuario;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icadastroprofessor);
        mGraduacao = findViewById(R.id.txt_grad);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        Button btn_salvar = findViewById(R.id.btn_submit);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarModel(usuario);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        databaseReference = firebaseDatabase.getReference("usuarios").child(mAuth.getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.getValue(Usuario.class);
                System.out.println(usuario.getNome());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                System.out.println("o erro é " + databaseError.getMessage());
            }
        });
    }

    protected void criarModel(Usuario usuario){
        Professor professor = new Professor() {
        };
            professor.setId(mAuth.getCurrentUser().getUid());
            professor.setEmail(usuario.getEmail());
            professor.setNome(usuario.getNome());
            professor.setCel(usuario.getCel());
            professor.setCep(usuario.getCep());
            professor.setSexo(usuario.getSexo());
            professor.setDtNasc(new Date());
            professor.setSenha(usuario.getSexo());
            professor.setNota(5);
            professor.setGraduacao(mGraduacao.getText().toString());
            criarUsuarioProfessor(professor);
    }

    protected void criarUsuarioProfessor(final Professor professor) {
        mAuth = FirebaseAuth.getInstance();
        //Passando infos banco
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DB = database.getReference("usuarios").child(mAuth.getCurrentUser().getUid());
        DB.child("id").setValue(mAuth.getCurrentUser().getUid());
        DB.child("email").setValue(professor.getEmail());
        DB.child("senha").setValue(professor.getSenha());
        DB.child("nome").setValue(professor.getNome());
        DB.child("dtNasc").setValue(professor.getDtNasc());
        DB.child("sexo").setValue(professor.getSexo());
        DB.child("cep").setValue(professor.getCep());
        DB.child("cel").setValue(professor.getCel());
        DB.child("graduacao").setValue(professor.getGraduacao());
        DB.child("nota").setValue(professor.getNota());
        //Chamando view perfil
        Intent it = new Intent(cadastroProfActivity.this, perfilActivity.class);
        FirebaseUser user = mAuth.getCurrentUser();
        startActivity(it);
    }
    
}
