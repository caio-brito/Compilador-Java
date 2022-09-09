package com.myname.analisadorlex;

public class ProxChar {

    public char LerProxChar(String Linha, int indi) {
        char c;
        c = Linha.charAt(indi);
        return c;
    }
     
    public boolean confere(String str, int contador) {
        return str.length() > contador + 2; 
   }
}
