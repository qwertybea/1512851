package ca.cours5b5.justinfofana.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ca.cours5b5.justinfofana.global.DebugTools;

public abstract class Activite extends AppCompatActivity {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        DebugTools.messageLog(this,"onCreate");
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        DebugTools.messageLog(this,"onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        DebugTools.messageLog(this,"onPause");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        DebugTools.messageLog(this, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        DebugTools.messageLog(this, "onSaveInstanceState");
    }

}