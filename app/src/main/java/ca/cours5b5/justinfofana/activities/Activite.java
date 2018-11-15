package ca.cours5b5.justinfofana.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.ControleurModeles;
import ca.cours5b5.justinfofana.donnees.Disque;
import ca.cours5b5.justinfofana.donnees.SauvegardeTemporaire;
import ca.cours5b5.justinfofana.donnees.Serveur;
import ca.cours5b5.justinfofana.donnees.Transition;
import ca.cours5b5.justinfofana.global.DebugTools;
import ca.cours5b5.justinfofana.global.GLog;

public abstract class Activite extends AppCompatActivity {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    protected void initialiserControleurModeles(Bundle savedInstanceState) {

        GLog.activite(savedInstanceState);

        ControleurModeles.setSequenceDeChargement(
                new SauvegardeTemporaire(savedInstanceState),
                new Transition(getIntent().getExtras()),
                Serveur.getInstance(),
                Disque.getInstance());

    }

    protected void initialiserApplication(){

        Disque.getInstance().setRepertoireRacine(getFilesDir());

    }

    protected void lanceActivite(Class destination) {

        Intent intentionParametres = new Intent(this, destination);

        startActivity(intentionParametres);

    }

    protected void lanceActivite(Class destination, Map<String, String> extras) {

        Intent intentionParametres = new Intent(this, destination);

        for (Map.Entry<String, String> extra : extras.entrySet()) {

            intentionParametres.putExtra(extra.getKey(), extra.getValue());

        }

        startActivity(intentionParametres);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        DebugTools.messageLog(this,"onCreate");

        initialiserControleurModeles(savedInstanceState);
        initialiserApplication();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        DebugTools.messageLog(this,"onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        DebugTools.messageLog(this,"onPause");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        DebugTools.messageLog(this, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        DebugTools.messageLog(this, "onSaveInstanceState");


        // FIXME: should this be here?
        //ControleurModeles.sauvegarderModeleDansCetteSource(MParametres.class.getSimpleName(),
        //        new SauvegardeTemporaire(outState));
    }

}
