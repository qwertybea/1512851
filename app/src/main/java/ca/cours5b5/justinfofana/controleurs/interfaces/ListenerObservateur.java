package ca.cours5b5.justinfofana.controleurs.interfaces;

import ca.cours5b5.justinfofana.modeles.Modele;

public abstract class ListenerObservateur {
    public void reagirNouveauModele(Modele modele) {}
    /*
     * L'implantation par défaut est d'appeler reagirChangementAuModele
     *
     */
    public abstract void reagirChangementAuModele(Modele modele);
}
