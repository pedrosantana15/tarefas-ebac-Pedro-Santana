package br.com.pedro;

public class Main {
    public static void main(String args[]){
        PessoaFisica pf = criarPessoaFisica("Pedro Henrique", "Vila Madalena, SP", "CPF", 625465861l, "Brasileiro", "Solteiro");
        PessoaJuridica pj = criarPessoaJuridica("Mercado TemDeTudo", "Vila Guilherme, SP", "CNPJ", "Joao Carlos", "Comércio");

        System.out.println("<---- PF ---->");
        System.out.println("Nome: " + pf.getNome());
        System.out.println("Endereço: " + pf.getEndereco());
        System.out.println("Tipo de Identificação Fiscal: " + pf.getIdFiscal());

        System.out.println("<---- PJ ---->");
        System.out.println("Nome: " + pj.getNome());
        System.out.println("Endereço: " + pj.getEndereco());
        System.out.println("Tipo de Identificação Fiscal: " + pj.getIdFiscal());

    }

    public static PessoaFisica criarPessoaFisica(String nome, String endereco, String idFiscal, Long rg, String nacionalidade, String estadoCivil){
        PessoaFisica pf = new PessoaFisica();
        pf.setNome(nome);
        pf.setEndereco(endereco);
        pf.setIdFiscal(idFiscal);
        pf.setRg(rg);
        pf.setNacionalidade(nacionalidade);
        pf.setEstadoCivil(estadoCivil);

        return pf;
    }

    public static PessoaJuridica criarPessoaJuridica(String nome, String endereco, String idFiscal, String rep, String tipo){
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome(nome);
        pj.setEndereco(endereco);
        pj.setIdFiscal(idFiscal);
        pj.setRepresentantes(rep);
        pj.setTipo(tipo);

        return pj;
    }
}
