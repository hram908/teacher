package com.blacksheep.teacher.game.nativefunction;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/26/12
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class NativeFunction {

    static {
        System.loadLibrary("ndksetup");
    }

    private static native void bitmapCode(int [] value,int lenght,int targetColor);

    public static void bitmapCodeNative(int [] value,int lenght,int targetColor)
    {
        bitmapCode(value, lenght, targetColor);
    }

}
