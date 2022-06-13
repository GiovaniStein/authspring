package com.example.authteste.service;

import com.example.authteste.controller.dto.CarroDTO;
import com.example.authteste.model.Carro;
import com.example.authteste.model.Marca;
import com.example.authteste.repository.CarroRepository;
import com.example.authteste.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public List<CarroDTO> findAll() {
        List<Carro> carros = carroRepository.findAll();
        return CarroDTO.convertToDto(carros);
    }

    public Carro findById(Long id) {
        Optional<Carro> val = carroRepository.findById(id);
        return val.orElse(null);
    }

    @Transactional
    public Carro save(CarroDTO dto) {
        Carro carro = new Carro();
        carro.setCor(dto.getCor());

        Optional<Marca> marca = marcaRepository.findById(dto.getIdMarca());
        carro.setMarca(marca.orElse(null));
        carro.setModelo(dto.getModelo());
        carro.setPotencia(dto.getPotencia());

        return carroRepository.save(carro);
    }

    @Transactional
    public Carro update(Long id, CarroDTO dto) {
        Optional<Carro> val = carroRepository.findById(id);

        Carro update = val.orElse(null);

        if (update != null) {
            update.setCor(dto.getCor());
            update.setModelo(dto.getModelo());
            update.setPotencia(dto.getPotencia());
            Optional<Marca> marca = marcaRepository.findById(dto.getIdMarca());
            update.setMarca(marca.orElse(null));

            carroRepository.save(update);
        }
        return update;
    }

    @Transactional
    public void delete(Long id) {
        Optional<Carro> val = carroRepository.findById(id);

        if (val.isPresent())
            carroRepository.delete(val.get());
        else
            throw new RuntimeException("Registro não encontrado");
    }

}
