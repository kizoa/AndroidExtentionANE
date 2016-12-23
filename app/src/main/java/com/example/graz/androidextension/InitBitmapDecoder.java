package com.example.graz.androidextension;

import android.os.Environment;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREFunction;
import com.example.graz.androidextension.VideoEditor.VideoEditor;

import java.io.IOException;

/**
 * Created by Graz on 12/23/16.
 */

public class InitBitmapDecoder implements FREFunction {

    public FREObject call(FREContext ctx, FREObject[] passedArgs) {

        AndroidANEContext context = (AndroidANEContext) ctx;

        VideoEditor editor = context.videoEditor;

        String videoFilePath = Environment.getExternalStorageDirectory() + "/test.mp4";

        Log.d( "TEST" , "InitBitmapDecoder file : " + videoFilePath );
        editor.initExtractor( videoFilePath );

        ctx.dispatchStatusEventAsync("SETUP_DECODE_FINISHED","test");
        Log.d( "TEST" , "InitBitmapDecoder sent event" );
        return null;

    }

}