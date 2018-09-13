package ca.cours5b5.justinfofana.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;

public class MParametres extends Modele {
    // FIXME: c'est temporaire ; on va écrire un gestionnaire de modèles à l'Atelier07
    public static MParametres instance = new MParametres();

    @AttributSerialisable
    public Integer hauteur = GConstantes.HAUTEUR_DEFAUT;
    private final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur = GConstantes.LARGEUR_DEFAUT;
    private final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner = GConstantes.SCORE_GAGNANT_DEFAUT;
    private final String __pourGagner = "pourGagner";

    public List<Integer> getChoixHauteur() {
        return this.genereList(GConstantes.HAUTEUR_MIN, GConstantes.HAUTEUR_MAX);
    }

    public List<Integer> getChoixLargeur() {
        return this.genereList(GConstantes.LARGEUR_MIN, GConstantes.LARGEUR_MAX);
    }

    public List<Integer> getChoixPourGagner() {
        return this.genereList(GConstantes.SCORE_GAGNANT_MIN, GConstantes.SCORE_GAGNANT_MAX);
    }

    private List<Integer> genereList (int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {
            if (entry.getKey().equals(__hauteur)) {
                hauteur = Integer.valueOf((entry.getValue().toString()));
            } else if (entry.getKey().equals(__largeur)) {
                largeur = Integer.valueOf((entry.getValue().toString()));
            } else if (entry.getKey().equals(__pourGagner)) {
                pourGagner = Integer.valueOf((entry.getValue().toString()));
            }
        }
    }

    @Override
    public Map<String, Object> enObjetJson() {

        Map<String, Object> map = new HashMap<>();

        //FIXME: if these are not strings bad things happen
        map.put(__hauteur, hauteur.toString());
        map.put(__largeur, largeur.toString());
        map.put(__pourGagner, pourGagner.toString());

        return map;
    }
}
