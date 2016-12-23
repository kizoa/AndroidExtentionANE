package com.example.graz.androidextension;

import com.adobe.fre.FREBitmapData;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.example.graz.androidextension.VideoEditor.VideoEditor;

/**
 * Created by Graz on 12/23/16.
 */

public class GetNextBitmap implements FREFunction {

    public FREObject call(FREContext ctx, FREObject[] passedArgs) {

        AndroidANEContext context = (AndroidANEContext) ctx;

        VideoEditor editor = context.videoEditor;
        try {
            FREBitmapData bmpdata = (FREBitmapData) passedArgs[1];
            bmpdata.acquire();
            editor.getNextBitmap( bmpdata );
            bmpdata.release();
        } catch (Exception e) {
            context.dispatchStatusEventAsync("ERROR_ASYNC", e.getMessage());
        }

        return null;

    }
}
