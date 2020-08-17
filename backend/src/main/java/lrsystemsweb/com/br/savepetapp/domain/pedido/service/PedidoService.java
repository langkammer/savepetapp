package lrsystemsweb.com.br.savepetapp.domain.pedido.service;

import com.vividsolutions.jts.geom.Geometry;
import lrsystemsweb.com.br.savepetapp.core.email.EmailPedidoAdocaoIncluido;
import lrsystemsweb.com.br.savepetapp.core.io.StorageService;
import lrsystemsweb.com.br.savepetapp.domain.dto.PedidoDTO;
import lrsystemsweb.com.br.savepetapp.domain.pedido.model.Pedido;
import lrsystemsweb.com.br.savepetapp.domain.pedido.repository.PedidoRepository;
import lrsystemsweb.com.br.savepetapp.domain.pedido.validacoes.ValidacaoPedidos;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.login.UsuarioService;
import org.hibernate.Hibernate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static lrsystemsweb.com.br.savepetapp.domain.comun.StatusPedido.APROVADO;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private EmailPedidoAdocaoIncluido emailPedidoAdocaoIncluido;
    @Autowired
    private UsuarioService usuarioService;

    private LocalDateTime dataHoraAtual = new LocalDateTime();

    @Transactional(rollbackFor={Exception.class})
    public Pedido salvar(Pedido pedido) {
        pedido.setUsuario(usuarioService.getUsuarioById(pedido.getUsuario().getPk()));
        new ValidacaoPedidos(pedido, usuarioService.getUsuarioLogado()).validar();
        if (pedido.getPk() != null) {
            pedido.setDataInclusao(dataHoraAtual);
        }
        pedido.setDataAlteracao(dataHoraAtual);
        pedido.setStatusPedido(APROVADO);
        this.repository.save(pedido);
        storageService.store(pedido.getAnimal().getFoto(), pedido.getPk());
        return pedido;
    }

    public void delete(Long pk) {
        this.repository.deleteById(pk);
    }

    public PedidoDTO get(Long pk) {
        Optional<Pedido> pedido = this.repository.findById(pk);
        if (pedido.isPresent()) {
            return new PedidoDTO(pedido.get());
        }
        return null;
    }


    public List<PedidoDTO> listarPedidosProximo(Geometry bounds, Double distancia) {
        return PedidoDTO.converter(this.repository.findByWithinPoints(bounds, distancia));
    }

    public Page<PedidoDTO> listarPaginado(Pageable paginacao, Long pkUsuario) {
        Page<Pedido> page = this.repository.findByUsuarioPk(pkUsuario, paginacao);
        Page<PedidoDTO> dtoPage = page.map(pedido -> new PedidoDTO(pedido));
        return dtoPage;
    }
}
