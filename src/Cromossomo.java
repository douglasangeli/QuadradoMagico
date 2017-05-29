import java.util.StringJoiner;

public class Cromossomo implements Comparable<Cromossomo> {

    private int[][] genes;
    private int dimensao;
    private int numeroMagico;

    public Cromossomo(int dimensao) {
        this.dimensao = dimensao;
        this.genes = new int[dimensao][dimensao];

        int count = 0;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                this.genes[i][j] = ++count;
            }
        }

        //perfeito
//        genes = new int[][]{
//                {7, 12, 6, 9},
//                {2, 5, 11, 16},
//                {15, 4, 14, 1},
//                {10, 13, 3, 8}};

        //int maior = dimensao * dimensao;
        //this.numeroMagico = (maior * (maior + 1) / 2) / dimensao;

        this.numeroMagico = ((int) Math.pow(dimensao, 3) + dimensao) / 2;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < dimensao; i++) {
            builder.append("| ");
            for (int j = 0; j < dimensao; j++) {
                builder.append(String.format("%5d\t", genes[i][j]));
            }
            builder.append("| " + getSomaLinha(i) + "\n");
        }

        builder.append("  ");

        for (int i = 0; i < dimensao; i++) {
            builder.append(String.format("%5d\t", getSomaColuna(i)));
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Cromossomo o) {
        if (this.getAptidao() > o.getAptidao()) {
            return 1;
        } else if (this.getAptidao() < o.getAptidao()) {
            return -1;
        }
        return 0;
    }

    public int getAptidao() {
        int aptidao = 0;

        for (int i = 0; i < dimensao; i++) {
            aptidao += Math.abs(getSomaLinha(i) - this.numeroMagico);
            aptidao += Math.abs(getSomaColuna(i) - this.numeroMagico);
        }

        return aptidao;
    }

    private int getSomaColuna(final int j) {
        int somaColuna = 0;
        for (int i = 0; i < dimensao; i++) {
            somaColuna += this.genes[i][j];
        }
        return somaColuna;
    }

    private int getSomaLinha(final int i) {
        int somaLinha = 0;
        for (int j = 0; j < dimensao; j++) {
            somaLinha += this.genes[i][j];
        }
        return somaLinha;
    }

    public int[][] getGenes() {
        return genes;
    }
}
