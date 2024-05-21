package br.com.pedro.service;

import br.com.pedro.dao.IContratoDAO;

public class ContratoService implements IContratoService {

    private IContratoDAO contratoDAO;

    public ContratoService(IContratoDAO contratoDAO) {
        this.contratoDAO = contratoDAO;
    }

    @Override
    public String salvar() {
        contratoDAO.salvar();
        return "Sucesso";
    }

    @Override
    public String atualizar() {
        contratoDAO.atualizar();
        return "Contrato atualizado com sucesso";
    }

    @Override
    public String buscar() {
        contratoDAO.buscar();
        return "Contrato encontrado";
    }

    @Override
    public String excluir() {
        contratoDAO.excluir();
        return "Contrato excluido";
    }
}
