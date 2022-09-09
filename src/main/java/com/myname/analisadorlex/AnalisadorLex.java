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
        String LinhaLida, teste,contadorLinhaS;
        Token s;
        int contadorLinha = 0;

        LinhaLida = l.LerLinha();
        contadorLinha++;
        while (LinhaLida != null) {

            StringTokenizer st = new StringTokenizer(LinhaLida, " :#()<=>+-", true);

            while (st.hasMoreTokens()) {

                teste = st.nextToken();
                
                if (teste.equals("#")) {
                    break;
                } else if (teste.equals(" ") == false) {
                    
                    contadorLinhaS = String.valueOf(contadorLinha);
                    s = identChar.geraToken(teste, contadorLinhaS);
                    System.out.println(s);
                }
            }
            LinhaLida = l.LerLinha();
            contadorLinha++;
        }
    }
}
