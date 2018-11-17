package ca.cours5b5.justinfofana.controleurs;

import java.io.File;

import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.justinfofana.donnees.Serveur;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.modeles.MPartieReseau;
import ca.cours5b5.justinfofana.modeles.Modele;
import ca.cours5b5.justinfofana.proxy.ProxyListe;
import ca.cours5b5.justinfofana.usagers.UsagerCourant;

public final class ControleurPartieReseau {

    private static final ControleurPartieReseau instance = new ControleurPartieReseau();

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    private ControleurPartieReseau() {}

    public static ControleurPartieReseau getInstance() {
        return instance;
    }

    public void connecterAuServeur(){

        ControleurModeles.getModele(MPartieReseau.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {

                MPartieReseau mPartieReseau = (MPartieReseau) modele;

                connecterAuServeur(mPartieReseau.getId());

            }
        });

    }
    /*
     * Obtenir le modèle MPartieReseau
     * Obtenir le id du modèle (qui est l'id du joueur hôte)
     * Appeler connecterAuServeur(String idJouerHote)
     *
     */

    private void connecterAuServeur(String idJoueurHote) {

        String monId = UsagerCourant.getId();

        if (monId.equals(idJoueurHote)) {

            connecterEnTantQueJoueurHote(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));

        } else {

            connecterEnTantQueJoueurInvite(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));

        }

        proxyRecevoirCoups.setActionNouvelItem(GCommande.RECEVOIR_COUP_RESEAU);

        proxyEmettreCoups.connecterAuServeur();
        proxyRecevoirCoups.connecterAuServeur();


    }
    /*
     * Connecter en tant que joueur hôte OU en tant qu'invité, selon le cas
     *
     * Connecter les deux proxy au serveur
     *
     * Ajouter l'action RECEVOIR_COUP_RESEAU au proxyRecevoirCoups
     *
     */

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurHote);

        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurInvite);

    }
    /*
     * Créer les proxy... avec les bons chemins
     *
     */

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurInvite);

        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurHote);

    }
    /*
     * Créer les proxy... avec les bons chemins
     *
     */

    public void deconnecterDuServeur(){

        proxyEmettreCoups.detruireValeurs();

        proxyEmettreCoups.deconnecterDuServeur();
        proxyRecevoirCoups.deconnecterDuServeur();

    }
    /*
     * Détruire les valeurs du proxyEmettreCoups
     *
     * Déconnecter les deux proxy
     *
     */

    public void transmettreCoup(Integer idColonne){

        proxyEmettreCoups.ajouterValeur(idColonne);

    }
    /*
     * Transmettre avec proxyEmettreCoups
     *
     */

    private String getCheminCoupsJoueurInvite(String idJoueurHote){

        return getCheminPartie(idJoueurHote) + File.separator + GConstantes.CLE_COUPS_JOUEUR_INVITE;

    }
    /*
     * Utiliser p.ex. la constante CLE_COUPS_JOUR_INVITE
     */

    private String getCheminCoupsJoueurHote(String idJoueurHote){

        return getCheminPartie(idJoueurHote) + File.separator + GConstantes.CLE_COUPS_JOUEUR_HOTE;

    }
    /*
     * Utiliser p.ex. la constante CLE_COUPS_JOUR_HOTE
     */

    private String getCheminPartie(String idJoueurHote) {

        return MPartieReseau.class.getSimpleName() + File.separator + idJoueurHote;

    }
    /*
     * Le chemin contient l'id de la partie (id du joueur hote)
     */

    public void detruireSauvegardeServeur() {

        Serveur.getInstance().effacerModele(getCheminPartie(UsagerCourant.getId()));

    }
    /*
     * Appeler p.ex. le detruireSauvegarde de Serveur (avec le bon chemin)
     */

}
