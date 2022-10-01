package com.teste.sacola.resource;

import com.teste.sacola.model.Item;
import com.teste.sacola.model.Sacola;
import com.teste.sacola.resource.dto.ItemDto;
import com.teste.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//Pode também ser chamada de controller
@RestController
@RequestMapping("/sacolas")
@RequiredArgsConstructor
public class SacolaResource {
    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{id}")
    public Sacola fecharSacola(@PathVariable("id") Long id,
           @RequestParam("formaPagamento") int formaPagamento)  {
        return sacolaService.fecharSacola(id, formaPagamento);
    }
}
