/*
 * For testing, delete this file before turning in
 */

import java.math.BigInteger;
import java.util.*;


public class main {

    public static void main(String[] args) {
        times2Test();
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
    public static boolean encryptTest(){
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
}
