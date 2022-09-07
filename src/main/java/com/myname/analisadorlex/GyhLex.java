package com.myname.analisadorlex;

public class GyhLex {

    public Token geraToken(String linha) {

        int count = 0, estado = 1;//Contador do indice atual da linha recebida para verificacao no automato e estado do automato
        String c1, c2;
        String auxiliar;

        ProxChar lpc = new ProxChar();

        c1 = String.valueOf(lpc.LerProxChar(linha, count));

        // if (linha.length() > count + 1) {
        count++;
        c2 = String.valueOf(lpc.LerProxChar(linha, count));
        // }

        // while (estado != -1) {
        switch (estado) {

            case 1://Estado 1 do automato              

                //Operadores Aritimeticos
                if (c1.equals("*")) {//Estado 2 do desenho

                    return new Token(TipoToken.OpAritMult, c1);

                } else if (c1.equals("/")) {// Estado 3 do desenho

                    return new Token(TipoToken.OpAritDiv, c1);

                } else if (c1.equals("+")) {// Estado 4 do desenho

                    return new Token(TipoToken.OpAritSoma, c1);

                } else if (c1.equals("-")) {// Estado 5 do desenho

                    return new Token(TipoToken.OpAritSub, c1);

                } else if (c1.equals("<")) {// Estado 6 do desenho

                    if (c2.equals("=")) {//    Estado 7 do desenho

                        auxiliar = c1 + c2;
                        return new Token(TipoToken.OpRelMenorIgual, auxiliar);
                        
                    } else {//                 Estado 8 do desenho
                        
                        return new Token(TipoToken.OpRelMenor, c1);
                        
                    }
                } else if (c1.equals(">")) {//Estado 9 do desenho

                    if (c2.equals("=")) {//   Estado 10 do desenho

                        auxiliar = c1 + c2;
                        return new Token(TipoToken.OpRelMaiorIgual, auxiliar);
                        
                    } else {//                Estado 11 do desenho
                        
                        return new Token(TipoToken.OpRelMaior, c1);
                        
                    }
                } else if (c1.equals("!")) {//Estado 14 do desenho
                    
                    if(c2.equals("=")){//     Estado 15 do desenho
                    return new Token(TipoToken.OpRelDif, c1);
                    }
                }
        }
        return null;
    }
}

