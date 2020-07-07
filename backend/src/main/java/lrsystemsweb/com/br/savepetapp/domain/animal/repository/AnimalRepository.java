package lrsystemsweb.com.br.savepetapp.domain.animal.repository;

import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface AnimalRepository<T, ID extends Serializable>  extends CrudRepository<Animal, Long> {
}
