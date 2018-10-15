package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import java.text.MessageFormat;

import ca.cours5b5.justinfofana.global.DebugTools;
import ca.cours5b5.justinfofana.global.GCouleur;

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

    public VCase(Context context, int rangee, int colonne) {
        super(context);
        this.setText(MessageFormat.format("{0}, {1}", rangee, colonne));
    }

    private void initialiser() {}

    public void afficherJeton(GCouleur jeton) {
        int color = Color.LTGRAY;

        if (jeton == GCouleur.ROUGE) {
            color = Color.RED;
        } else if (jeton == GCouleur.JAUNE) {
            color = Color.YELLOW;
        }

        this.setBackgroundColor(color);
        DebugTools.prinntStackTrace();
    }
}
