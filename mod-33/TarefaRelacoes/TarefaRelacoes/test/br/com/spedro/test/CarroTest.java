package br.com.spedro.test;

import main.br.com.spedro.dao.*;
import main.br.com.spedro.domain.Acessorio;
import main.br.com.spedro.domain.Carro;
import main.br.com.spedro.domain.Marca;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CarroTest {

    private IMarcaDao marcaDao;
    private IAcessorioDao acessorioDao;
    private ICarroDao carroDao;
    private Carro carro;

    public CarroTest(){
        marcaDao = new MarcaDao();
        acessorioDao = new AcessorioDao();
        carroDao = new CarroDao();
        carro = new Carro();
    }

    @Test
    public void cadastrar(){
        Assert.assertNotNull(carro);
        Assert.assertNotNull(carro.getId());
    }

    @Test
    public void buscarPorId(){
        Carro carroDb = carroDao.buscarPorId(carro.getId());
        Assert.assertNotNull(carroDb);
        Assert.assertEquals(carroDb.getId(), carro.getId());
    }

    @Test
    public void buscarTodos(){
        Marca marca1 = criarMarca("B4");
        Acessorio acessorio1 = criarAcessorio("B4");
        Carro carro1 = new Carro();
        carro1.setCodigo("C8");
        carro1.setNome("Tracker");
        carro1.setAno(2024L);
        carro1.setMarca(marca1);
        marca1.addCarros(carro1);
        carro1.addAcessorios(acessorio1);
        carroDao.cadastrar(carro1);

        List<Carro> carros = carroDao.buscarTodos();
        Assert.assertNotNull(carros);
        Assert.assertEquals(2, carros.size());
    }

    @Test
    public void excluir(){
        carroDao.excluir(carro);
        Carro carroDb = carroDao.buscarPorId(carro.getId());
        Assert.assertNull(carroDb);
    }

    @Test
    public void alterar(){
        Marca marca = criarMarca("A2");
        Acessorio acessorio = criarAcessorio("A2");
        carro.setCodigo("B1");
        carro.setNome("Uno");
        carro.setAno(2021L);
        carro.setMarca(marca);
        marca.addCarros(carro);
        carro.addAcessorios(acessorio);
        Carro carroAlterado = carroDao.alterar(carro);
        Assert.assertNotNull(carroAlterado);
        Assert.assertEquals(carroAlterado.getId(), carro.getId());
    }

    @Test
    public void buscarPorMarca(){
        Marca marca = new Marca();
        marca.setCodigo("J1");
        marca.setNome("Ford");
        marcaDao.cadastrar(marca);

        Acessorio acessorio = criarAcessorio("J1");
        Carro carro = new Carro();
        carro.setCodigo("J1");
        carro.setNome("Uno");
        carro.setAno(2020L);
        carro.setMarca(marca);
        marca.addCarros(carro);
        carro.addAcessorios(acessorio);
        carroDao.cadastrar(carro);

        Carro carro1 = new Carro();
        carro1.setCodigo("S1");
        carro1.setNome("Civic");
        carro1.setAno(2020L);
        carro1.setMarca(marca);
        marca.addCarros(carro1);
        carro1.addAcessorios(acessorio);
        carroDao.cadastrar(carro1);

        List<Carro> carros = carroDao.buscarPorMarca(carro.getMarca());
        Assert.assertNotNull(carros);
        Assert.assertEquals(carro.getMarca(), carro1.getMarca());
        Assert.assertEquals(2, carros.size());
        Assert.assertNotEquals(carro.getMarca(), this.carro.getMarca());
    }

    @Before
    public void criarCarro(){
        Marca marca = criarMarca("A1");
        Acessorio acessorio = criarAcessorio("A1");
        Carro carro = new Carro();
        carro.setCodigo("A1");
        carro.setNome("Fiesta");
        carro.setAno(2020L);
        carro.setMarca(marca);
        marca.addCarros(carro);
        carro.addAcessorios(acessorio);
        this.carro = carroDao.cadastrar(carro);
    }

    @After
    public void end(){
        List<Carro> carros = carroDao.buscarTodos();
        carros.forEach(c -> carroDao.excluir(c));

        List<Marca> marcas = marcaDao.buscarTodos();
        marcas.forEach(m -> marcaDao.excluir(m));

        List<Acessorio> acessorios = acessorioDao.buscarTodos();
        acessorios.forEach(a -> acessorioDao.excluir(a));
    }

    private Marca criarMarca(String codigo){
        Marca marca = new Marca();
        marca.setCodigo(codigo);
        marca.setNome("Ford");
        return marcaDao.cadastrar(marca);
    }

    private Acessorio criarAcessorio(String codigo){
        Acessorio acessorio = new Acessorio();
        acessorio.setCodigo(codigo);
        acessorio.setNome("Ar condicionado");
        acessorio = acessorioDao.cadastrar(acessorio);
        return acessorio;
    }

}
