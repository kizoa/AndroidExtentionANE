package com.example.graz.androidextension;

/**
 * Created by Graz on 9/26/16.
 */
import java.util.HashMap;
import java.util.Map;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.example.graz.androidextension.VideoEditor.EGLExtractor;
import com.example.graz.androidextension.VideoEditor.VideoEditor;


/*
 * This class specifies the mapping between the actionscript functions and the native classes.
 */

public class AndroidANEContext extends FREContext
{
    public VideoEditor videoEditor;

    public AndroidANEContext() {
        videoEditor = new VideoEditor();

    }

    @Override
    public void dispose() {
    }

    @Override
    public Map<String, FREFunction> getFunctions() {

        Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
        functionMap.put("initBitmapDecoder", new InitBitmapDecoder() );
        functionMap.put("startBitmapDecoder", new StartBitmapDecoder() );
        functionMap.put("getNextBitmap", new GetNextBitmap() );
        functionMap.put("stopBitmapDecoder", new StopBitmapDecoder() );

        return functionMap;
    }
}
