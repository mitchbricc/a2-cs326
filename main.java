/*
 * For testing, delete this file before turning in
 */

import java.math.BigInteger;
import java.util.*;


public class main {

    public static void main(String[] args) {
        addRoundKeyTest();
        // int n = 5;
        // System.out.println("2x"+n);
        // System.out.println("times2: " +AES.times2(n));
        // System.out.println("times: "+AES.times(n,2));
        // System.out.println("times: "+AES.times(2,n));
        //encryptTest();
        // int[][] state = {
        //     {219, 0x13, 0x53, 0x45},
        //     {19, 0x0a, 0x22, 0x5c},
        //     {83, 0x01, 0x01, 0x01},
        //     {69, 0xc6, 0xc6, 0xc6},
        // };

        // AES.mixColumns(state);

        // for (int i = 0; i < state.length; i++) {
        //     for (int j = 0; j < state[i].length; j++) {
        //         System.out.print(state[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }
    public static void times2Test(){
        ArrayList<int[]> tests = new ArrayList<int[]>();
        tests.add(new int[]{15,30});
        tests.add(new int[]{255,227});
        for (int[] test : tests) {
                System.out.println("The method times2Test "+(AES.times2(test[0]) == test[1]));
                System.out.println(test[0] +" X "+2+" = "+test[1]);
                System.out.println("value returned: "+AES.times2(test[0]));
        }
    }
    public static void timesTest(){
        ArrayList<int[]> tests = new ArrayList<int[]>();
        tests.add(new int[]{15,1,15});
        tests.add(new int[]{15,3,17});
        tests.add(new int[]{255,45,25});
        tests.add(new int[]{137,127,209});
        for (int[] test : tests) {
            if (true){//AES.times(test[0],test[1]) != test[2]) {
                System.out.println("The method timesTest "+(AES.times(test[0],test[1]) == test[2]));
                System.out.println(test[0] +" X "+test[1]+" = "+test[2]);
                System.out.println("value returned: "+AES.times(test[0],test[1]));
            }
        }
    }
    
    public static void inverseMixColumnsTest(){
        System.out.println("mixColumnsTest");
        int[][] m = new int[4][4];
        for (int i = 0; i < 16; i++) {
            m[i%4][i/4] = i;
        }
        System.out.println("output should match: ");
        AES.printMatrix(m);
        AES.mixColumns(m);
        System.out.println("output: ");
        AES.inverseMixColumns(m);
        AES.printMatrix(m);

    }

    public static void mixColumnsTest(){
        System.out.println("mixColumnsTest");
        int[][] m = new int[4][4];
        for (int i = 0; i < 16; i++) {
            m[i%4][i/4] = (i/4) + 1;
        }
        AES.printMatrix(m);
        AES.mixColumns(m);
        System.out.println("mix");
        AES.printMatrix(m);
        System.out.println("test 2");
        for (int i = 0; i < 16; i++) {
            m[i%4][i/4] = i;
        }
        
        AES.printMatrix(m);
        AES.mixColumns(m);
        System.out.println("mix");
        AES.printMatrix(m);
    }

    public static void inverseSubstituteBytesTest(){
        System.out.println("inverseSubstituteBytesTest");
        int[][] m = new int[4][4];
        for (int i = 0; i < 16; i++) {
            m[i%4][i/4] = i;
        }
        AES.printMatrix(m);
        AES.forwardSubstituteBytes(m);
        AES.inverseSubstituteBytes(m);
        System.out.println("output should match");
        AES.printMatrix(m);
    }

    public static void shiftRowsTest(){
        System.out.println("shiftrowsTest");
        String s = "11213141122232421323334314243444";
        int[][] m = AES.hexStringToByteArray(s);
        AES.printMatrix(m);
        AES.shiftRows(m);
        System.out.println("output should match");
        String o = "11223344122334411324314214213243";
        int[][] state = AES.hexStringToByteArray(o);
        AES.printMatrix(state);
        System.out.println("output");
        AES.printMatrix(m);
    }
     public static void addRoundKeyTest(){
        System.out.println("addRoundKeyTest");
        int[][] m = new int[4][4];
        for (int i = 0; i < 16; i++) {
            m[i%4][i/4] = i/4;
        }
        AES.printMatrix(m);
        int[][] k = AES.hexStringToByteArray("01020304020406080001020301020304");
        int[] w = AES.expandKey(k);
        System.out.println("last word of key");
        for (int j = 0; j < 4; j++) {
            System.out.printf("%02x ".toUpperCase(), w[3]>>8*(3-j)&0xff);
        }
        AES.addRoundKey(m, w, 1);
        System.out.println("\noutput");
        AES.printMatrix(m);
     }

    public static void encryptTest(){
        String plaintext = "0123456789abcdeffedcba9876543210";
        String key = "0f1571c947d9e8590cb7add6af7f6798";
        String ciphertext = "ff0b844a0853bf7c6934ab4364148fb9";
        int[][] m = AES.encrypt(plaintext, key);
        AES.printMatrix(m);
    }

    public static boolean eanddTest(){
        String plaintext = "helloworld this is a sentence of unknown length. I hope it is long enough";
        byte[] bytes = plaintext.getBytes();
        String plainHex = "";
        for (int i = 0; i < 8; i++) {
            plainHex += String.format("%02X", bytes[i]);
        }
        System.out.println("plaintext: "+plainHex);
        String key = "this is a key that is useless";
        byte[] bkey = key.getBytes();
        String keyHex = "";
        for (int i = 0; i < 8; i++) {
            keyHex += String.format("%02X", bkey[i]);
        }
        System.out.println("keyHex: "+keyHex);
        int[][] c = AES.encrypt(plainHex,keyHex);
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

    public static String textToHex(String plaintext){
        byte[] bytes = plaintext.getBytes();
        String plainHex = "";
        for (int i = 0; i < 8; i++) {
            plainHex += String.format("%02X", bytes[i]);
        }
        return plainHex;
    }
    public static String hexToString(String plainHex){
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
}
