package ca.cours5b5.justinfofana.controleurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.donnees.Disque;
import ca.cours5b5.justinfofana.donnees.SourceDeDonnees;
import ca.cours5b5.justinfofana.exceptions.ErreurModele;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;

public class ControleurModeles {

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();

        listeDeSauvegardes.add(Disque.getInstance());

    }

    private ControleurModeles(){}

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement) {
        ControleurModeles.sequenceDeChargement = sequenceDeChargement;
    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(nomModele, objetJson);

        }

    }

    static Modele getModele(final String nomModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            modele = chargerViaSequenceDeChargement(nomModele);

        }

        return modele;
    }

    private static Modele chargerViaSequenceDeChargement(final String nomModele){

        Modele modele = creerModeleSelonNom(nomModele);

        modelesEnMemoire.put(nomModele, modele);

        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){

            Map<String, Object> objetJson = sourceDeDonnees.chargerModele(nomModele);

            if(objetJson != null){

                modele.aPartirObjetJson(objetJson);
                break;

            }

        }

        return modele;
    }

    private static Modele creerModeleSelonNom(String nomModele) {
        if(nomModele.equals(MParametres.class.getSimpleName())){

            return new MParametres();

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            MParametres mParametres = (MParametres) getModele(MParametres.class.getSimpleName());
            
            return new MPartie(mParametres.getParametresPartie().cloner());

        }else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    public static void sauvegarderModele(String nomModele){

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }
    }

}
