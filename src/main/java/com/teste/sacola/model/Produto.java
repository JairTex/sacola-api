package com.teste.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor //Gera um construtor com todos os argumentos
@Builder //Ajuda na declaração do sobjetos em service
@Data //Gera os getters e os setter usando lombok
@Entity //Indicar para criação da tabela no bd
@JsonIgnoreProperties({"hibernateLazyInitializer, handler"}) //ajuda quando acontecer erros com JSON no hibernate
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private double valorUnitario;
    @Builder.Default //Na hora de criar true vai ser depois
    private Boolean disponivel = true;
    @ManyToOne //Um restalrante tem vários produtos
    @JsonIgnore
    private Restaurante restaurante;
}
