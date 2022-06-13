package com.example.authteste.controller;

import com.example.authteste.controller.dto.CarroDTO;
import com.example.authteste.model.Carro;
import com.example.authteste.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping
    private ResponseEntity<List<CarroDTO>> findAll() {
        List<CarroDTO> all = carroService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        Carro carro = carroService.findById(id);
        return carro != null ? ResponseEntity.ok(new CarroDTO(carro)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<CarroDTO> save(@RequestBody CarroDTO dto) {

        try {
            Carro insert = carroService.save(dto);

            if (insert != null)
                return ResponseEntity.ok(new CarroDTO(insert));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return null;
    }


    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> atualizar(@PathVariable Long id, @RequestBody CarroDTO dto) {
        try {
            Carro update = carroService.update(id, dto);
            return update != null ? ResponseEntity.ok(new CarroDTO(update)) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        try {
            carroService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
