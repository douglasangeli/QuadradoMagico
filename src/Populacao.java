import java.util.*;

public class Populacao {

    private int tamanho = 5;
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
        for (int i = 0; i < this.dimensao / 2; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                filho1[i][j] = genesPai[i][j];
                filho2[i][j] = genesMae[i][j];
            }
        }

        for (int i = this.dimensao / 2; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                filho1[i][j] = genesPai[i][j];
                filho2[i][j] = genesMae[i][j];
            }
        }



        // Faz mutacao trocando dois pontos randomicos
//        Random rand = new Random();
//        int pontoMutacao1 = rand.nextInt(genesPai.length());
//        int pontoMutacao2 = rand.nextInt(genesPai.length());
//
//        StringBuilder f1 = new StringBuilder(filho1);
//        f1.setCharAt(pontoMutacao1, filho1.charAt(pontoMutacao2));
//        f1.setCharAt(pontoMutacao2, filho1.charAt(pontoMutacao1));
//        filho1 = f1.toString();
//
//        StringBuilder f2 = new StringBuilder(filho2);
//        f2.setCharAt(pontoMutacao1, filho2.charAt(pontoMutacao2));
//        f2.setCharAt(pontoMutacao2, filho2.charAt(pontoMutacao1));
//        filho2 = f2.toString();
//        //
//        retorno.add(new Cromossomo(filho1));
//        retorno.add(new Cromossomo(filho2));
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
}

