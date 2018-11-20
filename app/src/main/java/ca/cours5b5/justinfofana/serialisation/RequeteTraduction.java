package ca.cours5b5.justinfofana.serialisation;

import android.content.Context;

import java.text.MessageFormat;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.global.GCouleur;
import ca.cours5b5.justinfofana.global.GLog;

public class RequeteTraduction {

    private RequeteTraduction() {}


    public static String getMsgGagnat(Context context, GCouleur gCouleur) {

        String msg = getStringResourceByName(context, "MSG_GAGNANT");

        String couleur = getCouleur(context, gCouleur);

        GLog.donnees(msg, couleur);

        msg = MessageFormat.format(msg, couleur);

        return msg;
    }

    public static String getCouleur(Context context, GCouleur gCouleur) {

        String color_name = "TXT_" + gCouleur.name().toUpperCase();

        return getStringResourceByName(context, color_name);
    }

    private static String getStringResourceByName(Context context, String aString) {

        String packageName = context.getPackageName();

        int resId = context.getResources().getIdentifier(aString, "string", packageName);

        return context.getString(resId);
    }

}
