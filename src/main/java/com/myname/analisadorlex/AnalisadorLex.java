package com.myname.analisadorlex;

import java.io.IOException;

public class AnalisadorLex {

    public static void main(String[] args) throws IOException {
      
        Leitura l = new Leitura(args[0]);
        GyhLex identChar = new GyhLex(); 
        String LinhaLida;
       
        LinhaLida = l.LerLinha();
        
        //Gerar token de palavras usando funcao tokanizer
        
        Token s = identChar.geraToken(LinhaLida);//passar token por token com um while
        System.out.print(s);
   
    }
}
