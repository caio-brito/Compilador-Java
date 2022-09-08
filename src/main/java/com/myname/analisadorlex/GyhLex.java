package com.myname.analisadorlex;

public class GyhLex {

    public Token geraToken(String linha) {

        String tokenLido;

//Operadores Aritimeticos-------------------------------------------------------
                switch (tokenLido) {
                    case "*":
                        return new Token(TipoToken.OpAritMult, tokenLido);
                    case "/":
                        return new Token(TipoToken.OpAritDiv, tokenLido);
                    case "+":
                        return new Token(TipoToken.OpAritSoma, tokenLido);
                    case "-":
                        return new Token(TipoToken.OpAritSub, tokenLido);

//Operadores Relacionais--------------------------------------------------------
                    case "<=":
                        return new Token(TipoToken.OpRelMenorIgual, tokenLido);
                    case "<":
                        return new Token(TipoToken.OpRelMenor, tokenLido);
                    case ">=":
                        return new Token(TipoToken.OpRelMaiorIgual, tokenLido);
                    case ">":
                        return new Token(TipoToken.OpRelMaior, tokenLido);
                    case "=":
                        return new Token(TipoToken.OpRelIgual, tokenLido);
                    case "!=":
                        return new Token(TipoToken.OpRelDif, tokenLido);

//Palavra chave-----------------------------------------------------------------
                    case "FIM":
                        return new Token(TipoToken.PCFim, tokenLido);
                    case "REAl":
                        return new Token(TipoToken.PCReal, tokenLido);
                    case "E":
                        return new Token(TipoToken.OpBoolE, tokenLido);
                    case "ENTAO":
                        return new Token(TipoToken.PCEntao, tokenLido);
                    case "ENQTO":
                        return new Token(TipoToken.PCEnqto, tokenLido);
                    case "LER":
                        return new Token(TipoToken.PCLer, tokenLido);
                    case "INI":
                        return new Token(TipoToken.PCIni, tokenLido);
                    case "INT":
                        return new Token(TipoToken.PCInt, tokenLido);
                    case "IMPRIMIR":
                        return new Token(TipoToken.PCImprimir, tokenLido);
                    case "DEC":
                        return new Token(TipoToken.PCDec, tokenLido);
                    case "PROG":
                        return new Token(TipoToken.PCProg, tokenLido);

//Atribuicao - Delimitador - Parenteses-----------------------------------------
                    case "(":
                        return new Token(TipoToken.AbrePar, tokenLido);
                    case ")":
                        return new Token(TipoToken.FechaPar, tokenLido);
                    case ":=":
                        return new Token(TipoToken.Atrib, tokenLido);
                    case ":":
                        return new Token(TipoToken.Delim, tokenLido);

//Operador booleano-------------------------------------------------------------
                    case "OU":
                        return new Token(TipoToken.OpBoolOu, tokenLido);

                    default:
                        break;
                }
                return null;
        }
        return null;
    }