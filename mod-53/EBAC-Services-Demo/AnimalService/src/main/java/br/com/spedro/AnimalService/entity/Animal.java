package br.com.spedro.AnimalService.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "entry_date", nullable = false)
    private Date entryDate;

    @Column(name = "adoption_date")
    private Date adoptionDate;

    @Column(name = "entry_condition", nullable = false)
    private String entryCondition;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "death_date")
    private Date deathDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Date getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    public Date getAdoptionDate() {
        return adoptionDate;
    }
    public void setAdoptionDate(Date adoptionDate) {
        this.adoptionDate = adoptionDate;
    }
    public String getEntryCondition() {
        return entryCondition;
    }
    public void setEntryCondition(String entryCondition) {
        this.entryCondition = entryCondition;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public Date getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

}
