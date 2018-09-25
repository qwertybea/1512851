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
    public MParametresPartie parametresPartie;
    private String __parametresPartie = "parametresPartie";

    @AttributSerialisable
    private Integer hauteur = GConstantes.HAUTEUR_DEFAUT;
    private final String __hauteur = "hauteur";

    @AttributSerialisable
    private Integer largeur = GConstantes.LARGEUR_DEFAUT;
    private final String __largeur = "largeur";

    @AttributSerialisable
    private Integer pourGagner = GConstantes.SCORE_GAGNANT_DEFAUT;
    private final String __pourGagner = "pourGagner";

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres() {
        this.genererListeChoixHauteur();
        this.genererListeChoixLargeur();
        this.genererListeChoixPourGagner();
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
        boolean h = false, l = false, g = false;
        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {
            if (entry.getKey().equals(__hauteur)) {
                hauteur = Integer.valueOf((entry.getValue().toString()));
                h = true;
            } else if (entry.getKey().equals(__largeur)) {
                largeur = Integer.valueOf((entry.getValue().toString()));
                l = true;
            } else if (entry.getKey().equals(__pourGagner)) {
                pourGagner = Integer.valueOf((entry.getValue().toString()));
                g = true;
            }
        }
        if (!(h && l && g)) {
            throw new ErreurSerialisation("");
        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        Map<String, Object> map = new HashMap<>();

        //FIXME: if these are not strings bad things happen
        map.put(__hauteur, hauteur.toString());
        map.put(__largeur, largeur.toString());
        map.put(__pourGagner, pourGagner.toString());

        return map;
    }

    // GETTER - SETTER

    public static MParametres getInstance() {
        return instance;
    }

    public Integer getHauteur() {
        return hauteur;
    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
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
