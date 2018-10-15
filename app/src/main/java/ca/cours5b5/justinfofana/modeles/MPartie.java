package ca.cours5b5.justinfofana.modeles;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.ControleurAction;
import ca.cours5b5.justinfofana.controleurs.ControleurObservation;
import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.global.DebugTools;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GCouleur;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;

public class MPartie extends Modele implements Fournisseur {

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";
    @AttributSerialisable
    public List<Integer> coups;
    private final String __coups = "coups";
    private MGrille grille;
    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres) {
        this.parametres = parametres;

        this.grille = new MGrille(parametres.getLargeur());

        fournirActionPlacerJeton();
        initialiserCouleurCourante();
    }

    public MParametresPartie getParametres() {
        return this.parametres;
    }

    public MGrille getGrille() { return this.grille; }

    private void initialiserCouleurCourante() {
        couleurCourante = GCouleur.JAUNE;
    }

    private void prochaineCouleurCourante() {
        if (couleurCourante == GCouleur.ROUGE) {
            couleurCourante = GCouleur.JAUNE;
        } else if (couleurCourante == GCouleur.JAUNE) {
            couleurCourante = GCouleur.ROUGE;
        }
    }

    private void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                jouerCoup((int) args[0]);
            }
        });
    }

    protected void jouerCoup(int colonne) {
        this.grille.placerJeton(colonne, couleurCourante);
        prochaineCouleurCourante();
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        List<String> coupsJson = new ArrayList<>();

        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {

            if (entry.getKey().equals(__parametres)) {

                this.parametres.aPartirObjetJson((Map<String, Object>) entry.getValue());

            } else if(entry.getKey().equals(__coups)) {

                coupsJson = (List<String>) entry.getValue();

            }

        }

        this.grille = new MGrille(parametres.getLargeur());

        this.initialiserCouleurCourante();

        rejouerLesCoups(listeCoupsAPartirJson(coupsJson));

    }


    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        

        return null;
    }

    private void rejouerLesCoups(List<Integer> coupsARejouer) {
        for (int coup : coupsARejouer) {
            jouerCoup(coup);
        }
    }

    private List<Integer> listeCoupsAPartirJson(List<String> listeCoupsObjetJson) {
        final List<Integer> listInt = new ArrayList<>();
//        listeCoupsObjetJson.forEach((coup) -> listInt.add(Integer.parseInt(coup)));

        for (String coup : listeCoupsObjetJson) {
            listInt.add(Integer.parseInt(coup));
        }

        return listInt;
    }

    private List<String> listeCoupsEnObjetJson(List<Integer> listeCoups) {
        final List<String> listJson = new ArrayList<>();

        for (int coup : listeCoups) {
            listJson.add(Integer.toString(coup));
        }

        return listJson;
    }

}
