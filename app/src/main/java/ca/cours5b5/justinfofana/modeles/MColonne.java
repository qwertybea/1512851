package ca.cours5b5.justinfofana.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.global.GCouleur;

public class MColonne extends Modele {

    private List<GCouleur> jetons;

    public MColonne() {
        jetons = new ArrayList<>();
    }

    public List<GCouleur> getJetons() {
        return jetons;
    }

    public void placerJeton(GCouleur couleur) {
        jetons.add(couleur);
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {}

    @Override
    public Map<String, Object> enObjetJson() {return null;}

}
