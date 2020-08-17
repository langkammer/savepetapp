package lrsystemsweb.com.br.savepetapp.domain.animal.service;


import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import lrsystemsweb.com.br.savepetapp.domain.animal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public Animal salvar(Animal animal) {
        return (Animal) this.repository.save(animal);
    }

    public void delete(Long pk) {
        this.repository.deleteById(pk);
    }

    public Optional get(Long pk) {
       return this.repository.findById(pk);
    }

    public Page<Animal> list(Pageable pagination, String nome) {
        return null;
    }
}
