package com.example.graz.androidextension.VideoEditor;

/**
 * Created by Graz on 10/18/16.
 */

/**
 * ExtractEventListener interface
 * @author Pierre LEVY
 */

public interface ExtractEventListener
{
    /**
     * Send progressMessage to listener
     * @param message
     */
    void message(String message );

    /**
     * Clear listener progressMessage queue
     */
    void clearMessages();
}
