package ca.cours5b5.justinfofana.controleurs.interfaces;

import ca.cours5b5.justinfofana.modeles.Modele;

public abstract class ListenerObservateur {
    /*
     * L'implantation par défaut est d'appeler reagirChangementAuModele
     *
     */
    public void reagirNouveauModele(Modele modele) {
        this.reagirChangementAuModele(modele);
    }

    public abstract void reagirChangementAuModele(Modele modele);
}
