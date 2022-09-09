package com.myname.analisadorlex;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;

public class GyhLex {

    public Token geraToken(String tokenLido) {

        if (isLowerCase(tokenLido.charAt(0))) {
            return new Token(TipoToken.Var, tokenLido);
        } else if (isDigit(tokenLido.charAt(0))) {
            if (tokenLido.contains(".")) {
                return new Token(TipoToken.NumReal, tokenLido);
            } else {
                return new Token(TipoToken.NumInt, tokenLido);
            }
        }

        switch (tokenLido) {

//Operadores Aritimeticos-------------------------------------------------------                
            case "*":
                return new Token(TipoToken.OpAritMult, tokenLido);
            case "/":
                return new Token(TipoToken.OpAritDiv, tokenLido);
            case "+":
                return new Token(TipoToken.OpAritSoma, tokenLido);
            case "-":
                return new Token(TipoToken.OpAritSub, tokenLido);
            case "–":
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
            case "SE":
                return new Token(TipoToken.PCSe, tokenLido);
            case "SENAO":
                return new Token(TipoToken.PCSenao, tokenLido);

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
            case "E":
                return new Token(TipoToken.OpBoolE, tokenLido);
            case "OU":
                return new Token(TipoToken.OpBoolOu, tokenLido);

            default:

                break;
        }
        return null;
    }
}
