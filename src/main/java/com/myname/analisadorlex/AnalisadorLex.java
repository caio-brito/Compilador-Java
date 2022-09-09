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
        String LinhaLida, token1, token2, contadorLinhaS;
        Token s;
        int contadorLinha = 0;

        LinhaLida = l.LerLinha();
        contadorLinha++;

        while (LinhaLida != null) {

            StringTokenizer st = new StringTokenizer(LinhaLida, " :#()<=>+-", true);

            while (st.hasMoreTokens()) {

                token1 = st.nextToken();

                if (token1.equals("#")) {
                    break;
                } else if (token1.equals(" ") == false) {

                    if (st.hasMoreTokens()) {
                        token2 = st.nextToken();

                        if (token1.equals(":") && token2.equals("=")) {

                            token1 = token1 + token2;
                            contadorLinhaS = String.valueOf(contadorLinha);
                            s = identChar.geraToken(token1, contadorLinhaS);
                            System.out.println(s);
                        } else if (token1.equals("<") && token2.equals("=")) {
                            token1 = token1 + token2;
                            contadorLinhaS = String.valueOf(contadorLinha);
                            s = identChar.geraToken(token1, contadorLinhaS);
                            System.out.println(s);
                        } else if (token1.equals("=") && token2.equals("=")) {
                            token1 = token1 + token2;
                            contadorLinhaS = String.valueOf(contadorLinha);
                            s = identChar.geraToken(token1, contadorLinhaS);
                            System.out.println(s);
                        } else if (token1.equals(">") && token2.equals("=")) {
                            token1 = token1 + token2;
                            contadorLinhaS = String.valueOf(contadorLinha);
                            s = identChar.geraToken(token1, contadorLinhaS);
                            System.out.println(s);
                        } else if (token1.equals("!") && token2.equals("=")) {
                            token1 = token1 + token2;
                            contadorLinhaS = String.valueOf(contadorLinha);
                            s = identChar.geraToken(token1, contadorLinhaS);
                            System.out.println(s);
                        } else {

                            contadorLinhaS = String.valueOf(contadorLinha);
                            s = identChar.geraToken(token1, contadorLinhaS);
                            System.out.println(s);
                            s = identChar.geraToken(token2, contadorLinhaS);
                            System.out.println(s);

                        }
                    }
                }
            }
            LinhaLida = l.LerLinha();
            contadorLinha++;
        }
    }
}
