/*
 * For testing, delete this file before turning in
 */

import java.math.BigInteger;

public class main {

    public static void main(String[] args) {
        // encryptTest();
        int[][] state = {
                { 1, 0x13, 0x53, 0x45 },
                { 2, 0x0a, 0x22, 0x5c },
                { 3, 0x01, 0x01, 0x01 },
                { 4, 0xc6, 0xc6, 0xc6 },
        };

        AES.mixColumns(state);
        printState(state);

        AES.inverseMixColumns(state);
        printState(state);
    }

    public static String textToHex(String plaintext) {
        byte[] bytes = plaintext.getBytes();
        String plainHex = "";
        for (int i = 0; i < 8; i++) {
            plainHex += String.format("%02X", bytes[i]);
        }
        return plainHex;
    }

    public static String hexToString(String plainHex) {
        StringBuilder result = new StringBuilder();
        // Split the hex string into pairs of characters
        for (int i = 0; i < plainHex.length(); i += 2) {
            // Get the substring of 2 characters
            String byteString = plainHex.substring(i, i + 2);
            // Convert the hex pair to an integer
            int charCode = Integer.parseInt(byteString, 16);
            result.append((char) charCode);
        }
        return result.toString();
    }

    public static void printState(int[][] s) {
        for (int i = 0; i < s.length; i++) {
            for (int k = 0; k < s[i].length; k++) {
                System.out.print(s[i][k] + " ");
            }
            System.out.println();
        }
    }

    public static boolean encryptTest() {
        String plaintext = "helloworld this is a sentence of unknown length. I hope it is long enough";
        byte[] bytes = plaintext.getBytes();
        String plainHex = "";
        for (int i = 0; i < 8; i++) {
            plainHex += String.format("%02X", bytes[i]);
        }
        System.out.println("plaintext: " + plainHex);
        String key = "this is a key that is useless";
        byte[] bkey = key.getBytes();
        String keyHex = "";
        for (int i = 0; i < 8; i++) {
            keyHex += String.format("%02X", bkey[i]);
        }
        System.out.println("keyHex: " + keyHex);
        int[][] c = AES.encrypt(plainHex, keyHex);
        AES.printMatrix(c);

        StringBuilder result = new StringBuilder();

        // Split the hex string into pairs of characters
        for (int i = 0; i < plainHex.length(); i += 2) {
            // Get the substring of 2 characters
            String byteString = plainHex.substring(i, i + 2);
            // Convert the hex pair to an integer
            int charCode = Integer.parseInt(byteString, 16);
            result.append((char) charCode);
        }
        System.out.println(result);
        return false;
    }
}
