package com.psoft.match.tcc.model.tcc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TCCStatus {
    PENDING("Pending"),
    APPROVED("Approved");
	
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
        TCCStatus resultado;
        if(texto.equals("Pending")) resultado = TCCStatus.PENDING;
        else resultado = TCCStatus.APPROVED;
        return resultado;
    }
}
