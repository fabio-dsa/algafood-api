package com.algafood.api.controller;

import com.algafood.api.dto.EstadoDTO;
import com.algafood.api.dto.assembler.EstadoAssembler;
import com.algafood.api.dto.disassembler.EstadoDisassembler;
import com.algafood.core.util.ResourceCreated;
import com.algafood.domain.model.Estado;
import com.algafood.domain.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    private final EstadoAssembler estadoAssembler;

    private final EstadoDisassembler estadoDisassembler;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        return ResponseEntity.ok(
                this.estadoAssembler.toDTOList(this.estadoService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.estadoAssembler.toDTO(this.estadoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EstadoDTO estadoDTO) {
        Estado estado = this.estadoService.save(this.estadoDisassembler.toEntity(estadoDTO));
        return ResponseEntity.created(ResourceCreated.location(estado.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> update(@PathVariable Long id, @Valid @RequestBody EstadoDTO estadoDTO) {
        Estado estado = this.estadoService.update(id, this.estadoDisassembler.toEntity(estadoDTO));
        return ResponseEntity.ok(this.estadoAssembler.toDTO(estado));
    }
}
