package com.myname.analisadorlex;

import java.util.ArrayList;

public class GyhSyntactic {//Codigo baseado em uma sequencia de resolucoes das siglas dos tokens, as quais são passadas recusrivamente pelas regras da gramatica GYH até que se obtenha uma validação ou erro sintático

    private int index = 0;//inteiro utilizado como ponteiro para andar pelos tokens sem perder a posição atual e ainda podendo verificar a proxima ou mais

    public void DelimAnalizer(ArrayList<TipoToken> tokenList) {//Verifica se é um delimitador e se a linguagem possui um DEC
        if (tokenList.get(index + 1).toString().equals("PCDec")) {//Transforma a sigla em string para realizar a comparação
            index++;
            ListDeclaracoes(tokenList);//Chama a lisca
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

    public void ComandoEntrada(ArrayList<TipoToken> tokenList) {//Faz a verificação do tipo LER VAR que se espera na gramatica do codigo

        if (tokenList.get(index + 1).toString().equals("Var")) {//Verifica se há um tipo var a ser utilizado, para chamar a funcao com seguranca
            index++;
            ListaComandos(tokenList);
        } else {
            System.out.println("Erro no comando, esperado LER + Variavel");
        }
    }//End ComandoEntrada

    public void ComandoAtribuicao(ArrayList<TipoToken> tokenList) {//Verifica a coerencia da atribuição de valores a variaveis

        if (tokenList.get(index + 1).toString().equals("Atrib")) {
            index++;
            ExpressaoAritmetica(tokenList);
            ListaComandos(tokenList);
        } else {
            System.out.println("Inesperado Token: " + tokenList.get(index + 1).toString());
        }
    }//End ComandoAtribuicao

    public void ComandosSaida(ArrayList<TipoToken> tokenList) {//Verifica a composição da linguagem GYH 'IMPRIMIR' VARIAVEL | 'IMPRIMIR' CADEIA;

        if (tokenList.get(index + 1).toString().equals("Var") || tokenList.get(index + 1).toString().equals("Cadeia")) {
            index++;
        } else {
            System.out.println("Erro, IMPRIMIR espera uma variavel ou uma cadeia");
        }
    }

    public void ComandoCondicao(ArrayList<TipoToken> tokenList) {//Verifica ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando CondA’. tendo ja recebido a verificação com a existencia de SE 

        ExpressaoRelacional(tokenList);

        if (tokenList.get(index + 1).toString().equals("PCEntao")) {//Todo SE precisa de ENTAO, faz-se a verificação da existencia do mesmo para tratamento de erros
            index++;
            ListaComandos(tokenList);
            CondALinha(tokenList);
        } else {
            System.out.println("Erro, SE espera uma finalização ENTAO para as condições");
        }
    }

    public void ComandoRepeticao(ArrayList<TipoToken> tokenList) {//Inicia o loop recursivo de verificações

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

    public void FatorAritmetico(ArrayList<TipoToken> tokenList) {//Faz as verificação dos terminais NumInt,Var, NumReal além de 

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
            case "AbrePar":
                index++;
                ExpressaoAritmetica(tokenList);
                index++;
                if (!tokenList.get(index).toString().equals("FechaPar")) {
                    System.out.println("Erro, espera-se um fecha parenteses");
                }
                break;

            default:

                //Colocar erro
                break;
        }

    }

    public void SubAlgoritmo(ArrayList<TipoToken> tokenList) {//Verifica presença do PCFim para validar o SubAlgoritmo  

        ListaComandos(tokenList);
        if (!tokenList.get(index).toString().equals("PCFim")) {
            System.out.println("Erro, espera-se um FIM para completar o INI");
        }
    }

    public void ListaComandos(ArrayList<TipoToken> tokenList) {//Encaminha o programa para o Comando referente ao token analisado  

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

            case "PCIni":
                SubAlgoritmo(tokenList);
                break;

            default:

                if (!tokenList.get(index).toString().equals("PCFim")) {
                    System.out.println("Inesperado Token: " + tokenList.get(index).toString());
                }
                break;

        }//End switch
    }//End ListaComandos

    public void ListDeclaracoes(ArrayList<TipoToken> tokenList) {//Verifica a composição do codigo que segue apos :DEC, validando as declarações
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
