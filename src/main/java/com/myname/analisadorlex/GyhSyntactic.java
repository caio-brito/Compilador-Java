package com.myname.analisadorlex;

import java.util.ArrayList;

public class GyhSyntactic {
    
    int index =0;
    TipoToken1 comparar;//Compactação do TipoToken para as comparações com as siglas que são desse mesmo tipo
    
    private void DelimAnalizer (ArrayList<TipoToken> tokenList){
        
        if(tokenList.get(index + 1).equals(comparar.PCDec)){
           
            //chamar funcao listaDeclaracoes
            
        } else {
            //erro sintático
        }
        
    }//End DelimAnalizer
    
    public String SyntaticAnalizer (ArrayList<TipoToken> tokenList){
    
        tokenList.get(index);
        
        return null;
}
    
    
}
