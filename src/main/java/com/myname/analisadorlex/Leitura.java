package com.myname.analisadorlex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {

    BufferedReader br;

    public Leitura(String arquivo) throws FileNotFoundException {

        File arq = new File(arquivo);
        FileReader fr = new FileReader(arq);
        br = new BufferedReader(fr);
    }

    public String LerLinha() throws FileNotFoundException, IOException {//arqPast deve ser uma String com a origem do arquivo a ser manipulado

        String linha = br.readLine();//Le uma linha completa do arquivo passado para o metodo LerLinha

        return linha;

    }

}
