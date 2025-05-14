package ru.apzakharov.demo.webraise.adapter.controller;

public interface ControllerMapper<DTO,DOMAIN> {

    DTO toDto(DOMAIN domain);
    DOMAIN toDomain(DTO domain);
}
