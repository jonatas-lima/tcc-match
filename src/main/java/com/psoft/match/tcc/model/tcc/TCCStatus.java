package com.psoft.match.tcc.model.tcc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TCCStatus {
    PENDING("Pending"),
    ON_GOING("On going"),
    FINISHED("Finished");
	
	private String tipo;
	
	TCCStatus(String tipo){
        this.tipo = tipo;
    }

    @JsonValue
    public String getTipo(){
        return this.tipo;
    }
    
    @JsonCreator
    public static TCCStatus fromText(String texto) {
        for (TCCStatus tccStatus : TCCStatus.values()) {
            if (tccStatus.getTipo().equals(texto))
                return tccStatus;
        }
        throw new RuntimeException("tcc sttus invalido");
    }
}
