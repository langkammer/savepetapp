package lrsystemsweb.com.br.savepetapp.api.raca;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Post;
import lrsystemsweb.com.br.savepetapp.domain.animal.model.Raca;
import lrsystemsweb.com.br.savepetapp.domain.animal.service.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedidos")
public class RacaController {
    @Autowired
    private RacaService service;

    @GetMapping(value = "/list")
    public Page<Raca> list(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @PageableDefault(sort = "pk",direction = Sort.Direction.DESC, page = 0,size = 10) Pageable pagination) {
        return service.list(pagination, nome);
    }

    @Get("/{id}")
    public Optional get(@PathVariable Long id) {
        return service.get(id);
    }

    @Delete("/{id}")
    public void delete(@PathVariable  Long id) {
        service.delete(id);
    }

    @Post("/{id}")
    public Raca create(@RequestBody Raca animal) {
        return service.salvar(animal);
    }
}
