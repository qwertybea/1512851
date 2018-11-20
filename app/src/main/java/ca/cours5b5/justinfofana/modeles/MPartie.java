package ca.cours5b5.justinfofana.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.Action;
import ca.cours5b5.justinfofana.controleurs.ControleurAction;
import ca.cours5b5.justinfofana.controleurs.ControleurPartie;
import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.justinfofana.exceptions.ErreurAction;
import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GCouleur;
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;

public class MPartie extends Modele implements Fournisseur {

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    @AttributSerialisable
    public List<Integer> listeCoups;
    private final String __listeCoups = "listeCoups";

    private MGrille grille;
    private GCouleur couleurCourante;
    private int joueurCourant;
    private int nombreJoueur;

    public boolean matchFini;

//    private Action actionCouleurJoueur;

    public MPartie(MParametresPartie parametres){

        this.parametres = parametres;

        initialiser();

        initialiserJoueurs();

        initialiserCouleurCourante();

        initialiserGrille();

        fournirActionPlacerJeton();

    }

    public void recommencerPartie() {

        initialiser();

        initialiserJoueurs();

        initialiserCouleurCourante();

        initialiserGrille();

    }

    private void initialiser() {

        listeCoups = new ArrayList<>();

        matchFini = false;

    }

    private void initialiserJoueurs() {

//        demanderActionCouleurJoueur();

        nombreJoueur = 2;

        joueurCourant = 0;

    }

//    private void demanderActionCouleurJoueur() {
//
//        actionCouleurJoueur = ControleurAction.demanderAction(GCommande.COULEUR_JOUEUR);
//
//    }

//    private void appelerActionCouleurJoueur() {
//
//        actionCouleurJoueur.setArguments(joueurCourant, couleurCourante);
//
//        actionCouleurJoueur.executerDesQuePossible();
//
//    }

    private void initialiserCouleurCourante() {
        prochaineCouleurCourante();
    }


    private void initialiserGrille() {
        grille = new MGrille(parametres.getHauteur(), parametres.getLargeur());
    }


    protected void fournirActionPlacerJeton() {

        ControleurAction.fournirAction(this,
                GCommande.JOUER_COUP_ICI,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{

                            int colonne = (Integer) args[0];

                            jouerCoup(colonne);

                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }

                    @Override
                    public boolean peutExecuter(Object... args) {

                        return siCoupLegal((int) args[0]);

                    }

                });
    }

    protected void jouerCoup(int colonne) {

        if(siCoupLegal(colonne)){

            listeCoups.add(colonne);

            grille.placerJeton(colonne, couleurCourante);

            if (grille.siCouleurGagne(getCouleurCourante(), parametres.getPourGagner())) {

                matchFini = true;
                ControleurPartie.getInstance().gagnerPartie(couleurCourante);

            } else {

                prochainJoueurCourant();
                prochaineCouleurCourante();

            }

        }
    }

    private boolean siCoupLegal(int colonne){

        MColonne mColonne = grille.getColonnes().get(colonne);

        return mColonne.nombreDeJetons() < parametres.getHauteur();

    }

    private void prochainJoueurCourant(){
        joueurCourant++;
        if (joueurCourant == nombreJoueur) {
            joueurCourant = 0;
        }
    }

    private void prochaineCouleurCourante(){

        couleurCourante = GCouleur.values()[joueurCourant];

//        appelerActionCouleurJoueur();

    }


    public MGrille getGrille() {
        return grille;
    }

    public MParametresPartie getParametres() {
        return parametres;
    }

    public GCouleur getCouleurCourante() {
        return couleurCourante;
    }

    public int getJoueurCourant() {
        return joueurCourant;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation  {

        parametres.aPartirObjetJson((Map<String, Object>)objetJson.get(__parametres));

        initialiserCouleurCourante();

        initialiserGrille();

        List<String> listeCoupsObjetJson = (List<String>) objetJson.get(__listeCoups);

        if(listeCoupsObjetJson != null){

            List<Integer> coupsARejouer = listeCoupsAPartirJson(listeCoupsObjetJson);
            rejouerLesCoups(coupsARejouer);

        }
    }


    private List<Integer> listeCoupsAPartirJson(List<String> listeCoupsObjetJson) {

        List<Integer> listeCoups = new ArrayList<>();

        Iterator iter = listeCoupsObjetJson.iterator();

        while(iter.hasNext()) {
            GLog.donnees(iter.next());
        }

        for(String coupChaine : listeCoupsObjetJson){

            GLog.donnees(coupChaine);

            // le coup peut etre null si la liste stocker n'est pas conforme (eg. [0: 1, 2: 1, 3: 1])
            // lorsque caster en liste ceci retournera une liste de longueur 4
            // et il remplira les element manquant avec une valeurs null (eg. [0: 1, 1: null, 2: 1, 3: 1])
            if (coupChaine != null) {
                listeCoups.add(Integer.valueOf(coupChaine));
            }

        }

        return listeCoups;
    }


    private void rejouerLesCoups(List<Integer> coupsARejouer) {

        listeCoups.clear();

        for(Integer coup : coupsARejouer){

            jouerCoup(coup);

        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__parametres, parametres.enObjetJson());
        objetJson.put(__listeCoups, listeCoupsEnObjetJson(listeCoups));

        return objetJson;

    }

    private  List<String> listeCoupsEnObjetJson(List<Integer> listeCoups) {

        List<String> listeCoupsObjetJson = new ArrayList<>();

        for(Integer coup : listeCoups){

            listeCoupsObjetJson.add(coup.toString());

        }

        return listeCoupsObjetJson;

    }


}
