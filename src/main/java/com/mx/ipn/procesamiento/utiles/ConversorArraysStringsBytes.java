package com.mx.ipn.procesamiento.utiles;

import java.nio.charset.Charset;

public class ConversorArraysStringsBytes {

    public static String [] convertirStrings (byte[][] byteStrings){        
        String[] data = new String[byteStrings.length];
        for (int i = 0; i < byteStrings.length; i++) {
            data[i] = new String(byteStrings[i], Charset.defaultCharset());

        }
        return data;
    }
    
    private static byte[][] convertirBytes (String[] strings) {
        byte[][] data = new byte[strings.length][];
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            data[i] = string.getBytes(Charset.defaultCharset());
        }
        return data;
    }
}