package com.myname.analisadorlex;

import java.util.ArrayList;

public class GyhSyntactic {

    private int index = 0;

    public void DelimAnalizer(ArrayList<TipoToken> tokenList) {
        if (tokenList.get(index + 1).toString().equals("PCDec")) {//Transforma a sigla em string para realizar a comparação
            index++;
            ListDeclaracoes(tokenList);
        } else {
            System.out.println("Erro sintático na declaração do lexema palavra-chave, faltando DEC");
        }
    }//End DelimAnalizer

    public void PCProgAnalizer(ArrayList<TipoToken> tokenList) {

        if (tokenList.get(index + 1).toString().equals("PCProg")) {//Transforma a sigla em string para realizar a comparação
            index++;

            ListaComandos(tokenList);
        } else {
            System.out.println("Erro sintático na declaração do lexema palavra-chave, faltando PROG");
        }

    }
    
    public void ComandoEntrada(ArrayList<TipoToken> tokenList){
        
         if (tokenList.get(index + 1).toString().equals("Var")) {
             index++;
             System.out.println("Chamou PCLer do comando entrada");
             ListaComandos(tokenList);
         }else{
             System.out.println("Erro no comando, esperado LER + Variavel");
         }
    }

    public void ListaComandos(ArrayList<TipoToken> tokenList) {

        index++;

        switch (tokenList.get(index).toString()) {

            case "PCLer":
                
                ComandoEntrada(tokenList);
                
                break;
                
            case "Var":
                
                System.out.println("Chamou a verificação do var");
                
                break;

            default:
                break;
        }

    }

    public void ListDeclaracoes(ArrayList<TipoToken> tokenList) {

        if (tokenList.get(index + 1).toString().equals("Var")) {
            index++;
            if (tokenList.get(index + 1).toString().equals("Delim")) {
                index++;
                if (tokenList.get(index + 1).toString().equals("PCInt") || tokenList.get(index + 1).toString().equals("PCReal")) {
                    index++;
                    if (tokenList.get(index + 1).toString().equals("Var")) {
                        ListDeclaracoes(tokenList);
                    } else if (tokenList.get(index + 1).toString().equals("Delim")) {
                        index++;

                        PCProgAnalizer(tokenList);

                    }//End else if
                } else {
                    System.out.println("Erro na declaração do tipo da variavel");
                }//End else erro na declaração do tipo da variavel
            } else {
                System.out.println("Erro na declaração do delimitador(atribuição) da variavel");
            }//End else erro na declaração do delimitador(atribuição) da variavel
        } else {
            System.out.println("Erro na declaração do do nome da variavel");
        }//End else erro na declaração do nome da variavel
    }//End ListDeclaracoes

    public void SyntaticAnalizer(ArrayList<TipoToken> tokenList) {

        DelimAnalizer(tokenList);

    }

}
