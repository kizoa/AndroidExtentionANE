package com.example.graz.androidextension.VideoEditor;

import android.support.v8.renderscript.*;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
//import android.renderscript.Allocation;
//import android.renderscript.Element;
//import android.renderscript.RenderScript;
//import android.renderscript.ScriptIntrinsicBlur;
//import android.renderscript.ScriptIntrinsicColorMatrix;
//import android.renderscript.ScriptIntrinsicYuvToRGB;
//import android.renderscript.Type;
import android.util.Size;


/**
 * Created by Graz on 9/30/16.
 */

public class RenderScriptHelper {



    // Convert to RGB using Intrinsic render script
    public static Bitmap convertYuvToRgbIntrinsic(RenderScript rs,byte[] data, int width, int height) {


        ScriptIntrinsicYuvToRGB yuvToRgbIntrinsic = ScriptIntrinsicYuvToRGB.create(rs, Element.RGBA_8888(rs));

        // Create the input allocation  memory for Renderscript to work with
        Type.Builder yuvType = new Type.Builder(rs, Element.U8(rs))
                .setX(width)
                .setY(height)
                .setYuvFormat(ImageFormat.NV21);

        Allocation aIn = Allocation.createTyped(rs, yuvType.create(), Allocation.USAGE_SCRIPT);
        // Set the YUV frame data into the input allocation
        aIn.copyFrom(data);



        // Create the output allocation
        Type.Builder rgbType = new Type.Builder(rs, Element.RGBA_8888(rs))
                .setX(width)
                .setY(height);

        Allocation aOut = Allocation.createTyped(rs, rgbType.create(), Allocation.USAGE_SCRIPT);



        yuvToRgbIntrinsic.setInput(aIn);
        // Run the script for every pixel on the input allocation and put the result in aOut
        yuvToRgbIntrinsic.forEach(aOut);

        // Create an output bitmap and copy the result into that bitmap
        Bitmap outBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        aOut.copyTo(outBitmap);

        return outBitmap ;

    }



    // Convert to RGB using render script - with Yuv2Rgb.rs
    public static Bitmap convertYuvToRgb(RenderScript rs,byte[] data, int width, int heigth) {



        // Input allocation
        Type.Builder yuvType = new Type.Builder(rs, Element.createPixel(rs, Element.DataType.UNSIGNED_8, Element.DataKind.PIXEL_YUV))
                .setX(width)
                .setY(heigth)
                .setMipmaps(false)
                .setYuvFormat(ImageFormat.NV21);
        Allocation ain = Allocation.createTyped(rs, yuvType.create(), Allocation.USAGE_SCRIPT);
        ain.copyFrom(data);


        // output allocation
        Type.Builder rgbType = new Type.Builder(rs, Element.RGBA_8888(rs))
                .setX(width)
                .setY(heigth)
                .setMipmaps(false);

        Allocation aOut = Allocation.createTyped(rs, rgbType.create(), Allocation.USAGE_SCRIPT);


        // Create the script
        ScriptC_Yuv2Rgb yuvScript = new ScriptC_Yuv2Rgb(rs);
        // Bind to script level -  set the allocation input and parameters from the java into the script level (thru JNI)
        yuvScript.set_gIn(ain);
        yuvScript.set_width(width);
        yuvScript.set_height(heigth);

        // invoke the script conversion method
        yuvScript.forEach_yuvToRgb(ain, aOut);

        Bitmap outBitmap = Bitmap.createBitmap(width, heigth, Bitmap.Config.ARGB_8888);
        aOut.copyTo(outBitmap) ;

        return outBitmap ;

    }






    public static Bitmap applyBlurEffectIntrinsic(RenderScript rs,Bitmap inBitmap, Size imageSize) {

        Bitmap outBitmap = inBitmap.copy(inBitmap.getConfig(), true);

        Allocation aIn = Allocation.createFromBitmap(rs, inBitmap,Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation aOut = Allocation.createTyped(rs, aIn.getType());

        //Blur the image
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // Set the blur radius
        script.setRadius(15f);
        script.setInput(aIn);
        script.forEach(aOut);
        aOut.copyTo(outBitmap);

        return outBitmap ;
    }



    public static Bitmap applyGrayScaleEffectIntrinsic(RenderScript rs, Bitmap inBitmap, Size imageSize) {

        Bitmap grayBitmap = inBitmap.copy(inBitmap.getConfig(), true);

        Allocation aIn = Allocation.createFromBitmap(rs, inBitmap,Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation aOut = Allocation.createTyped(rs, aIn.getType());

        //Make the image grey scale
        final ScriptIntrinsicColorMatrix scriptColor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        scriptColor.setGreyscale();
        scriptColor.forEach(aIn, aOut);
        aOut.copyTo(grayBitmap);

        return grayBitmap ;

    }

}
