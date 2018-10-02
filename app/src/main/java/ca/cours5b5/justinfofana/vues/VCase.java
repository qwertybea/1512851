package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import java.text.MessageFormat;

public class VCase extends AppCompatButton {
    public VCase(Context context) {
        super(context);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /*
     * Afficher la rangée et la colonne
     *
     * Changer la couleur de fond si désiré
     *
     */
    public VCase(Context context, int rangee, int colonne) {
        super(context);
        this.setText(MessageFormat.format("{0}, {1}", rangee, colonne));
    }

    private void initialiser() {}
}
