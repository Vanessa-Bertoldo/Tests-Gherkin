package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ColetaModel {
    @Expose(serialize = false)
    private int id_coleta;

    @Expose
    private String dataColeta;

    @Expose
    private String dataRegistro;

    @Expose
    private String nome_bairro;

    @Expose
    private Double numero_volume;

    @Expose
    private Integer rota;
}
