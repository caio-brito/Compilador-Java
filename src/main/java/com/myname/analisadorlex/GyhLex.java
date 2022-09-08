package com.myname.analisadorlex;

public class GyhLex {

    public Token geraToken(String linha) {

        int count = 0, estado = 1;//Contador do indice atual da linha recebida para verificacao no automato e estado do automato
        String c1, c2;
        String auxiliar;

        ProxChar lpc = new ProxChar();

        c1 = String.valueOf(lpc.LerProxChar(linha, count));

        // if (linha.length() > count + 1) {
        c2 = String.valueOf(lpc.LerProxChar(linha, count + 1));

        // }
        // while (estado != -1) {
        switch (estado) {

            case 1: //Estado 1 do automato
                //Operadores Aritimeticos---------------------------------------
                switch (c1) {
                    case "*":
                        //Estado 2 do desenho

                        return new Token(TipoToken.OpAritMult, c1);
                    case "/":
                        // Estado 3 do desenho

                        return new Token(TipoToken.OpAritDiv, c1);
                    case "+":
                        // Estado 4 do desenho

                        return new Token(TipoToken.OpAritSoma, c1);
                    case "-":
                        // Estado 5 do desenho

                        return new Token(TipoToken.OpAritSub, c1);
                    //Operadores Relacionais---------------------------------
                    //
                    case "<":
                        //        Estado 6 do desenho

                        if (c2.equals("=")) {//    Estado 7 do desenho

                            auxiliar = c1 + c2;
                            return new Token(TipoToken.OpRelMenorIgual, auxiliar);

                        } else {//                 Estado 8 do desenho

                            return new Token(TipoToken.OpRelMenor, c1);

                        }
                    case ">":
                        //Estado 9 do desenho

                        if (c2.equals("=")) {//   Estado 10 do desenho

                            auxiliar = c1 + c2;
                            return new Token(TipoToken.OpRelMaiorIgual, auxiliar);

                        } else {//                Estado 11 do desenho

                            return new Token(TipoToken.OpRelMaior, c1);

                        }
                    case "=":
                        //Estado 12 do desenho

                        if (c2.equals("=")) {//   Estado 13 do desenho

                            auxiliar = c1 + c2;
                            return new Token(TipoToken.OpRelIgual, auxiliar);
                        } else {

                            //TRATAR ERRO, POIS NAO VAI PERTENCER A LINGUAGEM
                        }
                        break;
                    case "!":
                        //Estado 14 do desenho

                        if (c2.equals("=")) {//  Estado 15 do desenho

                            auxiliar = c1 + c2;
                            return new Token(TipoToken.OpRelDif, auxiliar);
                        }
                        break;
                    //Palavra chave-------------------------------------------
                    //
                    case "F":
                        //  Estado 16 do desenho
                        if (c2.equals("I")) {//  Estado 17 do desenho

                            auxiliar = c1 + c2;
                            if (lpc.confere(linha, count)) {

                                count = count + 2;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                if (c1.equals("M")) {//  Estado 18 do desenho
                                    auxiliar = auxiliar + c1;
                                    return new Token(TipoToken.PCFim, auxiliar);
                                }
                            }//COLCOAR CONTROLE DE ERRO
                        }
                        break;
                    case "R":
                        //  Estado 19 do desenho

                        if (c2.equals("E")) {//  Estado 20 do desenho

                            auxiliar = c1 + c2;
                            if (lpc.confere(linha, count)) {

                                count++;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                c2 = String.valueOf(lpc.LerProxChar(linha, count + 1));

                                if (c1.equals("A")) {//  Estado 21 do desenho

                                    if (c2.equals("L")) {//  Estado 22 do desenho

                                        auxiliar = auxiliar + c1 + c2;
                                        return new Token(TipoToken.PCReal, auxiliar);
                                    }
                                }
                            } else {
                                //Printar erro na formacao da palavra chave REAL
                            }
                        }
                        break;
                    case "E":
                        if (c2.compareTo("N") != 0) {

                            return new Token(TipoToken.OpBoolE, c1);
                        } else {

                            auxiliar = c1 + c2;

                            if (lpc.confere(linha, count)) {

                                count = count + 2;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                c2 = String.valueOf(lpc.LerProxChar(linha, count + 1));
                                auxiliar = auxiliar + c1 + c2;
                                if (c1.equals("T")) {

                                    if (c2.equals("A") && lpc.confere(linha, count)) {

                                        count = count + 2;
                                        c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                        if (c1.equals("O")) {

                                            auxiliar = auxiliar + c1;
                                            return new Token(TipoToken.PCEntao, auxiliar);
                                        }
                                    }
                                } else if (c1.equals("Q")) {

                                    if (c2.equals("T") && lpc.confere(linha, count)) {

                                        count = count + 2;
                                        c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                        if (c1.equals("O")) {

                                            auxiliar = auxiliar + c1;
                                            return new Token(TipoToken.PCEnqto, auxiliar);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "L":
                        //  Estado  do desenho
                        if (c2.equals("E")) {//  Estado  do desenho

                            auxiliar = c1 + c2;
                            if (lpc.confere(linha, count)) {

                                count = count + 2;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                if (c1.equals("R")) {//  Estado  do desenho
                                    auxiliar = auxiliar + c1;
                                    return new Token(TipoToken.PCLer, auxiliar);
                                }
                            }//COLCOCAR CONTROLE DE ERRO
                        }
                        break;
                    case "I":
                        //  Estado  do desenho
                        if (c2.equals("N")) {//  Estado  do desenho

                            auxiliar = c1 + c2;
                            if (lpc.confere(linha, count)) {

                                count = count + 2;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                switch (c1) {
                                    case "I":
                                        //  Estado  do desenho

                                        auxiliar = auxiliar + c1;
                                        return new Token(TipoToken.PCIni, auxiliar);
                                    case "T":
                                        auxiliar = auxiliar + c1;
                                        return new Token(TipoToken.PCInt, auxiliar);
                                    //Colocar controle de erro
                                    default:
                                        break;
                                }
                            }//COLCOAR CONTROLE DE ERRO
                        } else if (c2.equals("M")) {
                            
                            auxiliar = c1 + c2;
                            if (lpc.confere(linha, count)) {

                                count = count + 2;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                c2 = String.valueOf(lpc.LerProxChar(linha, count + 1));
                                
                                if (c1.equals("P")) {//  Estado 21 do desenho

                                    if (c2.equals("R")) {//  Estado 22 do desenho

                                        auxiliar = auxiliar + c1 + c2;

                                        if (lpc.confere(linha, count)) {

                                            count = count + 2;
                                            c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                            c2 = String.valueOf(lpc.LerProxChar(linha, count + 1));

                                            if (c1.equals("I")) {//  Estado 21 do desenho

                                                if (c2.equals("M")) {//  Estado 22 do desenho

                                                    auxiliar = auxiliar + c1 + c2;
                                                    if (lpc.confere(linha, count)) {

                                                        count = count + 2;
                                                        c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                                        c2 = String.valueOf(lpc.LerProxChar(linha, count + 1));

                                                        if (c1.equals("I")) {//  Estado 21 do desenho

                                                            if (c2.equals("R")) {//  Estado 22 do desenho

                                                                auxiliar = auxiliar + c1 + c2;
                                                                
                                                                return new Token(TipoToken.PCImprimir, auxiliar);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "D":
                        //  Estado do desenho
                        if (c2.equals("E")) {//  Estado  do desenho

                            auxiliar = c1 + c2;
                            if (lpc.confere(linha, count)) {

                                count = count + 2;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                if (c1.equals("C")) {//  Estado  do desenho
                                    auxiliar = auxiliar + c1;
                                    return new Token(TipoToken.PCDec, auxiliar);
                                }
                            }//COLCOAR CONTROLE DE ERRO
                        }
                        break;
                    case "P":
                        //  Estado 19 do desenho

                        if (c2.equals("R")) {//  Estado 20 do desenho

                            auxiliar = c1 + c2;
                            if (lpc.confere(linha, count)) {

                                count++;
                                c1 = String.valueOf(lpc.LerProxChar(linha, count));
                                c2 = String.valueOf(lpc.LerProxChar(linha, count + 1));

                                if (c1.equals("O")) {//  Estado 21 do desenho

                                    if (c2.equals("G")) {//  Estado 22 do desenho

                                        auxiliar = auxiliar + c1 + c2;
                                        return new Token(TipoToken.PCProg, auxiliar);
                                    }
                                }
                            } else {
                                //Printar erro na formacao da palavra chave REAL
                            }
                        }
                        break;
                    //Atribuicao - Delimitador - Parenteses-------------------
                    case "(":
                        return new Token(TipoToken.AbrePar, c1);
                    case ")":
                        return new Token(TipoToken.FechaPar, c1);
                    case ":":
                        if (c2.equals("=")) {

                            auxiliar = c1 + c2;

                            return new Token(TipoToken.Atrib, auxiliar);

                        } else {

                            return new Token(TipoToken.Delim, c1);

                        }
                    //Operador booleano---------------------------------------
                    case "O":
                        if (c2.equals("U")) {

                            auxiliar = c1 + c2;
                            return new Token(TipoToken.OpBoolOu, auxiliar);
                        } else {
                            //Tratar erro 
                        }
                        break;
                    default:
                        break;
                }

                return null;

        }
        return null;
    }
}
