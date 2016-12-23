package com.example.graz.androidextension.VideoEditor;



import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;


import com.adobe.fre.FREBitmapData;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREWrongThreadException;

import wseemann.media.FFmpegMediaMetadataRetriever;


/**
 * Created by Graz on 10/4/16.
 */

public class VideoEditor {


    private static FFmpegMediaMetadataRetriever mMediaRetriever;
    private int mCurrentFrame;
    private ExtractorThread mThread;


    public void initExtractor( String videoPath  ){

        mCurrentFrame = 0;
        mThread = new ExtractorThread( videoPath );
        mThread.start();

    }

    public void start() {
    }


    public void stop() {
    }

    public void getNextBitmap( FREBitmapData bmpdata) throws FREInvalidObjectException, FREWrongThreadException {


        Bitmap bmp = Bitmap.createBitmap( 1920 , 1088 , Bitmap.Config.ARGB_8888 );
        Canvas canvas = new Canvas( bmp );
        Paint paint = new Paint();
        paint.setColor( Color.RED );
        canvas.drawRect( 0F , 0F , (float) 1920 , (float) 1088 , paint );
        bmpdata.acquire();
        bmp.copyPixelsToBuffer( bmpdata.getBits());


        bmpdata.release();
        Log.d("TEST","bitmap release");
        bmp.recycle();

        //Thread thread = new GetBitmapThread( bmpdata );
       // thread.start();
       // mCurrentFrame++;
    }

    class ExtractorThread extends Thread {

        private String mVideoPath;

        public ExtractorThread( String videoPath )
        {
            mVideoPath = videoPath;
        }

        @Override
        public void run() {
            super.run();
            Log.d( "TEST" , "Thread init retriever" );
    //        mMediaRetriever = new FFmpegMediaMetadataRetriever();
    //        mMediaRetriever.setDataSource( mVideoPath );

        }

    }

    class GetBitmapThread extends Thread {

        FREBitmapData bmpdata;

        public GetBitmapThread( FREBitmapData bmpdata )
        {
            this.bmpdata = bmpdata;
        }

        @Override
        public void run()  {
            super.run();
            try {
                Log.d( "TEST" , "Thread get bitmap" );
//                Bitmap bmp = mMediaRetriever.getFrameAtTime( mCurrentFrame * 1000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);

                Bitmap bmp = Bitmap.createBitmap( 1920 , 1088 , Bitmap.Config.ARGB_8888 );
                Canvas canvas = new Canvas( bmp );
                Paint paint = new Paint();
                paint.setColor( Color.RED );
                canvas.drawRect( 0F , 0F , (float) 1920 , (float) 1088 , paint );
                bmpdata.acquire();
                bmp.copyPixelsToBuffer( bmpdata.getBits());

                bmp.recycle();
                bmpdata.release();
            } catch (FREInvalidObjectException e) {
                e.printStackTrace();
            } catch (FREWrongThreadException e) {
                e.printStackTrace();
            }

        }
    }

}



