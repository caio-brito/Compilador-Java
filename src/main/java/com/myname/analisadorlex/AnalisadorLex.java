
/*
*Caio Vinicius Oliveira Brito RA:2150905
*Vitor Augusto Ozanick        RA:2152401 
 */
package com.myname.analisadorlex;

import java.io.IOException;
import java.util.StringTokenizer;

public class AnalisadorLex {

    public static void main(String[] args) throws IOException {

        Leitura l = new Leitura(args[0]);
        GyhLex identChar = new GyhLex();
        String LinhaLida, teste, teste1, junta;
        Token s;//
        int counter = 0;//numero de linhas lidas
        LinhaLida = l.LerLinha();//recebe a linha lida em leitura
        counter++;//incrementa primeira linha                                 
        boolean entrou = false;//Verifica se foi feita concatenacao de tokens para controlar o step do StringTokenizer

        while (LinhaLida != null) {

            StringTokenizer st = new StringTokenizer(LinhaLida, " \":#()<=>+-?==@^&|~!", true);//Corta a string recebida em "tokens", os delimitadores sao dados pelo segundo parametro, o terceiro parametro true indica que parametros tambem devem ser retornados

            while (st.hasMoreTokens()) {//garante que o loop nao tentara ler um token extrapolando a string

                entrou = false;
                teste = st.nextToken();
                if (teste.equals(" ") == false) {//excluir espacos

                    if (teste.equals("#")) {//excluir comentarios

                        break;
                    }
                    if (teste.equals(">")) {//verifica condicao adversa de >= para gerar token
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {//gera > se o proximo token diff de "=" 
                                s = identChar.geraToken(teste, counter);
                                System.out.print(s);
                                teste = teste1;
                            }
                        }
                    }
                    if (teste.equals("<")) {//verifica condicao adversa de <= para gerar token
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {//gera < se o proximo token diff de "="
                                s = identChar.geraToken(teste, counter);
                                System.out.print(s);
                                teste = teste1;
                            }
                        }
                    }
                    if (teste.equals("=")) {//verifica condicao adversa de == para gerar token
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {//gera = se o proximo token diff de "="
                                s = identChar.geraToken(teste, counter);
                                System.out.print(s);
                                teste = teste1;
                            }
                        }
                    }
                    if (teste.equals("!")) {//verifica condicao adversa de != para gerar token
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            
                            if (teste1.equals(" ") == false) {//gera ! se o proximo token diff de "="
                                s = identChar.geraToken(teste, counter);
                                System.out.print(s);
                                teste = teste1;
                            }else if (teste1.equals(" ") == true){//ignora o token espaco pra nao gerar falso erro
                                s = identChar.geraToken(teste, counter);
                                System.out.print(s);
                                entrou = true;
                            }
                        }
                    }
                    if (teste.equals(":")) {//verifica condicao adversa de != para gerar token
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.print(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {//gera : se o proximo token diff de "="
                                s = identChar.geraToken(teste, counter);
                                System.out.print(s);
                                teste = teste1;
                            }
                        }
                    }
                    if (teste.equals("\"") && st.hasMoreTokens()) {//verifica sequências de caracteres envolta por aspas, evitando erros por delimitadores detro da string

                        teste1 = st.nextToken();
                        junta = teste + teste1;
                        while (teste1.equals("\"") == false && st.hasMoreTokens()) {//concatena os tokens

                            teste1 = st.nextToken();
                            junta = junta + teste1;
                        }
                        s = identChar.geraToken(junta, counter);
                        System.out.print(s);
                        entrou = true;
                    }

                    if (entrou == false) {//se nao houve uso de ambas os tokens pegos, printa token que sobrou
                        s = identChar.geraToken(teste, counter);
                        System.out.print(s);
                    }
                }
            }
            LinhaLida = l.LerLinha();//recebe nova linha a ser lexicamente analisada
             System.out.print("\n");
            counter++;
        }
    }
}
