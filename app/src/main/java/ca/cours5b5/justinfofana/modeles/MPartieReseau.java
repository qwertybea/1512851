package ca.cours5b5.justinfofana.modeles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.ControleurAction;
import ca.cours5b5.justinfofana.controleurs.ControleurPartieReseau;
import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.justinfofana.exceptions.ErreurAction;
import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;
import ca.cours5b5.justinfofana.serialisation.Jsonification;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite = GConstantes.CLE_ID_JOUEUR_INVITE;

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote = GConstantes.CLE_ID_JOUEUR_HOTE;

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);

        fournirActionRecevoirCoup();
    }

    public String getId() {
        return idJoueurHote;
    }
    /*
     * utiliser l'id du joueur hôte
     */

    private void fournirActionRecevoirCoup() {
        ControleurAction.fournirAction(this,
                GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        int colonne = ((Long) args[0]).intValue();

                        recevoirCoupReseau(colonne);

                    }
                });
    }

    @Override
    protected void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this,
                GCommande.JOUER_COUP_ICI,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{

                            int colonne = (Integer) args[0];

                            jouerCoup(colonne);

                            ControleurPartieReseau.getInstance().transmettreCoup(colonne);

                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }
                });
    }
    /*
     * En plus de jouer le coup, le transmettre via
     *  le ControleurPartieReseau
     *
     *  ATTENTION au @Override. Le code qui fournit l'action
     *  PLACER_JETON dans la classe parent MPartie
     *  ne doit **pas** s'exécuter
     */


    private void recevoirCoupReseau(int colonne) {

        jouerCoup(colonne);

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        idJoueurHote = (String) objetJson.get(__idJoueurHote);
        idJoueurInvite = (String) objetJson.get(__idJoueurInvite);

        // FIXME: mauvais chargement si la cle nest pas "parametres"
        getParametres().aPartirObjetJson((Map<String, Object>) objetJson.get("parametres"));

        super.aPartirObjetJson(enObjetJson());

    }
    /*
     * charger les champs
     * appeler aussi super
     */

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        Map<String, Object> objetJson = super.enObjetJson();

        objetJson.put(__idJoueurHote, idJoueurHote);
        objetJson.put(__idJoueurInvite, idJoueurInvite);

        return objetJson;

    }
    /*
     * sauvegarder les champs
     * appeler aussi super
     */
}
