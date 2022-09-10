
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
        Token s;
        int counter = 0;
        LinhaLida = l.LerLinha();
        counter++;
        boolean entrou = false;

        while (LinhaLida != null) {

            StringTokenizer st = new StringTokenizer(LinhaLida, " \":#()<=>+-?==@^&|~!", true);

            while (st.hasMoreTokens()) {

                entrou = false;
                teste = st.nextToken();
                if (teste.equals(" ") == false) {

                    if (teste.equals("#")) {
                        break;
                    }
                    if (teste.equals(">")) {
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.println(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {
                                s = identChar.geraToken(teste, counter);
                                System.out.println(s);
                                teste = teste1;
                            }
                        }
                    }
                    if (teste.equals("<")) {
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.println(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {
                                s = identChar.geraToken(teste, counter);
                                System.out.println(s);
                                teste = teste1;
                            }
                        }
                    }
                    if (teste.equals("=")) {
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.println(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {
                                s = identChar.geraToken(teste, counter);
                                System.out.println(s);
                                teste = teste1;
                            }
                        }
                    }
                    if (teste.equals("!")) {
                        teste1 = st.nextToken();
                        if (teste1.equals("=")) {
                            junta = teste + teste1;
                            s = identChar.geraToken(junta, counter);
                            System.out.println(s);
                            entrou = true;
                        } else {
                            
                            if (teste1.equals(" ") == false) {
                                s = identChar.geraToken(teste, counter);
                                System.out.println(s);
                                teste = teste1;
                            }else if (teste1.equals(" ") == true){
                                s = identChar.geraToken(teste, counter);
                                System.out.println(s);
                                entrou = true;
                            }
                        }
                    }
                    if (teste.equals(":")) {// :
                        teste1 = st.nextToken();// =
                        if (teste1.equals("=")) {// SE FOR =
                            junta = teste + teste1;// :=
                            s = identChar.geraToken(junta, counter);
                            System.out.println(s);
                            entrou = true;
                        } else {
                            if (teste1.equals(" ") == false) {
                                s = identChar.geraToken(teste, counter);// :
                                System.out.println(s);
                                teste = teste1;// TESTE = '='
                            }
                        }
                    }
                    if (teste.equals("\"") && st.hasMoreTokens()) {

                        teste1 = st.nextToken();
                        junta = teste + teste1;
                        while (teste1.equals("\"") == false && st.hasMoreTokens()) {

                            teste1 = st.nextToken();
                            junta = junta + teste1;
                        }
                        s = identChar.geraToken(junta, counter);
                        System.out.println(s);
                        entrou = true;
                    }

                    if (entrou == false) {
                        s = identChar.geraToken(teste, counter);
                        System.out.println(s);
                    }
                }
            }
            LinhaLida = l.LerLinha();
            counter++;
        }
    }
}
