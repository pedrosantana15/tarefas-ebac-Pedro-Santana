package br.com.spedro;

/**
 * Classe que realiza o cálculo da constante de Fibonacci utilizando
 * Programação Dinâmica e Recursividade
 */
public class Fibonacci {

    private static final Integer MAX_ELEMENTS = 100;
    private static final Long[] fibElements = new Long[MAX_ELEMENTS];

    /**
     * Método que inicia o array fibElements com -1
     * @param element
     * @return retorna o método findElement que retorna o resultado da operação
     */
    public static Long findElementDP(Integer element){
        for(int i = 0; i < MAX_ELEMENTS; i++){
            fibElements[i] = -1L;
        }

        return findElement(element);
    }

    /**
     * Método que utiliza da Recursividade para realizar o cálculo de Fibonacci
     * Ex: 1 1 2 3 5 8 13
     * @param element
     * @return retorna o resultado da operação
     */
    public static Long findElement(Integer element){
       if(fibElements[element] == -1){
           if(element <= 1){
               fibElements[element] = Long.valueOf(element);
           } else {
               fibElements[element] = findElement(element - 1) + findElement(element - 2);
           }
       }
        return fibElements[element];
    }

    public static void main(String[] args) {
        Integer number = 50;

        System.out.println("Element " + number + ": " + findElementDP(number));
    }
}