package ca.cours5b5.justinfofana.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.justinfofana.controleurs.Action;
import ca.cours5b5.justinfofana.controleurs.ControleurAction;
import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.serialisation.Jsonification;

public class ProxyListe extends Proxy implements Fournisseur {

    private ChildEventListener childEventListener;

    private Query requete;

    private Action actionNouvelItem;

    private List<DatabaseReference> noeudsAjoutes;

    public ProxyListe(String cheminServeur) {
        super(cheminServeur);

        noeudsAjoutes = new ArrayList<>();

    }

    public void setActionNouvelItem(GCommande commande){

        actionNouvelItem = ControleurAction.demanderAction(commande);

    }

    public void ajouterValeur(Object valeur) {

        DatabaseReference sousNoeud = noeudServeur.push();

        sousNoeud.setValue(valeur);

        noeudsAjoutes.add(sousNoeud);

    }
    /*
     * Créer un sous-noeud avec push()
     * Mémoriser le noeud ajouté
     * Ajouter la valeur avec setValue()
     */

    @Override
    public void connecterAuServeur(){
        super.connecterAuServeur();

        creerListener();

        requete = getRequete();

        requete.addChildEventListener(childEventListener);

    }
    /*
     * Céer le listener
     * sauvegarder la requête
     * ajouter le listener
     */

    private void creerListener(){

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Object valeurAjoutee = dataSnapshot.getValue();

                if (actionNouvelItem != null) {

                    actionNouvelItem.setArguments(valeurAjoutee);

                    actionNouvelItem.executerDesQuePossible();

                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }

    protected Query getRequete(){
        return noeudServeur.orderByValue().limitToLast(GConstantes.NOMBRE_DE_VALEURS_A_CHARGER_DU_SERVEUR_PAR_DEFAUT);
    }
    /*
     * On veut trier par clé et limiter à un nombre max (utiliser une constante)
     */

    @Override
    public void deconnecterDuServeur() {

        requete.removeEventListener(childEventListener);

        noeudsAjoutes = null;

        super.deconnecterDuServeur();

    }
    /*
     * retirer le listener
     * oublier les noeuds ajoutés
     * déconnecter via super
     */

    @Override
    public void detruireValeurs() {

        noeudsAjoutes.clear();

    }
}
