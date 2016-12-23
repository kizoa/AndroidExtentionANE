package com.example.graz.androidextension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.example.graz.androidextension.VideoEditor.VideoEditor;

/**
 * Created by Graz on 12/23/16.
 */

public class StopBitmapDecoder  implements FREFunction {

    public FREObject call(FREContext ctx, FREObject[] passedArgs) {

        AndroidANEContext context = (AndroidANEContext) ctx;

        VideoEditor editor = context.videoEditor;

        editor.stop();

        return null;
    }

}