/*
 * For testing, delete this file before turning in
 */
public class main {

    public static void main(String[] args) {
        testMixColumns();
        // testInverseMixColumns();
    }

    private static void testMixColumns() {
        System.out.println("Testing mixColumns");
        int[][] state = {
                { 1, 0x13, 0x53, 0x45 },
                { 2, 0x0a, 0x22, 0x5c },
                { 3, 0x01, 0x01, 0x01 },
                { 4, 0xc6, 0xc6, 0xc6 },
        };

        AES.mixColumns(state);

        AES.printMatrix(state);
    }

    private static void testInverseMixColumns() {
        System.out.println("Testing inverseMixColumns");
        int[][] state = {
                { 1, 0x13, 0x53, 0x45 },
                { 1, 0x0a, 0x22, 0x5c },
                { 1, 0x01, 0x01, 0x01 },
                { 1, 0xc6, 0xc6, 0xc6 },
        };

        AES.mixColumns(state);
        AES.inverseMixColumns(state);
        AES.printMatrix(state);
    }
}
