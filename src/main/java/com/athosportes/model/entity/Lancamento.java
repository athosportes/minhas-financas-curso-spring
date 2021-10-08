package com.athosportes.model.entity;

import com.athosportes.model.enums.StatusLancamento;
import com.athosportes.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "financas")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer mes;

    @Column
    private Integer ano;

    @Column
    private BigDecimal valor;

    @Column
    private String descricao;

    @Column(name = "data_cadastro")
    //@Convert(converter = Jsr310Converters.LocalDateToDateConverter.class)
    private LocalDate dataCadastro;

    @Column(name = "status_lancamento")
    @Enumerated(EnumType.STRING)
    private StatusLancamento statusLancamento;

    @Column(name = "tipo_lancamento")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
