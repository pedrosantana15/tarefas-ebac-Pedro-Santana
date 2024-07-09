//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Fibonacci {

    private static final Integer MAX_ELEMENTS = 100;
    private static final Long[] fibElements = new Long[MAX_ELEMENTS];

    public static Long findElementDP(Integer element){
        for(int i = 0; i < MAX_ELEMENTS; i++){
            fibElements[i] = -1L;
        }

        return findElement(element);
    }

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