package lrsystemsweb.com.br.savepetapp.domain.animal.service;


import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import lrsystemsweb.com.br.savepetapp.domain.animal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public Animal salvar(Animal animal) {
        return (Animal) this.repository.save(animal);
    }
}
