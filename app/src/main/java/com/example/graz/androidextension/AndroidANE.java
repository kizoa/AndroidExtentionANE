package com.example.graz.androidextension;

/**
 * Created by Graz on 9/26/16.
 */

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

/*
 * Initialization and finalization class of native extension.
 */
public class AndroidANE implements FREExtension
{
    /*
     * Creates a new instance of ANESampleContext when the context is created
     * from the actionscript code.
     */
    public FREContext createContext(String extId) {
        AndroidANEContext ctx = new AndroidANEContext();
        //ctx.videoEditor.setContext(ctx);
        //ctx.videoEditor2.setContext(ctx);
        return ctx;
    }

    /*
     * Called if the extension is unloaded from the process. Extensions
     * are not guaranteed to be unloaded; the runtime process may exit without
     * doing so.
     */
    @Override
    public void dispose() {
    }

    /*
      * Extension initialization.
      */
    public void initialize( ) {
    }

    public void finalizer( ) {
    }
}

