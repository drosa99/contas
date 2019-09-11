package br.com.contas.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CONTA")
public class ContaEntity implements Serializable {
    private static final long serialVersionUID = 2606424908548969648L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "OID_CONTA")
    private String id;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "AGENCIA")
    private String agencia;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "DATA_CRIACAO")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataAtualizao;
}
