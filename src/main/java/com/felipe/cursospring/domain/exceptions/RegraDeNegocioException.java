package com.felipe.cursospring.domain.exceptions;

public class RegraDeNegocioException  extends RuntimeException{
    
    public RegraDeNegocioException(String message){
        super(message);
    }
}
