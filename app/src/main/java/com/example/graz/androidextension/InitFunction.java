package com.example.graz.androidextension;

/**
 * Created by Graz on 9/26/16.
 */



        import com.adobe.fre.FREContext;
        import com.adobe.fre.FREFunction;
        import com.adobe.fre.FREObject;

public class InitFunction implements FREFunction  {
    public FREObject call(FREContext ctx, FREObject[] passedArgs) {

        System.out.println("initFunction successfully called.");
        return null;
    }
}

