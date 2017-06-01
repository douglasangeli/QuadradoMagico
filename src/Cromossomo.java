import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cromossomo implements Comparable<Cromossomo> {

    private int[][] genes;
    private int dimensao;
    private int numeroMagico;

    public Cromossomo(int dimensao) {
        this.dimensao = dimensao;
        this.genes = getQuadradoAleatorio(dimensao);

        calcularNumeroMagico();
    }

    private int[][] getQuadradoAleatorio(int dimensao) {
        int[][] genes = new int[dimensao][dimensao];
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= (dimensao * dimensao); i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        int count = 0;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                genes[i][j] = list.get(count++);
            }
        }
        return genes;
    }

    private void calcularNumeroMagico() {
        this.numeroMagico = ((int) Math.pow(dimensao, 3) + dimensao) / 2;
    }

    public Cromossomo(int[][] genes) {
        this.genes = genes;
        this.dimensao = genes.length;
        calcularNumeroMagico();
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
