package com.example.produtivoBackend.domain.tasks.enums;

import lombok.Getter;

@Getter
public enum CategoryTask {
    WORK("Trabalho"),
    STUDY("Estudos"),
    PERSONAL("Pessoal"),
    HEALTH("Saúde"),
    FITNESS("Exercícios"),
    SHOPPING("Compras"),
    FINANCE("Finanças"),
    HOME("Casa"),
    FAMILY("Familia"),
    LEISURE("Lazer"),
    HOBBIES("Hobbies"),
    PROJECTS("Projetos"),
    MEETINGS("Reuniões"),
    TRAVEL("Viagens"),
    EVENTS("Eventos"),
    GOALS("Metas"),
    ROUTINE("Rotina"),
    URGENT("Urgente"),
    IMPORTANT("Importante"),
    OTHER("Outros");

    private final String label ;

    CategoryTask(String label){
        this.label = label;
    }


}
