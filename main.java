/*
 * For testing, delete this file before turning in
 */
public class main {

    public static void main(String[] args) {
        int[][] state = {
            {219, 0x13, 0x53, 0x45},
            {19, 0x0a, 0x22, 0x5c},
            {83, 0x01, 0x01, 0x01},
            {69, 0xc6, 0xc6, 0xc6},
        };

        AES.mixColumns(state);

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
    }
}
