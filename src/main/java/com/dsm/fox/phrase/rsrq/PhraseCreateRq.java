package com.dsm.fox.phrase.rsrq;

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
        if(man==null) {
            return user;
        }
        return this.man;
    }
}
