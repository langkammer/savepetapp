package lrsystemsweb.com.br.savepetapp.domain.animal.service;

import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import lrsystemsweb.com.br.savepetapp.domain.animal.model.Raca;
import lrsystemsweb.com.br.savepetapp.domain.animal.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RacaService {
    @Autowired
    private RacaRepository repository;

    public Raca salvar(Raca raca) {
        return (Raca) this.repository.save(raca);
    }

    public void delete(Long pk) {
        this.repository.deleteById(pk);
    }

    public Optional get(Long pk) {
        return this.repository.findById(pk);
    }

    public Page<Raca> list(Pageable pagination, String nome) {
        return null;
    }
}
