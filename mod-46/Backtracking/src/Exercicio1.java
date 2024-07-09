import java.util.ArrayList;
import java.util.List;

public class Exercicio1 {

    public class SubsetsOfSizeN {

        public static List<List<Integer>> findSubsets(int[] S, int n) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(S, n, 0, new ArrayList<>(), result);
            return result;
        }

        private static void backtrack(int[] S, int n, int start, List<Integer> current, List<List<Integer>> result) {
            if (current.size() == n) {
                result.add(new ArrayList<>(current));
                return;
            }

            for (int i = start; i < S.length; i++) {
                current.add(S[i]);
                backtrack(S, n, i + 1, current, result);
                current.remove(current.size() - 1);  // backtrack
            }
        }
    }

    public static void main(String[] args) {
        int[] S = {1, 2, 3};
        int n = 2;
        List<List<Integer>> subsets = SubsetsOfSizeN.findSubsets(S, n);

        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

}
