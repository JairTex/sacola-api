package com.teste.sacola.service;

import com.teste.sacola.model.Item;
import com.teste.sacola.model.Sacola;
import com.teste.sacola.resource.dto.ItemDto;

public interface SacolaService {
    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);
}
