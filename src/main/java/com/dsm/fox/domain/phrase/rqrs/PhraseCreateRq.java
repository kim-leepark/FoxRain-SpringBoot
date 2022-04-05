package com.dsm.fox.domain.phrase.rqrs;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PhraseCreateRq {
    @Getter
    @NotNull
    private final String content;
    private final String man;


    public String getMan(String user){
        if(man==null||man.isBlank()) {
            return user;
        }
        return this.man;
    }
}
