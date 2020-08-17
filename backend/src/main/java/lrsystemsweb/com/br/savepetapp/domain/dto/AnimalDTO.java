package lrsystemsweb.com.br.savepetapp.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.core.util.DataUtil;
import lrsystemsweb.com.br.savepetapp.domain.animal.enums.StatusAnimal;
import lrsystemsweb.com.br.savepetapp.domain.animal.enums.TipoAnimal;
import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AnimalDTO {

    private Long pk;
    private String nome;
    private RacaDTO raca;
    private LocalDate dataVizualizacao;
    private StatusAnimal statusAnimal;
    private TipoAnimal tipoAnimal;
    private String foto;
    private String nomeFoto;
    private String dataInclusao;
    private String dataAlteracao;

    public AnimalDTO(Animal animal) {
        this.pk = animal.getPk();
        this.nome = animal.getNome();
        this.raca = new RacaDTO(animal.getRaca());
        this.dataVizualizacao = animal.getDataVizualizacao();
        this.statusAnimal = animal.getStatusAnimal();
        this.tipoAnimal = animal.getTipoAnimal();
        this.foto = animal.getFoto();
        this.nomeFoto = animal.getNomeFoto();
        this.dataInclusao = DataUtil.toString(animal.getDataInclusao());
        this.dataAlteracao = DataUtil.toString(animal.getDataAlteracao());
    }

    public Animal transformToObj() {
        Animal animal = new Animal();
        animal.setFoto(foto);
        animal.setNomeFoto(nomeFoto);
        animal.setDataVizualizacao(dataVizualizacao);
        animal.setNome(nome);
        animal.setRaca(raca.transformToObj());
        return animal;
    }

    public static List<AnimalDTO> converter(List<Animal> list) {
        return list.stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

}
