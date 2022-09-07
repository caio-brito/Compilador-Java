package com.myname.analisadorlex;

public class GyhLex {

    public Token geraToken(String linha) {

        int count = 0, estado = 0;//Contador do indice atual da linha recebida para verificacao no automato e estado do automato
        char c1, c2 = 0;
        String C1;

        ProxChar lpc = new ProxChar();

        while (count < linha.length() - 1) {

            c1 = lpc.LerProxChar(linha, count);

            if (linha.length() >= count + 1) {

                count++;
                c2 = lpc.LerProxChar(linha, count);
            }

            while (estado != -1) {

                switch (estado) {

                    case 1:

                        if (c1 == '*') {

                        } else if (c1 == '/') {

                        } else if (c1 == '+') {

                        } else if (c1 == '-') {

                        } else if (c1 == '<') {//Estado 6 do desenho

                            if (c2 == '=') {//Estado 7 do desenho

                                C1 = String.valueOf(c1) + String.valueOf(c2);
                                count++;
                                return new Token(TipoToken.OpRelMenorIgual, C1);
                            } else {
                                C1 = String.valueOf(c1);
                                return new Token(TipoToken.OpRelMenor, C1);
                            }

                        } else if (c1 == '<' && c2 == '=') {//Estado 8 do desenho

                        }

                        break;

                }
            }
        }
        /*
        if (c1 == ':' && c2 != '=') {

            return new Token(TipoToken.Delim, ":");
        } else {
            return new Token(TipoToken.Atrib, ":=");
        }
         */
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
