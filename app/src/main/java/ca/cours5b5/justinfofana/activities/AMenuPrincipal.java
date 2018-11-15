package ca.cours5b5.justinfofana.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurAction;
import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.modeles.MPartieReseau;
import ca.cours5b5.justinfofana.vues.VMenuPrincipal;

public class AMenuPrincipal extends Activite implements Fournisseur {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    // FIXME: this is aweful
    VMenuPrincipal vMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        vMenuPrincipal = findViewById(R.id.activity_menuPrincipal);

        fournirActions();

    }

    private void fournirActions() {

        fournirActionOuvrirMenuParametres();

        fournirActionDemarrerPartie();

        fournirActionDemarrerPartieEnLigne();

        fournirActionConnexion();

        fournirActionDeconnexion();
    }

    private void fournirActionOuvrirMenuParametres() {

        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionParametres();

                    }
                });
    }

    private void fournirActionDemarrerPartie() {

        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartie();

                    }
                });
    }

    private void fournirActionDemarrerPartieEnLigne() {

        ControleurAction.fournirAction(this,
                GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartieReseau();

                    }
                });
    }

    private void fournirActionConnexion() {

        ControleurAction.fournirAction(this,
                GCommande.CONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        connexion();

                    }
                });
    }

    private void fournirActionDeconnexion() {

        ControleurAction.fournirAction(this,
                GCommande.DECONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        deconnexion();

                    }
                });
    }

    private void transitionParametres(){

        lanceActivite(AParametres.class);

    }

    private void transitionPartie(){

        lanceActivite(APartie.class);

    }

    private void transitionPartieReseau(){

        HashMap<String, String> extras = new HashMap<>();

        extras.put(MPartieReseau.class.getSimpleName(), GConstantes.FIXME_JSON_PARTIE_RESEAU);

        lanceActivite(APartieReseau.class, extras);

    }

    private void connexion(){

        List<AuthUI.IdpConfig> fournisseursDeConnexion = new ArrayList<>();

        fournisseursDeConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(fournisseursDeConnexion)
                .build();

        this.startActivityForResult(intentionConnexion, GConstantes.MA_CONSTANTE_CODE_CONNEXION);

    }

    private void deconnexion(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                    GLog.activite("Déconnexion terminée");

                    vMenuPrincipal.initialiserConnexionInputs();

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GConstantes.MA_CONSTANTE_CODE_CONNEXION) {

            if (resultCode == RESULT_OK) {

                GLog.activite("Connexion réussie", resultCode);

            } else {

                GLog.activite("connexion échouée", resultCode);

            }

            vMenuPrincipal.initialiserConnexionInputs();
        }
    }

    // maybe we want to check after resumes. beacause we might be able to connect somewhere else
    @Override
    protected void onResume()
    {
        super.onResume();

        vMenuPrincipal.initialiserConnexionInputs();
    }

}
