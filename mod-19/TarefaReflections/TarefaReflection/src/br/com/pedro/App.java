package br.com.pedro;


import br.com.pedro.annotations.Tabela;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class App {

    public static void main(String[] args) {
        getNomeTabela();
    }

    public static void getNomeTabela(){
        Carros carros = new Carros();
        Class c = carros.getClass();

        Method[] methods = c.getDeclaredMethods();

        for(Method method : methods){
            if(method.isAnnotationPresent(Tabela.class)){
                Tabela ann = method.getAnnotation(Tabela.class);
                String tableName = ann.value();

                System.out.println(tableName);
            }
        }
    }

}
