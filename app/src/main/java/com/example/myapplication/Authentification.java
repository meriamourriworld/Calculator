package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Authentification extends AppCompatActivity {

    Button btnAuthValider, btnNouveauCompte;
    EditText edAuthMail, edAuthMp;
    TextView txtAuthErr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        //Récupérer les composants
        edAuthMail = findViewById(R.id.edAuthMail);
        edAuthMp = findViewById(R.id.edAuthmp);
        btnAuthValider = findViewById(R.id.btnAuthValider);
        btnNouveauCompte = findViewById(R.id.btnNouveauCompte);
        txtAuthErr = findViewById(R.id.txtAuthError);

        DataBaseHandler db = new DataBaseHandler(this);

        //ÉVÉNEMENTS
        btnNouveauCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Authentification.this, nouveauCompte.class);
            }
        });
        btnAuthValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = verifierChamps();
                txtAuthErr.setText("");

                if(msg.equals(""))
                {
                    Client client = db.getClient(edAuthMail.getText().toString(), edAuthMp.getText().toString());
                    if(client == null)
                    {
                        Toast.makeText(Authentification.this, "Ce client est inexistant !", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Log.d("CONNECTED", client.toString());
                        //redirectActivity(Authentification.this, afficherClients.class);
                    }
                }else{
                    txtAuthErr.setText(msg);
                }
            }
        });
    }
    public static void redirectActivity(Activity activite, Class activite1)
    {
        Intent intent = new Intent(activite, activite1);
        activite.startActivity(intent);
    }
    public String verifierChamps()
    {
        String mail = edAuthMail.getText().toString();
        String mp = edAuthMp.getText().toString();
        if(mail.equals("")) return "L'e-mail est obligatoire !";
        if(mp.equals("")) return "Le mot de passe est obligatoire !";
        return "";
    }
}