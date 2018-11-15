package ca.cours5b5.justinfofana.proxy;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public abstract class Proxy {

    private String cheminServeur;

    protected DatabaseReference noeudServeur;

    public Proxy(String cheminServeur){
        this.cheminServeur = cheminServeur;
    }

    public void connecterAuServeur(){

        noeudServeur = FirebaseDatabase.getInstance().getReference(cheminServeur);

    }
    /*
     * Obtenir le noeud
     *
     */

    public void deconnecterDuServeur(){

        noeudServeur = null;

    }
    /*
     * Oublier le noeud
     *
     */

    public abstract void detruireValeurs();

}
