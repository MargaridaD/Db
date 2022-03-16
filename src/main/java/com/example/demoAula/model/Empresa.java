package com.example.demoAula.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Empresa")
public class Empresa {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @OneToMany(mappedBy = "empresa")
    private List<Pessoa> listaPessoas;

    private String name;
    private String adress;
    private int numeroFuncionariosDesdeCriacao;

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}
	
    public int getNumeroFuncionariosAtual() {
    	if(listaPessoas == null) {
    		return 0;
    	}
        return listaPessoas.size();
    }
	
	public int getNumeroFuncionariosDesdeCriacao() {
		return numeroFuncionariosDesdeCriacao;
	}


	public void setNumeroFuncionariosDesdeCriacao(int numeroFuncionariosDesdeCriacao) {
		this.numeroFuncionariosDesdeCriacao = numeroFuncionariosDesdeCriacao;
	}


	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}


	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}


	@Override
	public String toString() {
		return "Empresa [id=" + id + ", name=" + name + ", adress=" + adress + "]";
	}
	
    public void addPessoa(Pessoa pessoa){
        numeroFuncionariosDesdeCriacao++;
        listaPessoas.add(pessoa);
    }

    public void removePessoa(Pessoa pessoa){
        listaPessoas.remove(pessoa);
    }

}
