package com.blacksheep.teacher.nuanceApp;

public class AppInfo
{
    /**
     * The login parameters should be specified in the following manner:
     *
     * public static final String SpeechKitServer = "ndev.server.name";
     *
     * public static final int SpeechKitPort = 1000;
     *
     * public static final String SpeechKitAppId = "ExampleSpeechKitSampleID";
     *
     * public static final byte[] SpeechKitApplicationKey =
     * {
     *     (byte)0x38, (byte)0x32, (byte)0x0e, (byte)0x46, (byte)0x4e, (byte)0x46, (byte)0x12, (byte)0x5c, (byte)0x50, (byte)0x1d,
     *     (byte)0x4a, (byte)0x39, (byte)0x4f, (byte)0x12, (byte)0x48, (byte)0x53, (byte)0x3e, (byte)0x5b, (byte)0x31, (byte)0x22,
     *     (byte)0x5d, (byte)0x4b, (byte)0x22, (byte)0x09, (byte)0x13, (byte)0x46, (byte)0x61, (byte)0x19, (byte)0x1f, (byte)0x2d,
     *     (byte)0x13, (byte)0x47, (byte)0x3d, (byte)0x58, (byte)0x30, (byte)0x29, (byte)0x56, (byte)0x04, (byte)0x20, (byte)0x33,
     *     (byte)0x27, (byte)0x0f, (byte)0x57, (byte)0x45, (byte)0x61, (byte)0x5f, (byte)0x25, (byte)0x0d, (byte)0x48, (byte)0x21,
     *     (byte)0x2a, (byte)0x62, (byte)0x46, (byte)0x64, (byte)0x54, (byte)0x4a, (byte)0x10, (byte)0x36, (byte)0x4f, (byte)0x64
     * };
     *
     * Please note that all the specified values are non-functional
     * and are provided solely as an illustrative example.
     *
     */

    /* Please contact Nuance to receive the necessary connection and login parameters */
    /*public static final String SpeechKitServer ="sandbox.nmdp.nuancemobility.net" *//* Enter your server here*//*;

    public static final int SpeechKitPort =443 *//* Enter your port here *//*;
    
    public static final boolean SpeechKitSsl = false;

    public static final String SpeechKitAppId ="NMDPTRIAL_vovanderr20120304135255" *//* Enter your ID here *//*;

    public static final byte[] SpeechKitApplicationKey = {
            *//* Enter your application key here:
            (byte)0x00, (byte)0x01, ... (byte)0x00
            *//*
            (byte)0x15, (byte)0xb2, (byte)0xb4, (byte)0x46, (byte)0x7b, (byte)0xee, (byte)0xa6, (byte)0x80, (byte)0x28, (byte)0x7d,
            (byte)0x8b, (byte)0xd7, (byte)0xdc, (byte)0xcb, (byte)0x9b, (byte)0x08, (byte)0xf9, (byte)0x8d, (byte)0x90, (byte)0xa5,
            (byte)0x30, (byte)0xfd, (byte)0x7a, (byte)0xbf, (byte)0x24, (byte)0x64, (byte)0x07, (byte)0x2e, (byte)0x54, (byte)0xa1,
            (byte)0x0c, (byte)0x69, (byte)0x54, (byte)0xc0, (byte)0xb0, (byte)0xcf, (byte)0xae, (byte)0x56, (byte)0x4f, (byte)0xd7,
            (byte)0xd3, (byte)0xda, (byte)0xae, (byte)0xaf, (byte)0xa5, (byte)0x49, (byte)0xb7, (byte)0x9e, (byte)0xd6, (byte)0x47,
            (byte)0x43, (byte)0x06, (byte)0x45, (byte)0xbe, (byte)0xc6, (byte)0x69, (byte)0x22, (byte)0xcd, (byte)0x97, (byte)0x76,
            (byte)0x6f, (byte)0xa3, (byte)0xe0, (byte)0xc3};*/
    public static final String SpeechKitServer ="sandbox.nmdp.nuancemobility.net" /* Enter your server here*/;

    public static final int SpeechKitPort =443 /* Enter your port here */;

    public static final boolean SpeechKitSsl = false;

    public static final String SpeechKitAppId ="NMDPTRIAL_NextStepEntertainment20120423074902" /* Enter your ID here */;
/*    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return data;
    }*/
    public static final byte[] SpeechKitApplicationKey =  {(byte)0xdd, (byte)0x77, (byte)0x8a, (byte)0xa6, (byte)0xfc, (byte)0xad, (byte)0x09, (byte)0xee, (byte)0x8d,
        (byte)0x8a, (byte)0x80, (byte)0x95, (byte)0x69, (byte)0x78, (byte)0xa0, (byte)0xe4, (byte)0xb5, (byte)0xec, (byte)0x38, (byte)0x3f, (byte)0x65, (byte)0xf6,
        (byte)0xfa, (byte)0xba, (byte)0x11, (byte)0xb8, (byte)0xe3, (byte)0xb5, (byte)0x2c, (byte)0x88, (byte)0xb3, (byte)0x09, (byte)0xff, (byte)0x3d, (byte)0xb0,
        (byte)0xfc, (byte)0xea, (byte)0xb9, (byte)0xea, (byte)0x77, (byte)0x43, (byte)0x6a, (byte)0x0c, (byte)0xac, (byte)0xe2, (byte)0xac, (byte)0x91, (byte)0x97,
        (byte)0x99, (byte)0xc9, (byte)0xd6, (byte)0xfe, (byte)0xbf, (byte)0xd1, (byte)0xb8, (byte)0xd0, (byte)0x89, (byte)0x9b, (byte)0x86, (byte)0xb5, (byte)0x23,
        (byte)0x39, (byte)0xbf, (byte)0x23};

}
