package ca.cours5b5.justinfofana.modeles;

import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;

public class MParametresPartie extends Modele{
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
        return new MParametresPartie(mParametres.getHauteur(), mParametres.getLargeur(),mParametres.getPourGagner());
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

    // TODO: implémenter ces méthodes
    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {}

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {return null;}
}
