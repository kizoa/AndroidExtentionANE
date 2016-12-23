package com.example.graz.androidextension.VideoEditor;

/**
 * Created by Graz on 10/18/16.
 */

import android.os.AsyncTask;

/**
 * Asynchronous task to extract frames
 * @author Pierre LEVY
 */

public class ExtractAsyncTask extends AsyncTask<Void, String, String>
{
    private String mFilename;
    private Extractor mExtractor;
    private ExtractEventListener mListener;
    private Settings mSettings;

    ExtractAsyncTask(String filename, Extractor extractor, ExtractEventListener listener, Settings settings)
    {
        mFilename = filename;
        mExtractor = extractor;
        mListener = listener;
        mSettings = settings;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        mListener.message( "Start extraction ..." );
    }

    @Override
    protected void onProgressUpdate(String... values)
    {
        super.onProgressUpdate(values);
        mListener.message(values[0]);

    }

    @Override
    protected String doInBackground(Void... arg0)
    {
        String ret = "Extraction completed successfully !";
        try
        {

            mExtractor.extractMpegFrames(mFilename);
        } catch (Exception e)
        {
            ret = "Extraction aborted on error : " + e.getMessage();
            e.printStackTrace();

        }
        return ret;

    }

    @Override
    protected void onPostExecute(String result)
    {
        mListener.message( result );
    }

    public void progressMessage(String message)
    {
        publishProgress(message);
    }
}