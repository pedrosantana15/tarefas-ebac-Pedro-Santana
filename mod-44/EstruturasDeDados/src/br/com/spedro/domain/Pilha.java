package br.com.spedro.domain;

import java.util.EmptyStackException;

public class Pilha<T> {

    public static class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> top;
    private Integer size;

    public Pilha(){
        top = null;
        size = 0;
    }

    public void push(T data){
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }

        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public Boolean isEmpty(){
        return top == null;
    }

    public T top(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.data;
    }

    public Integer size(){
        return size;
    }

}
