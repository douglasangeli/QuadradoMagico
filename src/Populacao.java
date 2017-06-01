import java.util.*;

public class Populacao {

    private int tamanho = 7;
    private int dimensao;
    private ArrayList<Cromossomo> cromossomos;

    public Populacao(int dimensao) {
        this.dimensao = dimensao;
        this.cromossomos = new ArrayList<>();

        for (int i = 0; i < tamanho; i++) {
            this.cromossomos.add(new Cromossomo(dimensao));
        }
    }

    public Cromossomo getMelhorCromossomo() {
        Collections.sort(this.cromossomos);
        return this.cromossomos.get(0);
    }

    private ArrayList<Cromossomo> crossover(Cromossomo pai, Cromossomo mae) {

        ArrayList<Cromossomo> retorno = new ArrayList<>();
        int[][] genesPai = pai.getGenes();
        int[][] genesMae = mae.getGenes();
        int[][] filho1 = new int[this.dimensao][this.dimensao];
        int[][] filho2 = new int[this.dimensao][this.dimensao];

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
        for (int i = 0; i < dimensao / 4; i++) {
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
        }

        //
        retorno.add(new Cromossomo(filho1));
        retorno.add(new Cromossomo(filho2));
        return retorno;
    }

    public void geraNovaPopulacao() {

        ArrayList<Cromossomo> geracao = new ArrayList<>();

        // Pega melhor cromossomo e passa direto
        Collections.sort(this.cromossomos);

        geracao.add(this.cromossomos.get(0));

        // Pega os dois melhores e faz crossover
        geracao.addAll(this.crossover(this.cromossomos.get(0), this.cromossomos.get(1)));


        // Pega outros dois aleatoriamente e faz o crossover para completar
        Random rand = new Random();
        int pai = rand.nextInt(tamanho - 1) + 1;
        int mae = rand.nextInt(tamanho - 1) + 1;
        while (mae == pai) {
            mae = rand.nextInt(tamanho - 1) + 1;
        }

        geracao.addAll(this.crossover(this.cromossomos.get(pai), this.cromossomos.get(mae)));

        // Define a populacao com a nova geracao
        this.cromossomos = geracao;
    }

    public boolean arrayPossuiElemento(int[][] array, int elemento) {
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

