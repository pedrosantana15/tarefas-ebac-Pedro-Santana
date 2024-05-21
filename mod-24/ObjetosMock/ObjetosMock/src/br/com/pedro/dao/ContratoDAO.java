package br.com.pedro.dao;

import java.net.UnknownServiceException;

public class ContratoDAO implements IContratoDAO{

    public void salvar(){
        throw new UnsupportedOperationException("Não é possível salvar no banco");
    }

    public void atualizar(){
        throw new UnsupportedOperationException("Erro ao atualizar");
    }

    public void buscar(){
        throw new UnsupportedOperationException("Erro ao buscar");
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Erro ao excluir");
    }

}
