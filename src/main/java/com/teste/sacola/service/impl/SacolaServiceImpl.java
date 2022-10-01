package com.teste.sacola.service.impl;

import com.teste.sacola.Repository.ItemRepository;
import com.teste.sacola.Repository.ProdutoRepository;
import com.teste.sacola.Repository.SacolaRepository;
import com.teste.sacola.enumeration.FormaPagamento;
import com.teste.sacola.model.Item;
import com.teste.sacola.model.Restaurante;
import com.teste.sacola.model.Sacola;
import com.teste.sacola.resource.dto.ItemDto;
import com.teste.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());
        if (sacola.isFechada()) {
            throw new RuntimeException("Este produto não existe!");
        }
        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Esta sacola não existe!");
                        }
                ))
                .build(); //Criando um objeto utilizando o @Builder

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual =
                    itensDaSacola.get(0).getProduto().getRestaurante(); //get()Pegando o primeiro item da sacola
            Restaurante restauranteDoItemParaAdicionar =
                    itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Não é possivel adicionar " +
                        "produtos de restaurantes diferentes a mesma sacola." +
                        " Tente esvaziar a sacola para adicionar este item.");
            }
        }

        sacolaRepository.save(sacola); //Atualiza a sacola

        return itemRepository.save(itemParaSerInserido); //O item inserido
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Esta sacola não existe!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);

        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola!");
        }

        FormaPagamento formaPagamento =
                numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);

        return sacolaRepository.save(sacola);
    }
}
