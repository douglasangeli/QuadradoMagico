import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int dimensao = 4;

        Cromossomo c = new Cromossomo(dimensao);

//        System.out.println(c);
        System.out.println(c.getAptidao());

        int[][] genesPai = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        int[][] genesMae = new int[][]{
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}};

        int[][] filho1 = new int[dimensao][dimensao];
        int[][] filho2 = new int[dimensao][dimensao];

        //filho 1, primeira metade do pai
        //filho 2, primeira metade da mae
        for (int i = 0; i < dimensao / 2; i++) {
            for (int j = 0; j < dimensao; j++) {
                filho1[i][j] = genesPai[i][j];
                filho2[i][j] = genesMae[i][j];
            }
        }

        //percorrer a segunda metade do filho1 vendo os elementos da mae que ele nao tem
        for (int i = dimensao / 2; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {

                //percorrer a mae
                for (int pi = 0; pi < dimensao; pi++) {
                    for (int pj = 0; pj < dimensao; pj++) {

                        if (filho1[i][j] == 0 && !arrayPossuiElemento(filho1, genesMae[pi][pj])) {
                            filho1[i][j] = genesMae[pi][pj];
                        }

                        if (filho2[i][j] == 0 && !arrayPossuiElemento(filho2, genesPai[pi][pj])) {
                            filho2[i][j] = genesPai[pi][pj];
                        }

                        if (filho1[i][j] > 0 && filho2[i][j] > 0) {
                            break;
                        }
                    }
                    if (filho1[i][j] > 0 && filho2[i][j] > 0) {
                        break;
                    }
                }
            }
        }

        // Faz mutacao trocando dois pontos randomicos
        Random rand = new Random();
        int h1 = rand.nextInt(dimensao);
        int v1 = rand.nextInt(dimensao);
        int h2 = rand.nextInt(dimensao);
        int v2 = rand.nextInt(dimensao);

        int aux = filho1[h1][v1];
        filho1[h1][v1] = filho1[h2][v2];
        filho1[h2][v2] = aux;

        aux = filho2[h1][v1];
        filho2[h1][v1] = filho2[h2][v2];
        filho2[h2][v2] = aux;

        filho1.toString();
    }

    public static boolean arrayPossuiElemento(int[][] array, int elemento) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == elemento) {
                    return true;
                }
            }
        }
        return false;
    }
}
