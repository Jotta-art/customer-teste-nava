package com.example.api.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoKey;

    @Column(nullable = false)
    @NotEmpty
    private String rua;

    @Column(nullable = false)
    @NotNull
    private Integer numero;

    @Column(nullable = false)
    @NotEmpty
    private String bairro;

    @Column(nullable = false)
    @NotEmpty
    private String cep;

    @Column(nullable = false)
    @NotEmpty
    private String cidade;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Long getEnderecoKey() {
        return enderecoKey;
    }

    public void setEnderecoKey(Long enderecoKey) {
        this.enderecoKey = enderecoKey;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
