package com.teste.sacola.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor //Gera um construtor com todos os argumentos
@Builder //Ajuda na declaração do sobjetos em service
@Data //Gera os getters e os setter usando lombok
@Embeddable //Reuso desse código em outra classe "Cliente"
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String complemento;
}
