package com.penguin.padang.pasir.listbuah.helpers.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jowy on 8/12/16.
 */
public class Md5Converter {
//    private String hashed;

    public static String getMd5(String input) {
        String hashed = "";
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashText = number.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            hashed = hashText;

        } catch (NoSuchAlgorithmException e ) {
            e.printStackTrace();
        }

        return hashed;
    }
}
