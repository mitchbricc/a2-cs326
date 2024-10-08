/*
 * For testing, delete this file before turning in
 */

import java.util.*;

public class main {

    public static void main(String[] args) {
        inverseAddRoundKeyTest();
    }

    public static void times2Test() {
        ArrayList<int[]> tests = new ArrayList<int[]>();
        tests.add(new int[] { 15, 30 });
        tests.add(new int[] { 255, 227 });
        for (int[] test : tests) {
            System.out.println("The method times2Test " + (AES.times2(test[0]) == test[1]));
            System.out.println(test[0] + " X " + 2 + " = " + test[1]);
            System.out.println("value returned: " + AES.times2(test[0]));
        }
    }

    public static void timesTest() {
        ArrayList<int[]> tests = new ArrayList<int[]>();
        tests.add(new int[] { 15, 1, 15 });
        tests.add(new int[] { 15, 3, 17 });
        tests.add(new int[] { 255, 45, 25 });
        tests.add(new int[] { 137, 127, 209 });
        for (int[] test : tests) {
            if (true) {// AES.times(test[0],test[1]) != test[2]) {
                System.out.println("The method timesTest " + (AES.times(test[0], test[1]) == test[2]));
                System.out.println(test[0] + " X " + test[1] + " = " + test[2]);
                System.out.println("value returned: " + AES.times(test[0], test[1]));
            }
        }
    }

    public static void inverseMixColumnsTest() {
        System.out.println("mixColumnsTest");
        int[][] m = new int[4][4];
        for (int i = 0; i < 16; i++) {
            m[i % 4][i / 4] = i;
        }
        System.out.println("output should match: ");
        AES.printMatrix(m);
        AES.mixColumns(m);
        System.out.println("output: ");
        AES.inverseMixColumns(m);
        AES.printMatrix(m);

    }

    public static void forwardSubstituteByteTest() {
        System.out.println("forwardSubstituteByteTest");
        int[] tests = { 0xB6 };
        for (int test : tests) {
            System.out.println("output (should be 0x4e): " + Integer.toHexString(AES.forwardSubstituteByte(test)));
        }
    }

    public static void mixColumnsTest() {
        System.out.println("mixColumnsTest");
        int[][] m = new int[4][4];
        for (int i = 0; i < 16; i++) {
            m[i % 4][i / 4] = (i / 4) + 1;
        }
        AES.printMatrix(m);
        AES.mixColumns(m);
        System.out.println("mix");
        AES.printMatrix(m);
        System.out.println("test 2");
        for (int i = 0; i < 16; i++) {
            m[i % 4][i / 4] = i;
        }

        AES.printMatrix(m);
        AES.mixColumns(m);
        System.out.println("mix");
        AES.printMatrix(m);
    }

    public static void inverseAddRoundKeyTest() {
        System.out.println("inverseAddRoundKeyTest");

        int[][] s = {
                { 0x00, 0x01, 0x02, 0x04 },
                { 0x00, 0x01, 0x02, 0x04 },
                { 0x00, 0x01, 0x02, 0x04 },
                { 0x00, 0x01, 0x02, 0x04 },
        };

        int[][] key = {
                { 0x01, 0x02, 0x00, 0x01 },
                { 0x02, 0x04, 0x01, 0x02 },
                { 0x03, 0x06, 0x02, 0x03 },
                { 0x04, 0x08, 0x03, 0x04 }
        };
        int[] kS = AES.expandKey(key);
        AES.printMatrix(s);
        System.out.println("should match after addRoundKey and inverseAddRoundKey");
        AES.addRoundKey(s, kS, 1);
        AES.inverseAddRoundKey(s, kS, 1);
        AES.printMatrix(s);
    }

