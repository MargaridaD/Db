package com.example.demoAula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoAula.dto.SimpleResponse;
import com.example.demoAula.dto.SimpleResponseEmpresas;
import com.example.demoAula.model.Empresa;
import com.example.demoAula.service.empresaService;



@RestController
public class empresaController {
	
	private final empresaService empresa_service;
	
	@Autowired
	public empresaController(empresaService empresa_service) {
		this.empresa_service = empresa_service;
	}
	
    @GetMapping("/getEmpresas")
    public List<Empresa> getEmpresas(){
        return empresa_service.getEmpresas();
    }

    @PostMapping(path = "/addEmpresa")													
    public ResponseEntity<SimpleResponse> addEmpresas(@RequestBody Empresa e){
        SimpleResponseEmpresas sr = new SimpleResponseEmpresas();

        if (e.getName() == null || e.getName().isBlank()){
            sr.setMessage("Nome Invalido");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(sr);
        }
        
        if (e.getAdress() == null || e.getAdress().isBlank()){
            sr.setMessage("Morada Invalida");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(sr);
        }
        
        
        if (empresa_service.addEmpresa(e)){
            sr.setAsSuccess("Empresa Inserida Com Sucesso");
            sr.setEmpresas(empresa_service.getEmpresas());

        }else{
            sr.setAsError("Erro ao inserir a Empresa");
        }


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sr);
    }


    @DeleteMapping("/removeEmpresa/{id}")
    public SimpleResponse removePessoa2(@PathVariable String id){
        SimpleResponse sr = new SimpleResponse();

        if (empresa_service.removeEmpresa2(id)){
            sr.setAsSuccess("Empresa Removida Com Sucesso");
        }
        else{
            sr.setAsError("Erro a Remover a Empresa");
        }

        return sr;
    }

    @DeleteMapping("/removeEmpresa")
    public SimpleResponse removeEmpresa(@RequestBody Empresa e){
        SimpleResponseEmpresas sr = new SimpleResponseEmpresas();
        sr.setAsSuccess("Sucesso");

        if (empresa_service.removeEmpresa(e)){
            sr.setAsSuccess("Empresa Removida Com Sucesso");
        }
        else{
            sr.setAsError("Erro a Remover a Empresa");
        }

        return sr;
    }

    @PutMapping("/updateEmpresa")
    public SimpleResponse updateEmpresa(@RequestBody Empresa e){
        SimpleResponse sr = new SimpleResponse();

        if (e.getName() == null || e.getName().isBlank()){
            sr.setAsError("Nome Invalido");
            return sr;
        }
        
        if (e.getAdress() == null || e.getAdress().isBlank()){
            sr.setAsError("Morada Invalida");
            return sr;
        }

        boolean suc = empresa_service.updateEmpresa(e);

        if (suc){
            sr.setAsSuccess("Empresa atualizada com sucesso");
        }
        else{
            sr.setAsError("Erro na atualização da empresa "+ e.getId());
        }
        return sr;

    }


}
