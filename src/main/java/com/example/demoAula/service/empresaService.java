package com.example.demoAula.service;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoAula.model.Empresa;
import com.example.demoAula.model.Pessoa;
import com.example.demoAula.repository.empresaRepository;
import com.example.demoAula.repository.pessoaRepository;

@Service
public class empresaService {
    private final pessoaRepository pessoaRepo;
	private final empresaRepository empresaRepo;
	
    @Autowired
    public empresaService(empresaRepository empresaRepo, pessoaRepository pessoaRepo) {
        this.pessoaRepo = pessoaRepo;
		this.empresaRepo = empresaRepo;
    }
    
    public boolean addEmpresa(Empresa empresa){
        if (empresa.getId() == null){
            empresaRepo.save(empresa);
            return true;
        }
        return false;
    }

    public boolean removeEmpresa(Empresa empresa){
        if (empresa.getId() == null || empresaRepo.findById(empresa.getId()).isEmpty()){
            return false;
        }

        Empresa i = empresaRepo.findById(empresa.getId()).get();
        for (Pessoa aux: i.getListaPessoas()){
            pessoaRepo.delete(aux);
        }
        empresaRepo.delete(i);

        return true;
    }

    public boolean removeEmpresa2(String id){
        try {
            Long id_long = parseLong(id);

            if (id == null || id_long == NaN || empresaRepo.findById(id_long).isEmpty()){
                return false;
            }

            Empresa i = empresaRepo.findById(id_long).get();
            for (Pessoa aux: i.getListaPessoas()){
                pessoaRepo.delete(aux);
            }

            empresaRepo.delete(i);
            return true;
            
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateEmpresa(Empresa empresa){
        if (empresa.getId() == null || empresaRepo.findById(empresa.getId()).isEmpty()){
            return false;
        }

        Empresa i = empresaRepo.findById(empresa.getId()).get();

        if (empresa.getName() != null || !empresa.getName().isBlank()){
            i.setName(empresa.getName());
        }

        if (empresa.getAdress() != null || !empresa.getAdress().isBlank()){
            i.setAdress(empresa.getAdress());
        }

        empresaRepo.save(i);

        return true;
    }

    public List<Empresa> getEmpresas(){
        List<Empresa> listaEmpresas = new ArrayList<>();

        empresaRepo.findAll().forEach(listaEmpresas::add);

        return listaEmpresas;
    }

    public Optional<Empresa> getEmpresa(Long id)
    {
        return empresaRepo.findById(id);
    }
    

}
