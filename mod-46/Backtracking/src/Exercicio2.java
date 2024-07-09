import java.util.ArrayList;
import java.util.List;

public class Exercicio2 {

        // Conjunto de moedas disponíveis
        private static final int[] MOEDAS = {50, 25, 10, 5, 1};
        private static List<Integer> melhorTroco = null;

        // Função para encontrar o troco usando backtracking
        private static void encontrarTroco(int quantia, List<Integer> trocoAtual) {
            if (quantia == 0) {
                if (melhorTroco == null || trocoAtual.size() < melhorTroco.size()) {
                    melhorTroco = new ArrayList<>(trocoAtual);
                }
                return;
            }

            for (int moeda : MOEDAS) {
                if (quantia >= moeda) {
                    trocoAtual.add(moeda);
                    encontrarTroco(quantia - moeda, trocoAtual);
                    trocoAtual.remove(trocoAtual.size() - 1);
                }
            }
        }

    public static void main(String[] args) {
        int quantia = 63; // Quantia para dar troco em centavos
        List<Integer> troco = new ArrayList<>();
        encontrarTroco(quantia, troco);

        System.out.println("Melhor troco: " + melhorTroco);
    }

}
