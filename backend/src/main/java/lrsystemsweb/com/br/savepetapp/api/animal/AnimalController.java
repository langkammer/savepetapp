package lrsystemsweb.com.br.savepetapp.api.animal;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Post;
import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import lrsystemsweb.com.br.savepetapp.domain.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping(value = "/list")
    public Page<Animal> list(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @PageableDefault(sort = "pk",direction = Sort.Direction.DESC, page = 0,size = 10) Pageable pagination) {
        return animalService.list(pagination, nome);
    }

    @Get("/{id}")
    public Optional get(@PathVariable Long id) {
        return animalService.get(id);
    }

    @Delete("/{id}")
    public void delete(@PathVariable  Long id) {
        animalService.delete(id);
    }

    @Post("/salvar}")
    public Animal create(@RequestBody Animal animal) {
        return animalService.salvar(animal);
    }
}
