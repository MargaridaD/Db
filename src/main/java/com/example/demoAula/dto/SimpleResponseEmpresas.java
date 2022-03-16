package com.example.demoAula.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demoAula.model.Empresa;

public class SimpleResponseEmpresas extends SimpleResponse {
	List<Empresa> empresas;
	
    public SimpleResponseEmpresas() {
        this.empresas = new ArrayList<>();
    }

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
    
    


}
