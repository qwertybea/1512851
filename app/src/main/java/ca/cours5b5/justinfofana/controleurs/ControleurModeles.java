package ca.cours5b5.justinfofana.controleurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.donnees.Disque;
import ca.cours5b5.justinfofana.donnees.SourceDeDonnees;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;

public class ControleurModeles {

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        /*
         * Initialisation
         *
         * Ajout du Disque dans la liste de sauvegarde
         *
         */

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();

        listeDeSauvegardes.add(Disque.getInstance());

    }

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement) {

    }

    /*
     * Doit être appelée à la création de l'activité
     *
     */



    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {



    }


    static Modele getModele(final String nomModele){

        if (modelesEnMemoire.get(nomModele) == null) {
            chargerViaSequenceDeChargement(nomModele);
        }

        return modelesEnMemoire.get(nomModele);
    }
        /*
         * Doit retourner un modèle (jamais null)
         *
         * Au besoin, utiliser la sequence de chargement pour charger le modèle
         *
         */

    private static Modele chargerViaSequenceDeChargement(final String nomModele){

        Modele modele = creerModeleSelonNom(nomModele);

        sauvegarderModele(nomModele);

        return modele;

    }
        /*
         * Commencer par créer un modèle vide
         *
         * (le sauvegarder en mémoire!)
         *
         * Ensuite, charger les données à partir de la première source
         * de la séquence qui retourne non-null
         *
         */

    private static Modele creerModeleSelonNom(String nomModele) {
        Modele modele = null;

        if (MParametres.class.getSimpleName().equals(nomModele)) {
            modele = new MParametres();
        } else if (MPartie.class.getSimpleName().equals(nomModele)) {
//            modele = new MPartie(new MParametresPartie());
        }

        return modele;
    }
    /*
     * À partir du nom, créer le bon modèle
     *
     */

    public static void sauvegarderModele(String nomModele){

    }
        /*
         * Sauvegarder dans toutes les sources
         *
         */

}
