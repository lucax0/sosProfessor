package Controller.Usuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.br.fatec.sos_professores.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;
import Model.Usuario.Usuario;


public class perfilActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private TextView mNome;//Chamando o campo da view
    private String email , nome;
    private Query databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilaluno);
        mNome = findViewById(R.id.txt_nome);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        email = currentUser.getEmail();
        System.out.println(email);
        databaseReference = firebaseDatabase.getReference("usuarios").child(mAuth.getCurrentUser().getUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("entrou");
                System.out.println(dataSnapshot.getKey());
                System.out.println(dataSnapshot.getValue());
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                System.out.println(usuario.getNome());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                System.out.println("o erro é " + databaseError.getMessage());
            }
        });
        carregarCampos();
    }


    public void carregarCampos(){
        //mNome.setText(usuario.getNome());
    }
}
