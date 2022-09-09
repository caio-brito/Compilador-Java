package com.myname.analisadorlex;

import java.io.IOException;
import java.util.StringTokenizer;

public class AnalisadorLex {

    public static void main(String[] args) throws IOException {

        Leitura l = new Leitura(args[0]);
        GyhLex identChar = new GyhLex();
        String LinhaLida, teste;
        Token s;

        LinhaLida = l.LerLinha();

        //Gerar token de palavras usando funcao tokanizer
        while (LinhaLida != null) {

            StringTokenizer st = new StringTokenizer(LinhaLida, " :#", true);

            while (st.hasMoreTokens()) {

                teste = st.nextToken();
                
                if(teste.equals("#")){
                    break;
                }
                s = identChar.geraToken(teste);
                System.out.println(s);

            }
            LinhaLida = l.LerLinha();
        }
    }
}
