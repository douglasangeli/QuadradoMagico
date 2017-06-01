import java.util.Scanner;

public class Main {

    public static final int LIMITE_POPULACIONAL = 5000000;
    public static final int LIMITE_POPULACOES_SEM_MELHORA = 100000;

    public static void main(String[] args) {

        System.out.print("Digite a dimensao do quadrado magico: ");

        Scanner scanner = new Scanner(System.in);
        int dimensao = scanner.nextInt();

        scanner.close();

        Populacao populacao = new Populacao(dimensao);

        int counter = 1;
        int melhorAptidao = 1000000;
        int geracoesSemMelhora = 0;

        Cromossomo melhorCromossomo;

        while (counter <= LIMITE_POPULACIONAL) {

            melhorCromossomo = populacao.getMelhorCromossomo();

            int aptidao = melhorCromossomo.getAptidao();

            if (aptidao < melhorAptidao) {
                melhorAptidao = aptidao;
                geracoesSemMelhora = 0;
            } else {
                geracoesSemMelhora++;
            }

            System.out.print("População " + counter);

            if (geracoesSemMelhora > LIMITE_POPULACOES_SEM_MELHORA) {
                System.out.println();
                System.out.println(LIMITE_POPULACOES_SEM_MELHORA + " Populacoes sem melhora. Cromossomo mais APTO:\n" +
                        melhorCromossomo.toString());
                break;
            } else if (aptidao == 0) {
                System.out.println();
                System.out.println(" Cromossomo ideal encontrado:\n" + melhorCromossomo.toString());
                break;
            } else {
                System.out.println(" INAPTA!");

                populacao.geraNovaPopulacao();
                counter++;
            }
        }
    }
}
