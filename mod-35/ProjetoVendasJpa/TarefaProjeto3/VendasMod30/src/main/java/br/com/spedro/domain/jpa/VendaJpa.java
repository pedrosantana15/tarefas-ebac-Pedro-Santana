package br.com.spedro.domain.jpa;

import br.com.spedro.dao.Persistente;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "TB_VENDA")
public class VendaJpa implements Persistente {

    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA;

        public static Status getByName(String value){
            for (Status status : Status.values()){
                if(status.name().equals(value)){
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_sq")
    @SequenceGenerator(name = "venda_sq", sequenceName = "sq_venda", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_cliente_fk",
            foreignKey = @ForeignKey(name = "fk_venda_cliente"),
            referencedColumnName = "id", nullable = false)
    private ClienteJpa cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private Set<ProdutoQuantidadeJpa> produtos;

    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "DATA_VENDA", nullable = false)
    private Instant dataVenda;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_VENDA", nullable = false)
    private Status status;

    public VendaJpa(){
        this.produtos = new HashSet<>();
    }

    public void adicionarProduto(ProdutoJpa produto, Integer quantidade){
        validarStatus();
        Optional<ProdutoQuantidadeJpa> op =
                produtos.stream()
                        .filter(p -> p.getProduto().getCodigo().equals(produto.getCodigo()))
                        .findAny();

        if(op.isPresent()){
            ProdutoQuantidadeJpa produtoQuantidade = op.get();
            produtoQuantidade.add(quantidade);
        } else {
            ProdutoQuantidadeJpa produtoQuantidade = new ProdutoQuantidadeJpa();
            produtoQuantidade.setProduto(produto);
            produtoQuantidade.add(quantidade);
            produtoQuantidade.setVenda(this);
            produtos.add(produtoQuantidade);
        }
        recalcularValorVenda();
    }

    private void recalcularValorVenda(){
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ProdutoQuantidadeJpa p : this.produtos){
            valorTotal = valorTotal.add(p.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }

    private void validarStatus(){
        if(this.status == Status.CONCLUIDA){
            throw new UnsupportedOperationException("NÃO É POSSÍVEL ALTERAR UMA VENDA FINALIZADA");
        }
    }

    public void removerProduto(ProdutoJpa produto, Integer quantidade){
        validarStatus();
        Optional<ProdutoQuantidadeJpa> op =
                produtos.stream()
                        .filter(p -> p.getProduto().getCodigo().equals(produto.getCodigo()))
                        .findAny();
        if(op.isPresent()){
            ProdutoQuantidadeJpa produtoQuantidade = op.get();
            if(produtoQuantidade.getQuantidade() > quantidade){
                produtoQuantidade.remover(quantidade);
                recalcularValorVenda();
            }
            else{
                produtos.remove(op.get());
                recalcularValorVenda();
            }
        }
    }

    public void removerTodosProdutos(){
        validarStatus();
        produtos.clear();
        valorTotal = BigDecimal.ZERO;
    }

    public Integer getQuantidadeTotalProdutos(){
        Integer result = produtos.stream()
                .reduce(0, (qtd, p) -> qtd + p.getQuantidade(), Integer::sum);
        return result;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public ClienteJpa getCliente() {
        return cliente;
    }
    public void setCliente(ClienteJpa cliente) {
        this.cliente = cliente;
    }
    public Set<ProdutoQuantidadeJpa> getProdutos() {
        return produtos;
    }
    public void setProdutos(Set<ProdutoQuantidadeJpa> produtos) {
        this.produtos = produtos;
    }
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Instant getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(Instant dataVenda) {
        this.dataVenda = dataVenda;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

}
