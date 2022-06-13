package com.example.authteste.controller.dto;

import com.example.authteste.model.Carro;
import com.example.authteste.model.Marca;

import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

public class CarroDTO {

    private Long id;
    private String marca;
    private Long idMarca;
    private String modelo;
    private Integer potencia;
    private String cor;

    public CarroDTO() {
    }

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.marca = carro.getMarca().getNome();
        this.modelo = carro.getModelo();
        this.potencia = carro.getPotencia();
        this.cor = carro.getCor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public static List<CarroDTO> convertToDto(List<Carro> carros) {

        List<CarroDTO> values = new ArrayList<>();

        carros.forEach(p -> {
            CarroDTO dto = new CarroDTO();
            dto.setId(p.getId());
            dto.setMarca(p.getMarca().getNome());
            dto.setPotencia(p.getPotencia());
            dto.setModelo(p.getModelo());
            dto.setCor(p.getCor());

            values.add(dto);
        });

        return values;
    }

}
