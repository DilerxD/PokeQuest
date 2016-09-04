package ceids.ulima.edu.pe.pokequest.Codigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ceids.ulima.edu.pe.pokequest.FirebaseHelper.FirebaseHelper;
import ceids.ulima.edu.pe.pokequest.R;
import ceids.ulima.edu.pe.pokequest.beans.Codigo;
import ceids.ulima.edu.pe.pokequest.beans.Correo;

public class CodigoActiviry extends AppCompatActivity {
    TextView codigito;
    Button boton;
    DatabaseReference db;
    FirebaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        codigito=(TextView) findViewById(R.id.eteSugerencias);
        boton=(Button) findViewById(R.id.btnSugerencia);

        db= FirebaseDatabase.getInstance().getReference();

        helper=new FirebaseHelper(db);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigoinput=codigito.getText().toString();
                Codigo codigo=new Codigo();
                codigo.setCodigo(codigoinput);

                if(codigoinput.length()==8 && codigoinput!=null){
                    if(helper.saveCodigo(codigo)){
                        codigito.setText("");
                        Toast.makeText(CodigoActiviry.this,"Gracias",Toast.LENGTH_SHORT).show();
                        Correo correo= new Correo();
                        correo.setCorreo(getIntent().getStringExtra("correo").toString());
                        helper.saveCorreo(correo);
                    }else{
                        Toast.makeText(CodigoActiviry.this,"El codigo debe ser de 8 digitos",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }


}
