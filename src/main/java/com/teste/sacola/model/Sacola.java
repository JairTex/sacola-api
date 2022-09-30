package com.teste.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.sacola.enumeration.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
//Anotações do lombok e do hibernate
@AllArgsConstructor //Gera um construtor com todos os argumentos
@Builder //Ajuda na declaração do sobjetos em service
@Data //Gera os getters e os setter usando lombok
@Entity //Indicar para criação da tabela no bd
@JsonIgnoreProperties({"hibernateLazyInitializer, handler"}) //ajuda quando acontecer erros com JSON no hibernate
@NoArgsConstructor //Construtor sem argumentos exigido pelo hibernate e feito com lombok
public class Sacola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
                //fetch é a apresentação, estou pedindo para não mostrar na tabela
    @ManyToOne(fetch = FetchType.LAZY, optional = false) //Um cliente pode ter várias sacolas
    @JsonIgnore //Ignorar exceptions serializaveis
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;

    @Enumerated
    private FormaPagamento FormaPagamento;
    private boolean fechada;
}
