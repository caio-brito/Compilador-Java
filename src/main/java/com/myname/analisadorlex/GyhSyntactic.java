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
            ListaComandos(tokenList);
        } else {

        }//Erro na atribuicao, esperava-se um simbolo de atribuicao 
    }//End ComandoAtribuicao

    public void ComandosSaida(ArrayList<TipoToken> tokenList) {

        if (tokenList.get(index + 1).toString().equals("Var") || tokenList.get(index + 1).toString().equals("Cadeia")) {
            index++;
        } else {
            System.out.println("Erro, IMPRIMIR espera uma variavel ou uma cadeia");
        }
    }

    public void ComandoCondicao(ArrayList<TipoToken> tokenList) {

        ExpressaoRelacional(tokenList);

        if (tokenList.get(index + 1).toString().equals("PCEntao")) {
            index++;
            ListaComandos(tokenList);
            CondALinha(tokenList);
        } else {
            System.out.println("Erro, SE espera uma finalização ENTAO para as condições");
        }
    }

    public void ComandoRepeticao(ArrayList<TipoToken> tokenList) {

        ExpressaoRelacional(tokenList);
        ListaComandos(tokenList);
    }

    public void ExpressaoRelacional(ArrayList<TipoToken> tokenList) {

        TermoRelacional(tokenList);
        if (tokenList.get(index + 1).toString().equals("OpBoolE") || tokenList.get(index + 1).toString().equals("OpBoolOU")) {
            RelALinha(tokenList);
        }
    }

    public void RelALinha(ArrayList<TipoToken> tokenList) {

        OperadorBooleano(tokenList);
        TermoRelacional(tokenList);
        if (VerificaOP_REL(tokenList.get(index + 1).toString())) {
            index++;
            RelALinha(tokenList);
        }
    }

    public void OperadorBooleano(ArrayList<TipoToken> tokenList) {

        switch (tokenList.get(index + 1).toString()) {

            case "OpBoolE":
                index++;
                break;

            case "OpBoolOU":
                index++;
                break;

            default:
                System.out.println("Erro, é esperado um operador booleano (E ou OU)");
                break;
        }//End switch
    }//End OperadorBooleano

    public void TermoRelacional(ArrayList<TipoToken> tokenList) {

        if (tokenList.get(index + 1).toString().equals("AbrePar")) {
            index++;
            ExpressaoRelacional(tokenList);

            if (tokenList.get(index + 1).toString().equals("FechaPar")) {

            } else {
                System.out.println("Erro, esperado um fecha parenteses");
            }
        } else {

            ExpressaoAritmetica(tokenList);
            if (VerificaOP_REL(tokenList.get(index + 1).toString())) {
                index++;
                ExpressaoAritmetica(tokenList);
            } else {
                System.out.println("Erro, faltando um operador relacional na condição");
            }
        }
    }

    public boolean VerificaOP_REL(String siglaOP) {

        switch (siglaOP) {

            case "OpRelMenor":
                return true;
            case "OpRelMaior":
                return true;
            case "OpRelMenorIgual":
                return true;
            case "OpRelMaiorIgual":
                return true;
            case "OpRelIgual":
                return true;
            case "OpRelDif":
                return true;
        }
        return false;
    }

    public void CondALinha(ArrayList<TipoToken> tokenList) {

        if (tokenList.get(index + 1).toString().equals("PCSenao")) {
            index++;
            ListaComandos(tokenList);
        }
    }

    public void ExpressaoAritmetica(ArrayList<TipoToken> tokenList) {

        TermoAritmetico(tokenList);
        ExpressaoAritmeticaLinha(tokenList);
    }//End ExpressaoAritmetica

    public void ExpressaoAritmeticaLinha(ArrayList<TipoToken> tokenList) {

        switch (tokenList.get(index + 1).toString()) {

            case "OpAritSoma":
                index++;
                FatorAritmetico(tokenList);
                ExpressaoAritmeticaLinha(tokenList);
                break;

            case "OpAritSub":
                index++;
                FatorAritmetico(tokenList);
                ExpressaoAritmeticaLinha(tokenList);
                break;

            default:
                break;
        }
    }

    public void TermoAritmetico(ArrayList<TipoToken> tokenList) {

        FatorAritmetico(tokenList);
        TermoAritmeticoLinha(tokenList);

    }//End TermoAritmetico

    public void TermoAritmeticoLinha(ArrayList<TipoToken> tokenList) {

        switch (tokenList.get(index + 1).toString()) {

            case "OpAritMult":
                index++;
                FatorAritmetico(tokenList);
                TermoAritmeticoLinha(tokenList);
                break;

            case "OpAritDiv":
                index++;
                FatorAritmetico(tokenList);
                TermoAritmeticoLinha(tokenList);
                break;

            default:
                break;
        }
    }//End TermoAritmeticoLinha

    public void FatorAritmetico(ArrayList<TipoToken> tokenList) {

        switch (tokenList.get(index + 1).toString()) {

            case "NumInt":
                index++;
                break;
            case "Var":
                index++;
                break;
            case "NumReal":
                index++;
                break;
            case "(":
                index++;
                ExpressaoAritmetica(tokenList);

                if (!tokenList.get(index + 1).toString().equals("FechaPar")) {
                    System.out.println("Erro, espera-se um fecha parenteses");
                }
                break;

            default:

                //Colocar erro
                break;
        }

    }

    public void SubAlgoritmo(ArrayList<TipoToken> tokenList) {
        ListaComandos(tokenList);

        if (!tokenList.get(index + 1).toString().equals("FIM")) {
            System.out.println("Erro, espera-se um FIM para completar o INI");
        }
        index++;
    }

    public void ListaComandos(ArrayList<TipoToken> tokenList) {

        index++;
        switch (tokenList.get(index).toString()) {

            case "PCImprimir":
                ComandosSaida(tokenList);
                break;

            case "PCLer":
                ComandoEntrada(tokenList);
                break;

            case "Var":
                ComandoAtribuicao(tokenList);
                break;

            case "PCSe":
                ComandoCondicao(tokenList);
                break;

            case "PCEnqto":
                ComandoRepeticao(tokenList);
                break;

            case "INI":
                SubAlgoritmo(tokenList);
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
