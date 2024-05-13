package br.com.pedro;

public abstract class CarroFactory {

    public static Carro getInstance(String marca){
        if(marca.equals("ford")){
            return new CarroFord();
        }
        else if(marca.equals("fiat")) {
            return new CarroFiat();
        }
        return null;
    }

}
