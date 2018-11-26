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
import com.br.fatec.sos_professores.R;


public class menuActivity extends AppCompatActivity {


    public void btn_perfilOnClick(View v){
        Intent it = new Intent(menuActivity.this, perfilActivity.class);
        startActivity(it);

    }
    public void btn_aulaOnClick(View v){
        Intent it = new Intent(menuActivity.this, menuActivity.class);
        startActivity(it);

    }
    public void btn_professorOnClick(View v){
        Intent it = new Intent(menuActivity.this, menuActivity.class);
        startActivity(it);

    }

}
