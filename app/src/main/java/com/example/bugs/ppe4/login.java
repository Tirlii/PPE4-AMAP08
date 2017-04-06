package com.example.bugs.ppe4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class login extends Activity {

    public final static String nom = "";
    public EditText text;
    public EditText pass;
    public String motDePasse;
    public String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        text = (EditText)findViewById(R.id.inputName);
        pass = (EditText)findViewById(R.id.inputPassword);
    }

    public void valider(View view) {
        login = text.getText().toString();
        motDePasse = pass.getText().toString();
        ConnexionHTTP connexion = new ConnexionHTTP();
        connexion.execute("http://10.10.9.135/android/index.php", login, motDePasse);
        try
        {
            if (connexion.get())
            {
                XMLParseur parseurXML = new XMLParseur(connexion.getXML());                         //connexion au XMLParseur
                Toast.makeText(this, parseurXML.getValue("status"), Toast.LENGTH_SHORT).show();  //on affiche la valeur des balises XML status dans un toast
                if(parseurXML.getValue("status").compareTo("-2") == 0)                         //si -2 (get)
                {
                    /* Erreur */
                    Toast.makeText(this, "Erreur de login et/ou mot de passe", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, parseurXML.getValue("etat"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, loginValid.class);
                    EditText inputName = (EditText) findViewById(R.id.inputName);
                    intent.putExtra(nom, inputName.getText().toString());
                    startActivity(intent);
                    this.finish();
                     }
            }

    else Toast.makeText(this, "Echec lors de la connexion", Toast.LENGTH_LONG).show();
    }
    catch (InterruptedException e)
        {
            Log.w("connexion", "Interruption lecture fichier");
        }
    catch (ExecutionException e)
    {
        Log.w("connexion", "Problème exécution");
    }

        /*if(text.getText().toString().equals("bugs")) {
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, loginValid.class);
            EditText inputName = (EditText) findViewById(R.id.inputName);
            intent.putExtra(nom, inputName.getText().toString());
            startActivity(intent);
            this.finish();
        }
        else {
            Toast.makeText(this, "Mauvais login m8", Toast.LENGTH_SHORT).show();
        }*/
    }
}



