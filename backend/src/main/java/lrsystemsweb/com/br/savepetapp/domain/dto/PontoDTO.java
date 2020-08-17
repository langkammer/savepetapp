package lrsystemsweb.com.br.savepetapp.domain.dto;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PontoDTO {
    private Double x;
    private Double y;
    private int SRID;
    private Double distancia;
    public PontoDTO(Point point) {
        if (point != null){
            this.x = point.getX();
            this.y = point.getY();
        }
    }

    public Point transformToObj() {
        GeometryFactory gf=new GeometryFactory();
        return gf.createPoint(new Coordinate(this.x,this.y));
    }

}
