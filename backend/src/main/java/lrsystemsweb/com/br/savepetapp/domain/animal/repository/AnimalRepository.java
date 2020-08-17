package lrsystemsweb.com.br.savepetapp.domain.animal.repository;

import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface AnimalRepository<T, ID extends Serializable>  extends CrudRepository<Animal, Long> {
}
