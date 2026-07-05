package com.example.produtivoBackend.domain.tasks;

import lombok.Getter;

@Getter
public enum StatusTask {
    IN_PROGRESS("EM PROGRESSO"),
    PENDING("PENDENTE"),
    COMPLETED("COMPLETO"),
    OVERDUE("ATRASADO");

    private final String label;

    StatusTask(String label){
        this.label = label;
    }



//    public boolean calculeOverdue(Task task){
//        return task.getExpirationDate().isAfter(LocalDateTime.now()) &&
//                !task.isCompleted();
//    } //TESTAR(URGENTE)
}
