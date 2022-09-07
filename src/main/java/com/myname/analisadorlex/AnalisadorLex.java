package com.myname.analisadorlex;

import java.io.IOException;

public class AnalisadorLex {

    public static void main(String[] args) throws IOException {
      
        Leitura l = new Leitura(args[0]);
        GyhLex identChar = new GyhLex(); 
        String LinhaLida;
       
        LinhaLida = l.LerLinha();
        Token s = identChar.geraToken(LinhaLida);
        System.out.print(s);
        
        /*
        while(teste != null){
        
        //Passar a string para uma classe e manipular para gerar tokens
        //usar a classe GyhLex para manipular    
            
        teste = l.LerLinha();
        
        }
        */
        
    }


  
}