/* 
    private Token operadorAritmetico() {
        int caractereLido = ldat.lerProxCaractere();
        char c = (char) caractereLido;
        if (c == '*') {
            return new Token(TipoToken.OpAritMult, "*");
        } else if (c == '/') {
            return new Token(TipoToken.OpAritDiv, "/");
        } else if (c == '+') {
            return new Token(TipoToken.OpAritSoma, "+");
        } else if (c == '-') {
            return new Token(TipoToken.OpAritSub, "-");
        } else {
            return null;
        }

    }

    
    

    private Token parenteses() {
        int caractereLido = ldat.lerProxCaractere();
        char c = (char) caractereLido;
        if (c == '(') {
            return new Token(TipoToken.AbrePar, "(");
        } else if (c == ')') {
            return new Token(TipoToken.FechaPar, ")");
        } else {
            return null;
        }
    }

    private Token operadorRelacional() {
        int caractereLido = ldat.lerProxCaractere();
        char c = (char) caractereLido;
        if (c == '<') {
            c = (char) ldat.lerProxCaractere();
            if (c == '=') {
                return new Token(TipoToken.OpRelMenorIgual, "<=");
            } else {
                ldat.retroceder();
                return new Token(TipoToken.OpRelMenor, "<");
            }
        } else if (c == '>') {
            c = (char) ldat.lerProxCaractere();
            if (c == '=') {
                return new Token(TipoToken.OpRelMaiorIgual, ">=");
            } else {
                ldat.retroceder();
                return new Token(TipoToken.OpRelMaior, ">");
            }
        } else if (c == '=') {
            c = (char) ldat.lerProxCaractere();
            if (c == '=') {
                return new Token(TipoToken.OpRelIgual, "==");
            } else {
                ldat.retroceder();
            }
        } else if (c == '!') {
            c = (char) ldat.lerProxCaractere();
            if (c == '=') {
                return new Token(TipoToken.OpRelDif, "!=");
            } else {
                ldat.retroceder();
            }
        }
        return null;
    }

    private Token numeros() {
        int estado = 1;
        while (true) {
            char c = (char) ldat.lerProxCaractere();
            if (estado == 1) {
                if (Character.isDigit(c)) {
                    estado = 2;
                } else {
                    return null;
                }
            } else if (estado == 2) {
                if (c == '.') {
                    c = (char) ldat.lerProxCaractere();
                    if (Character.isDigit(c)) {
                        estado = 3;
                    } else {
                        return null;
                    }
                } else if (!Character.isDigit(c)) {
                    ldat.retroceder();
                    return new Token(TipoToken.NumInt, ldat.getLexema());
                }
            } else if (estado == 3) {
                if (!Character.isDigit(c)) {
                    ldat.retroceder();
                    return new Token(TipoToken.NumReal, ldat.getLexema());
                }

            }

        }

    }

    private Token variavel() {
        int estado = 1;
        while (true) {
            char c = (char) ldat.lerProxCaractere();
            if (estado == 1) {
                if (Character.isLetter(c)) {
                    estado = 2;
                } else {
                    return null;
                }
            } else if (estado == 2) {
                if (!Character.isLetterOrDigit(c)) {
                    ldat.retroceder();
                    return new Token(TipoToken.Var, ldat.getLexema());
                }
            }
        }
    }

    private Token cadeia() {
        int estado = 1;
        while (true) {
            char c = (char) ldat.lerProxCaractere();
            if (estado == 1) {
                if (c == '\'') {
                    estado = 2;
                } else {
                    return null;
                }
            } else if (estado == 2) {
                if (c == '\n') {
                    return null;
                }
                if (c == '\'') {
                    return new Token(TipoToken.Cadeia, ldat.getLexema());
                } else if (c == '\\') {
                    estado = 3;
                }
            } else if (estado == 3) {
                if (c == '\n') {
                    return null;
                } else {
                    estado = 2;
                }
            }

        }

    }

    private void espacoEComentarios() {
        int estado = 1;
        while (true) {
            char c = (char) ldat.lerProxCaractere();
            if (estado == 1) {
                if (Character.isWhitespace(c) || c == ' ') {
                    estado = 2;
                } else if (c == '%') {
                    estado = 3;
                } else {
                    ldat.retroceder();
                    return;
                }
            } else if (estado == 2) {
                if (c == '%') {
                    estado = 3;
                } else if (!(Character.isWhitespace(c) || c == ' ')) {
                    ldat.retroceder();
                    return;
                }
            } else if (estado == 3) {
                if (c == '\n') {
                    return;
                }
            }
        }

    }

    private Token palavrasChave() {
        while (true) {
            char c = (char) ldat.lerProxCaractere();
            if (!Character.isLetter(c)) {
                ldat.retroceder();
                String lexema = ldat.getLexema();
                if (lexema.equals("DEC")) {
                    return new Token(TipoToken.PCDec, lexema);
                } else if (lexema.equals("PR")) {
                    return new Token(TipoToken.PCProg, lexema);
                } else if (lexema.equals("INT")) {
                    return new Token(TipoToken.NumInt, lexema);
                } else if (lexema.equals("REAL")) {
                    return new Token(TipoToken.PCReal, lexema);
                } else if (lexema.equals("LER")) {
                    return new Token(TipoToken.PCLer, lexema);
                } else if (lexema.equals("IMPRIMIR")) {
                    return new Token(TipoToken.PCImprimir, lexema);
                } else if (lexema.equals("SE")) {
                    return new Token(TipoToken.PCSe, lexema);
                } else if (lexema.equals("SENAO")) {
                    return new Token(TipoToken.PCSenao, lexema);
                } else if (lexema.equals("ENTAO")) {
                    return new Token(TipoToken.PCEntao, lexema);
                } else if (lexema.equals("ENQTO")) {
                    return new Token(TipoToken.PCEnqto, lexema);
                } else if (lexema.equals("INI")) {
                    return new Token(TipoToken.PCIni, lexema);
                } else if (lexema.equals("FIM")) {
                    return new Token(TipoToken.PCFim, lexema);
                } else if (lexema.equals("E")) {
                    return new Token(TipoToken.OpBoolE, lexema);
                } else if (lexema.equals("OU")) {
                    return new Token(TipoToken.OpBoolOu, lexema);
                } else {
                    return null;
                }
            }
        }
    }

    private Token fim() {
        int caractereLido = ldat.lerProxCaractere();
        if (caractereLido == -1) {
            return new Token(TipoToken.Fim, "Fim");
        }
        return null;
    }
    
    
}
 */
