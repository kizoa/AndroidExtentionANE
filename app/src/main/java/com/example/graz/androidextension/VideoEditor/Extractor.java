package com.example.graz.androidextension.VideoEditor;

/**
 * Created by Graz on 10/18/16.
 */
import java.io.IOException;

/**
 * Extractor interface
 * @author Pierre LEVY
 */

public interface Extractor
{
    /**
     * Extract images from a given video file
     * @param filePath The video absolute path
     * @param task The asynchronous task launching the extractor
     * @param settings The generation settings
     * @throws IOException if a file error occurs
     */
    void extractMpegFrames(String filePath) throws IOException;
}
