package main.br.com.spedro.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CARRO")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_sq")
    @SequenceGenerator(name = "carro_sq", sequenceName = "sq_caro", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "NOME", length = 30, nullable = false)
    private String nome;

    @Column(name = "ANO", nullable = false)
    private Long ano;

    @ManyToOne
    @JoinColumn(name = "id_marca_fk",
            foreignKey = @ForeignKey(name = "fk_carro_marca"),
            referencedColumnName = "id", nullable = false)
    private Marca marca;

    @ManyToMany
    @JoinTable(name = "TB_CARRO_ACESSORIO",
            joinColumns = { @JoinColumn(name = "id_carro_fk") },
            inverseJoinColumns = { @JoinColumn(name = "id_acessorio_fk") })
    private List<Acessorio> acessorios;

    public Carro(){
        acessorios = new ArrayList<Acessorio>();
    }

    public void addAcessorios(Acessorio acessorio){
        this.acessorios.add(acessorio);
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
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getAno() {
        return ano;
    }
    public void setAno(Long ano) {
        this.ano = ano;
    }
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public List<Acessorio> getAcessorios() {
        return acessorios;
    }
    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }

}
