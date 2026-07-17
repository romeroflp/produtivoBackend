package com.example.produtivoBackend.domain.tasks.enums;

import lombok.Getter;

@Getter
public enum StatusTask {
    IN_PROGRESS("EM PROGRESSO"),
    PENDING("PENDENTE"),
    COMPLETED("COMPLETO"),
    OVERDUE("ATRASADO"),
    CANCELED("CANCELADO");

    private final String label;

    StatusTask(String label){
        this.label = label;
    }
}
