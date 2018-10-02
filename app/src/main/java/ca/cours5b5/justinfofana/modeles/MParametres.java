package ca.cours5b5.justinfofana.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;

public class MParametres extends Modele {
    // FIXME: c'est temporaire ; on va écrire un gestionnaire de modèles à l'Atelier07
    private static MParametres instance = new MParametres();

    @AttributSerialisable
    private MParametresPartie parametresPartie;
    private String __parametresPartie = "parametresPartie";

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres() {
        this.genererListeChoixHauteur();
        this.genererListeChoixLargeur();
        this.genererListeChoixPourGagner();
        this.parametresPartie = new MParametresPartie(GConstantes.HAUTEUR_DEFAUT, GConstantes.LARGEUR_DEFAUT, GConstantes.SCORE_GAGNANT_DEFAUT);
    }

    private List<Integer> genereList (int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    private void genererListeChoixHauteur() {
        this.choixHauteur = this.genereList(GConstantes.HAUTEUR_MIN, GConstantes.HAUTEUR_MAX);
    }

    private void genererListeChoixLargeur() {
        this.choixLargeur = this.genereList(GConstantes.LARGEUR_MIN, GConstantes.LARGEUR_MAX);
    }

    private void genererListeChoixPourGagner() {
        this.choixPourGagner = this.genereList(GConstantes.SCORE_GAGNANT_MIN, GConstantes.SCORE_GAGNANT_MAX);
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        this.parametresPartie.aPartirObjetJson(objetJson);
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        return this.parametresPartie.enObjetJson();
    }

    // GETTER - SETTER

    public static MParametres getInstance() {
        return instance;
    }

    public MParametresPartie getParametrePartie() {
        return this.parametresPartie;
    }

    public List<Integer> getChoixHauteur() {
        return this.choixHauteur;
    }

    public List<Integer> getChoixLargeur() {
        return this.choixLargeur;
    }

    public List<Integer> getChoixPourGagner() {
        return this.choixPourGagner;
    }
}
