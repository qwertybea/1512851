package ca.cours5b5.justinfofana.global;

import android.util.Log;

import ca.cours5b5.justinfofana.vues.Vue;

public class DebugTools {

    static { Log.d("Atelier04", Vue.class.getSimpleName() + "::static"); }

    public static void messageLog(Object object, String nomMethode) {
        /*
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // 0 stacktrace
        // 1 java lang
        // 2 debug tools (this)
        String [] classes_paths = stackTraceElements[3].getClassName().split("\\.");
        Log.d("Atelier04", MessageFormat.format("{0}::{1}::{2}", classes_paths[classes_paths.length - 1],
                stackTraceElements[4].getMethodName(), object.getClass().getSimpleName()));

        //Log.d("Atelier04", object.getClass().getSimpleName() + "::" + nomMethode);

        //Log.d("Atelier04", MessageFormat.format("{0}::{1}", stackTraceElements[stackTraceElements.length - 1].getClassName(), stackTraceElements[stackTraceElements.length - 1].getMethodName()));
        //Log.d("Atelier04", MessageFormat.format("{0}::{1}", stackTraceElements.length, stackTraceElements[stackTraceElements.length - 1].getMethodName()));
        */
        Log.d("Atelier04", object.getClass().getSimpleName() + "::" + nomMethode);
    }

    public static void prinntStackTrace() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        for (StackTraceElement call : trace) {
            Log.d("Atelier07", call.toString());
        }
    }

}
