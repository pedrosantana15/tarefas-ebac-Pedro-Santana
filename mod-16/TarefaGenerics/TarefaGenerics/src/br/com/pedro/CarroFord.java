package br.com.pedro;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarroFord extends Carro<String>{

    public CarroFord(String modelo) {
        super("Ford", modelo);
    }
}
