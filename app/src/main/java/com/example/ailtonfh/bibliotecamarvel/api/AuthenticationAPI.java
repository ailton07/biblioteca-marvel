package com.example.ailtonfh.bibliotecamarvel.api;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by AiltonFH on 07/06/2016.
 */
public class AuthenticationAPI {

    static private String privateKey = "";
    static public String publicKey = "";

    private String timeStamp;

    public AuthenticationAPI() {
        this.timeStamp = timeStampVal();
    }

    public static String getPublicKey() {
        return publicKey;
    }

    // Retorna um time stamp salvo
    public String getTimeStamp() {
        return timeStamp;
    }

    public void timeStampReload() {
        this.timeStamp = timeStampVal();
    }

    // Retorna um novo time stamp
    private String timeStampVal(){
        //Long tsLong = System.currentTimeMillis()/1000;
        Long tsLong = System.currentTimeMillis()/1000 ;
        return tsLong.toString();
    }

    // Retorna md5(ts+privateKey+publicKey)
    public String getHash(){
        return md5(getTimeStamp()+privateKey+publicKey);
    }

    public String md5(String s)
    {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
