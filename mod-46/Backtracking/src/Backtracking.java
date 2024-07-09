public class Backtracking {

    //Resolvendo algorítmo onde as rainhas de um tabuleiro
    //de xadrez não podem se atacar (vertical, horizontal e diagonal)

    public static Boolean nRainhas(int[][] tab, int n){
        //Checar se as rainhas foram colocadas
        if(n == 0){
            return true;
        }

        for(int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
                if(hasAttack(i, j, tab)){
                    continue;
                }

                //Colocando rainha no tabuleiro
                tab[i][j] = 1;

                if(nRainhas(tab, n - 1)){
                    return true;
                }

                //Retirando a rainha do tabuleiro
                tab[i][j] = 0;
            }
        }

        return false;
    }

    public static Boolean hasAttack(int x, int y, int[][] tab){

        //Verificando se há ataque nas colunas
        for(int j = 0; j < tab.length; j++){
            if(tab[x][j] == 1){
                return true;
            }
        }

        //Verificando se há ataque nas linhas
        for(int i = 0; i < tab.length; i++){
            if(tab[i][y] == 1){
                return true;
            }
        }

        //Verificando se há ataque nas diagonais
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab.length; j++){

                int leftIndex = i + j;
                int rightIndex = i - j;

                if(leftIndex == (x + y)){
                    if(tab[i][j] == 1){
                        return true;
                    }
                }

                if(rightIndex == (x - y)){
                    if(tab[i][j] == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void print(int[][] tab){
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab.length; j++){
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] tab = {
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };

        int n = 4;

        Boolean result = nRainhas(tab, n);

        if(result){
            print(tab);
        } else {
            System.out.println("Não há solução");
        }

    }
}