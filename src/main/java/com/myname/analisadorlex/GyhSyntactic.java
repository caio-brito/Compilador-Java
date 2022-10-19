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

    }//End PCPogAnalizer

    public void ComandoEntrada(ArrayList<TipoToken> tokenList) {

        if (tokenList.get(index + 1).toString().equals("Var")) {
            index++;
            ListaComandos(tokenList);
        } else {
            System.out.println("Erro no comando, esperado LER + Variavel");
        }
    }

    public void ComandoAtribuicao(ArrayList<TipoToken> tokenList) {

        if (tokenList.get(index + 1).toString().equals("Atrib")) {
            index++;
            ExpressaoAritmetica(tokenList);
        } else {

        }//Erro na atribuicao, esperava-se um simbolo de atribuicao 
    }//End ComandoAtribuicao

    public void ExpressaoAritmetica(ArrayList<TipoToken> tokenList) {

        TermoAritmetico(tokenList);

//        if (tokenList.get(index + 1).toString().equals("+") || tokenList.get(index + 1).toString().equals("-")) {
//
//        }
    }//End ExpressaoAritmetica

    public void ExpressaoAritmeticaLinha(ArrayList<TipoToken> tokenList) {

    }

    public void TermoAritmetico(ArrayList<TipoToken> tokenList) {

        FatorAritmetico(tokenList);
        TermoAritmeticoLinha(tokenList);

    }//End TermoAritmetico

    public void TermoAritmeticoLinha(ArrayList<TipoToken> tokenList) {

        index++;           
        switch(tokenList.get(index).toString()){
            
            case "OpAritMult":
                FatorAritmetico(tokenList);
                
                TermoAritmeticoLinha(tokenList);
                break;
                
            case "OpAritDiv":
                System.out.println(tokenList.get(index).toString());
                FatorAritmetico(tokenList);
                TermoAritmeticoLinha(tokenList);
                break;
        
            default:
                System.out.println(tokenList.get(index).toString());
                break;
        }
    }//End TermoAritmeticoLinha

    public void FatorAritmetico(ArrayList<TipoToken> tokenList) {

        index++;
        switch (tokenList.get(index).toString()) {

            case "NumInt":
                System.out.println(tokenList.get(index).toString());
                break;
            case "Var":
 
                break;
            case "NumReal":
                break;
            case "(":

                //chamar expressao aritmetica
                break;

            case ")":

                break;

            default:
                
                
                //Colocar erro
                break;
        }

    }

    public void ListaComandos(ArrayList<TipoToken> tokenList) {

        index++;

        
        switch (tokenList.get(index).toString()) {

            case "PCLer":

                ComandoEntrada(tokenList);

                break;

            case "Var":

                ComandoAtribuicao(tokenList);

                break;

            default:
                break;
        }//End switch

    }//End ListaComandos

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
