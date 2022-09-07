package com.myname.analisadorlex;


public class Token {
   public TipoToken sigla;
   public String lexema;
   
   public Token(TipoToken sigcla, String lexema) {
	   this.sigla = sigcla;
	   this.lexema =lexema;
   }
   
   @Override
   public String toString() {
       
                
	   return "< "+sigla+", "+lexema+" >";
   }
}
