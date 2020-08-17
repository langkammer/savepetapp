package lrsystemsweb.com.br.savepetapp.domain.pedido.repository;

import com.vividsolutions.jts.geom.Geometry;
import lrsystemsweb.com.br.savepetapp.domain.comun.StatusPedido;
import lrsystemsweb.com.br.savepetapp.domain.comun.TipoPedido;
import lrsystemsweb.com.br.savepetapp.domain.dto.PedidoDTO;
import lrsystemsweb.com.br.savepetapp.domain.pedido.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface PedidoRepository<T, ID extends Serializable>  extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT p FROM Pedido p WHERE ST_Distance(p.ponto, :localizacao) <= :raio")
    List<Pedido> findByWithinPoints(@Param("localizacao") Geometry localizacao, @Param("raio") Double raio);

    Page<Pedido> findByUsuarioPk(@Param("pk") Long pk, Pageable pageable);

}
