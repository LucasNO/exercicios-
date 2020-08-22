package com.apiconcessionaria.enums;

import lombok.Getter;

@Getter
public enum Marcas {

    AUDI("AUDI"),
    BMW("BMW"),
    CHEVROLET("Chevrolet"),
    FIAT("Fiat"),
    FORD("Ford"),
    HONDA("Honda"),
    HYUNDAI("Hyundai"),
    MERCEDESBENS("Mercedes-Benz"),
    NISSAN("Nissan"),
    PEUGEOT("Peugeot"),
    RENAULT("Renault"),
    TOYOTA("Toyota"),
    VOLKSWAGEN("Volkswagen");

    private final String nomeMarca;

    Marcas(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

}
