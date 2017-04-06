package com.example.bugs.ppe4;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by bugs on 16/03/17.
 */

 /*
========================================================================================
BTS SIO - SLAM 2 - 11/2014 - PF
----------------------------------------------------------------------------------------
Étude Android : Connexion à un service Web et échange de données au format XML
=> Programme principal Android ...
========================================================================================
*/

public class ConnexionHTTP extends AsyncTask<String, Void, Boolean>
{
    private String xml = null;
    @Override
    protected Boolean doInBackground(String... arg0)
    {
        URL url = null;
        String urlComplete = arg0[0];
/* ... */
        try
        {
            urlComplete += "?" + URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(arg0[1], "UTF-8");
            urlComplete += "&" + URLEncoder.encode("motDePasse", "UTF-8") + "=" + URLEncoder.encode(arg0[2], "UTF-8");
        }
        catch(UnsupportedEncodingException e)
        {
            Log.e("ConnexionHTTP", "Erreur lors de l'encodage : " + e);
            return false;
        }
/* ... */
        try
        {
            url = new URL(urlComplete);
        }
        catch(MalformedURLException e)
        {
            Log.e("ConnexionHTTP", "URL incorrect : " + e);
            return false;
        }
        URLConnection connexion = null;
        try
        {
            connexion = url.openConnection();
        }
        catch(IOException e)
        {
            Log.e("ConnexionHTTP", "Connexion impossible : " + e);
            return false;
        }
/* Analyse de la réponse du serveur au format XML ... */
        xml = "";
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String tmp;
            while((tmp = reader.readLine()) != null)
            {
                xml += tmp;
            }
            reader.close();
        }
        catch(Exception e)
        {
            Log.e("ConnexionHTTP", "Erreur lors de la lecture de la réponse : " + e);
            return false;
        }
        return true;
    }
    public String getXML()
    {
        return xml;
    }
}