package Controller.Usuario;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.br.fatec.sos_professores.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Model.Usuario.Professor;

public class buscaprofessorActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private Query databaseReference;
    Professor professor;
    private EditText mID;
    private TextView mNomeView, mMateria, mNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibuscaprofessor);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mID = findViewById(R.id.txt_id);
        mMateria = findViewById(R.id.txt_materia);
        mNota = findViewById(R.id.txt_nota);
        mNomeView = findViewById(R.id.txt_nome);
        Button btn_busca = findViewById(R.id.btn_busca);
        btn_busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
    }

    public void buscar(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getReference("professores").child(mID.getText().toString());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                professor = dataSnapshot.getValue(Professor.class);
                System.out.println(professor.getNome());
                carregarCampos();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                mID.setError(getString(R.string.erro_idBusca));
                mID.requestFocus();
                System.out.println("The read failed: " + databaseError.getCode());
                System.out.println("o erro é " + databaseError.getMessage());
            }
        });
    }

    public void carregarCampos(){
        mNomeView.setText(professor.getNome());
        mMateria.setText(professor.getGraduacao());
        mNota.setText(String.valueOf(professor.getNota())) ;
    }

}
