package ca.cours5b5.justinfofana.modeles;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;

public class MParametresPartie extends Modele {
    @AttributSerialisable
    private Integer hauteur;
    protected final String __hauteur = "hauteur";

    @AttributSerialisable
    private Integer largeur;
    protected final String __largeur = "largeur";

    @AttributSerialisable
    private Integer pourGagner;
    protected final String __pourGagner = "pourGagner";


    /*
     * Retourne une instance de MParametresPartie avec
     *   exactement les mêmes hauteur/largeur/pourGagner
     *   que mParametres reçu en argument
     *
     */
    public static MParametresPartie aPartirMParametres(MParametres mParametres) {
        return new MParametresPartie(mParametres.getParametrePartie().getHauteur(),
                mParametres.getParametrePartie().getLargeur(),
                mParametres.getParametrePartie().getPourGagner());
    }

    public MParametresPartie cloner() {
        return new MParametresPartie(this.hauteur, this.largeur, this.pourGagner);
    }

    public MParametresPartie(int hauteur, int largeur, int pourGagner) {
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
        this.setPourGagner(pourGagner);
    }

    public Integer getHauteur(){
        return this.hauteur;
    }

    public Integer getLargeur() {
        return this.largeur;
    }

    public Integer getPourGagner() {
        return this.pourGagner;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(int pourGagner) {
        this.pourGagner = pourGagner;
    }

    // FIXME: these are the same exact methods from MParametre. seems bad
    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation
    {
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
            throw new ErreurSerialisation("dun goofed");
        }

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation
    {
        Map<String, Object> map = new HashMap<>();

        //FIXME: if these are not strings bad things happen
        map.put(__hauteur, hauteur.toString());
        map.put(__largeur, largeur.toString());
        map.put(__pourGagner, pourGagner.toString());

        return map;
    }
}
