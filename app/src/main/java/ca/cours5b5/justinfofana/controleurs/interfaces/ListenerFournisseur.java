package ca.cours5b5.justinfofana.controleurs.interfaces;

public abstract class ListenerFournisseur {
    public abstract void executer(Object... args);
    public boolean peutExecuter(Object... args) {
        return true;
    }
}
