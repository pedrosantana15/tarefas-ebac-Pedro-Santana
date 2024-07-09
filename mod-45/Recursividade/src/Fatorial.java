public class Fatorial {

    private static final Integer MAX_ELEMENTS = 100;
    private static final Long[] elements = new Long[MAX_ELEMENTS];

    public static Long fatorialDP(Integer number) {
        if(number < 0){
            throw new IllegalArgumentException("O numero deve ser maior que zero");
        }
        if(number == 0 || number == 1){
            return 1L;
        }

        Long result = number * fatorialDP(number - 1);

        return result;
    }

    public static void main(String[] args) {
        Integer num = 5;
        System.out.println("Fatorial " + num + ":" + fatorialDP(num));
    }

}
