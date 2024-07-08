package br.com.spedro.domain;

public class Fila<T> {

    public static class Node<T>{
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private Integer size;

    public Fila() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if(last != null){
            last.next = newNode;
        }

        last = newNode;

        if(first == null){
            first = last;
        }
        size++;
    }

    public T dequeue() {
        T data = first.data;
        first = first.next;

        if(first == null){
            last = null;
        }

        size--;
        return data;
    }

    public Boolean isEmpty() {
        return first == null;
    }

    public T front(){
        return first.data;
    }

    public Integer size() {
        return size;
    }

    public T rear(){
        return last.data;
    }

}
