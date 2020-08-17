package lrsystemsweb.com.br.savepetapp.api.pedidos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lrsystemsweb.com.br.savepetapp.core.io.StorageService;
import lrsystemsweb.com.br.savepetapp.domain.dto.PedidoDTO;
import lrsystemsweb.com.br.savepetapp.domain.dto.PontoDTO;
import lrsystemsweb.com.br.savepetapp.domain.pedido.model.Pedido;
import lrsystemsweb.com.br.savepetapp.domain.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
@Api(value = "Api REST Pedidos de SAVEPET")
public class PedidosController {

    @Autowired
    private PedidoService service;

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/listarPedidosProximo")
    @ApiOperation(value = "Animais Perdidos Proximo da Minha Localização", authorizations = { @Authorization(value="jwtToken") })
    public List<PedidoDTO> listarPedidosProximo(
            @RequestBody PontoDTO pontoDTO) {
        return service.listarPedidosProximo(pontoDTO.transformToObj(), pontoDTO.getDistancia());
    }

    @GetMapping(value = "/listarPaginado")
    @ApiOperation(value = "Listar Todos Pedidos Paginado", authorizations = { @Authorization(value="jwtToken") })
    public Page<PedidoDTO> listarPaginado(@PageableDefault(sort = "pk",direction = DESC, page = 0,size = 10) Pageable paginacao,
                                          @RequestParam(value = "pkUsuario", required = false, defaultValue = "") Long pkUsuario) {
        return service.listarPaginado(paginacao, pkUsuario);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Pega Pedidos por ID", authorizations = { @Authorization(value="jwtToken") })
    public PedidoDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta Pedidos por ID" , authorizations = { @Authorization(value="jwtToken") })
    public void delete(@PathVariable  Long id) {
        service.delete(id);
    }

    @PostMapping
    @ApiOperation(value = "Cria Pedidos" , authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<Object> create(@Valid @RequestBody Pedido pedido) {
        try{
            return ResponseEntity.ok(service.salvar(pedido));
        }
        catch (Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getFoto/{pk}")
    @ApiOperation(value = "Get Foto do Animal Perdido" , authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<Resource> getFile(@PathVariable Long pk){
        try{
            Resource file = storageService.loadAsResource(pk.toString());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        catch (Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
