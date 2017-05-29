
public class Main {

    public static void main(String[] args) {

        int dimensao = 4;

        Cromossomo c = new Cromossomo(dimensao);

//        System.out.println(c);
        System.out.println(c.getAptidao());

        int[][] genesPai = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        int[][] genesMae = new int[][]{
                {8, 8, 8, 8},
                {8, 8, 8, 8},
                {8, 8, 8, 8},
                {8, 8, 8, 8}};

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

        //percorrer a segunda metade dos filhos vendo o que ainda nao tem
        for (int i = dimensao / 2; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {

                for (int pi = 0; pi < dimensao; pi++) {
                    for (int pj = 0; pj < dimensao; pj++) {
                        if(!arrayPossuiElementoPrimeiraMetade(filho2, genesPai[pi][pj])){
                            filho2[i][j] = genesPai[pi][pj];
                            break;
                        }
                    }
                    if(filho2[i][j] >0){
                        break;
                    }
                }
                filho1[i][j] = genesMae[i][j];
                filho2[i][j] = genesPai[i][j];



            }
        }

        filho1.toString();
        filho2.toString();

    }

    public static boolean arrayPossuiElementoPrimeiraMetade(int[][] array, int elemento) {
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
