
/*
*Caio Vinicius Oliveira Brito RA:2150905
*Vitor Augusto Ozanick        RA:2152401 
 */
package com.myname.analisadorlex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AnalisadorLex {

    public static void main(String[] args) throws IOException {

        ArrayList<TipoToken> siglaList = new ArrayList<>();
        
        Leitura l = new Leitura(args[0]);
        GyhLex identChar = new GyhLex();
        String LinhaLida, token1, token2, juntar;
        Token s;//
        int counter = 0;//numero de linhas lidas
        LinhaLida = l.LerLinha();//recebe a linha lida em leitura
        counter++;//incrementa primeira linha                                 
        boolean entrou = false;//Verifica se foi feita concatenacao de tokens para controlar o step do StringTokenizer

        while (LinhaLida != null) {

            StringTokenizer st = new StringTokenizer(LinhaLida, " \":#()<=>+-?==@^&|~!", true);//Corta a string recebida em "tokens", os delimitadores sao dados pelo segundo parametro, o terceiro parametro true indica que parametros tambem devem ser retornados

            while (st.hasMoreTokens()) {//garante que o loop nao tentara ler um token extrapolando a string

                entrou = false;
                token1 = st.nextToken();
                if (token1.equals(" ") == false) {//excluir espacos

                    if (token1.equals("#")) {//excluir comentarios

                        break;
                    }
                    if (token1.equals(">")) {//verifica condicao adversa de >= para gerar token
                        token2 = st.nextToken();
                        if (token2.equals("=")) {
                            juntar = token1 + token2;
                            s = identChar.geraToken(juntar, counter);
                            siglaList.add(s.sigla);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (token2.equals(" ") == false) {//gera > se o proximo token diff de "=" 
                                s = identChar.geraToken(token1, counter);
                                siglaList.add(s.sigla);
                                System.out.print(s);
                                token1 = token2;
                            }
                        }
                    }
                    if (token1.equals("<")) {//verifica condicao adversa de <= para gerar token
                        token2 = st.nextToken();
                        if (token2.equals("=")) {
                            juntar = token1 + token2;
                            s = identChar.geraToken(juntar, counter);
                            siglaList.add(s.sigla);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (token2.equals(" ") == false) {//gera < se o proximo token diff de "="
                                s = identChar.geraToken(token1, counter);
                               siglaList.add(s.sigla);
                                System.out.print(s);
                                token1 = token2;
                            }
                        }
                    }
                    if (token1.equals("=")) {//verifica condicao adversa de == para gerar token
                        token2 = st.nextToken();
                        if (token2.equals("=")) {
                            juntar = token1 + token2;
                            s = identChar.geraToken(juntar, counter);
                            siglaList.add(s.sigla);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (token2.equals(" ") == false) {//gera = se o proximo token diff de "="
                                s = identChar.geraToken(token1, counter);
                                siglaList.add(s.sigla);
                                System.out.print(s);
                                token1 = token2;
                            }
                        }
                    }
                    if (token1.equals("!")) {//verifica condicao adversa de != para gerar token
                        token2 = st.nextToken();
                        if (token2.equals("=")) {
                            juntar = token1 + token2;
                            s = identChar.geraToken(juntar, counter);
                            siglaList.add(s.sigla);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            
                            if (token2.equals(" ") == false) {//gera ! se o proximo token diff de "="
                                s = identChar.geraToken(token1, counter);
                                siglaList.add(s.sigla);
                                System.out.print(s);
                                token1 = token2;
                            }else if (token2.equals(" ") == true){//ignora o token espaco pra nao gerar falso erro
                                s = identChar.geraToken(token1, counter);
                                siglaList.add(s.sigla);
                                System.out.print(s);
                                entrou = true;
                            }
                        }
                    }
                    if (token1.equals(":")) {//verifica condicao adversa de != para gerar token
                        token2 = st.nextToken();
                        if (token2.equals("=")) {
                            juntar = token1 + token2;
                            s = identChar.geraToken(juntar, counter);
                            siglaList.add(s.sigla);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (token2.equals(" ") == false) {//gera : se o proximo token diff de "="
                                s = identChar.geraToken(token1, counter);
                                siglaList.add(s.sigla);
                                System.out.print(s);
                                token1 = token2;
                            }
                        }
                    }
                    if (token1.equals("\"") && st.hasMoreTokens()) {//verifica sequências de caracteres envolta por aspas, evitando erros por delimitadores detro da string

                        token2 = st.nextToken();
                        juntar = token1 + token2;
                        while (token2.equals("\"") == false && st.hasMoreTokens()) {//concatena os tokens

                            token2 = st.nextToken();
                            juntar = juntar + token2;
                        }
                        s = identChar.geraToken(juntar, counter);
                        siglaList.add(s.sigla);
                        System.out.print(s);
                        entrou = true;
                    }

                    if (entrou == false) {//se nao houve uso de ambas os tokens pegos, printa token que sobrou
                        s = identChar.geraToken(token1, counter);
                        siglaList.add(s.sigla);
                        System.out.print(s);
                    }
                }
            }
            LinhaLida = l.LerLinha();//recebe nova linha a ser lexicamente analisada
             System.out.print("\n");
            counter++;
            
        }
        System.out.print(siglaList.toString());
        System.out.print("\n");
        var teeeeste = new GyhSyntactic();
        teeeeste.SyntaticAnalizer(siglaList);
    }
}
