package com.teste.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor //Gera um construtor com todos os argumentos
@Builder //Ajuda na declaração do sobjetos em service
@Data //Gera os getters e os setter usando lombok
@Entity //Indicar para criação da tabela no bd
@JsonIgnoreProperties({"hibernateLazyInitializer, handler"}) //ajuda quando acontecer erros com JSON no hibernate
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL) //Sempre que o argumento for outro model
    private List<Produto> cardapio; //
    @Embedded //Juntando a classe endereco nessa
    private Endereco endereco;
}
