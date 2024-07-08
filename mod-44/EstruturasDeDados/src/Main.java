import br.com.spedro.domain.Fila;
import br.com.spedro.domain.ListaEncadeada;
import br.com.spedro.domain.Pilha;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Pilha
        Pilha<Integer> numbers = new Pilha<>();
        numbers.push(1);
        numbers.push(2);
        numbers.push(3);

        System.out.println(numbers.size());
        System.out.println(numbers.top());
        System.out.println(numbers.pop());

        //Complexidade assintótica de tempo:
        //push: O(1)
        //pop: O(1)

        //Fila
        Fila<Integer> fila = new Fila<>();
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        fila.enqueue(4);

        System.out.println(fila.dequeue());
        System.out.println(fila.front());
        System.out.println(fila.rear());

        //Complexidade assintótica de tempo:
        //enqueue: O(1)
        //dequeue: O(1)
        //rear: O(1)
        //front: O(1)

        //Lista Encadeada
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.push(1);
        lista.push(2);
        lista.push(3);

        System.out.println(lista.pop());
        System.out.println(lista.elementAt(0));
        System.out.println(lista.size());

        //Complexidade assintótica de tempo:
        //push: O(1)
        //pop: O(1)
        //insert: O(n)
        //remove: O(n)
        //elementAt: O(n)


    }
}