    public static void addRoundKeyTest() {
        System.out.println("addRoundKeyTest");
        int[][] m = new int[4][4];
        for (int i = 0; i < 16; i++) {
            m[i % 4][i / 4] = i > 11 ? 4 : i / 4;
        }
        AES.printMatrix(m);
        int[][] k = AES.hexStringToByteArray("01020304020406080001020301020304");
        int[] w = AES.expandKey(k);
        System.out.println("last word of key");
        for (int j = 0; j < 4; j++) {
            System.out.printf("%02x ".toUpperCase(), w[3] >>> 8 * (3 - j));
        }
        AES.addRoundKey(m, w, 1);
        System.out.println("\noutput");
        AES.printMatrix(m);
    }

    public static void rConTest() {
        System.out.println("rConTest");
        for (int i = 1; i < 11; i++) {
            System.out.println("\ni: " + i);
            int word = AES.rCon(i);
            int byte1 = (word >>> 24) & 0xFF;
            int byte2 = (word >>> 16) & 0xFF;
            int byte3 = (word >>> 8) & 0xFF;
            int byte4 = word & 0xFF;
            System.out.printf("%02x ".toUpperCase(), byte1);
            System.out.printf("%02x ".toUpperCase(), byte2);
            System.out.printf("%02x ".toUpperCase(), byte3);
            System.out.printf("%02x ".toUpperCase(), byte4);
        }
    }

    public static void encryptTest() {
        String plaintext = "0123456789abcdeffedcba9876543210";
        String key = "0f1571c947d9e8590cb7add6af7f6798";
        String ciphertext = "ff0b844a0853bf7c6934ab4364148fb9";
        System.out.println("encryptTest");
        int[][] p = AES.hexStringToByteArray(plaintext);
        AES.printMatrix(p);
        System.out.println("output should match");
        int[][] o = AES.hexStringToByteArray(ciphertext);
        AES.printMatrix(o);
        System.out.println("output");
        int[][] m = AES.encrypt(plaintext, key);
        AES.printMatrix(m);
    }

    public static void decryptTest() {
        String plaintext = "0123456789abcdeffedcba9876543210";
        String key = "0f1571c947d9e8590cb7add6af7f6798";
        String ciphertext = "ff0b844a0853bf7c6934ab4364148fb9";
        System.out.println("decryptTest");
        
        int[][] p = AES.hexStringToByteArray(plaintext);
        AES.printMatrix(p);

        System.out.println("output should match");

        int[][] m = AES.decrypt(ciphertext, key);
        AES.printMatrix(m);
    }

    public static void inverseShiftRowsTest() {
        System.out.println("inverseShiftRowsTest");
        int[][] s = {
                { 11, 12, 13, 14 },
                { 21, 22, 23, 24 },
                { 31, 32, 33, 34 },
                { 41, 42, 43, 44 }
        };
        AES.printMatrix(s);
        System.out.println("should match after shift and inverse shift");
        AES.shiftRows(s);
        AES.inverseShiftRows(s);
        AES.printMatrix(s);
    }

    public static void rotWordTest() {
        System.out.println("\nrotWordTest");
        int r = AES.rotWord(0x01020304);
        System.out.println("should match 02030401: " + Integer.toHexString(r));
    }

    public static void expandKeyTest() {
        System.out.println("\nexpandKeyTest");
        int[][] key = {
                { 0x01, 0x02, 0x00, 0x01 },
                { 0x02, 0x04, 0x01, 0x02 },
                { 0x03, 0x06, 0x02, 0x03 },
                { 0x04, 0x08, 0x03, 0x04 }
        };
        int[] kS = AES.expandKey(key);
        for (int i = 0; i < kS.length; i++) {
            System.out.printf("%02x ", kS[i]);
        }
        System.out.println();
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

    public static void eanddTest(){
        String plaintext = "0123456789abcdeffedcba9876543210";
        String key = "0f1571c947d9e8590cb7add6af7f6798";
        String ciphertext = "ff0b844a0853bf7c6934ab4364148fb9";
        System.out.println("eandDTest");
        int[][] p = AES.hexStringToByteArray(plaintext);
        AES.printMatrix(p);
        System.out.println("encrypt");
        int[][] m = AES.encrypt(plaintext, key);
        System.out.println("decrypt");
        m = AES.decrypt(ciphertext, key);
        AES.printMatrix(m);
    }
}

