package com.myname.analisadorlex;

public class GyhLex {

    public Token geraToken(String linha) {

       
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
                        return new Token(TipoToken.OpAritMult, c1);
                    case "/":
                        return new Token(TipoToken.OpAritDiv, c1);
                    case "+":
                        return new Token(TipoToken.OpAritSoma, c1);
                    case "-":
                        return new Token(TipoToken.OpAritSub, c1);
                    //Operadores Relacionais---------------------------------
                    //
                    case "<=":
                        
                    return new Token(TipoToken.OpRelMenorIgual, auxiliar);
                        
                    break;
                           
                    case "<":
                        
                    return new Token(TipoToken.OpRelMenor, c1);
                        
                    break;
                        

                    return new Token(TipoToken.OpRelMenor, c1);

                    case ">=":
                       
                    return new Token(TipoToken.OpRelMaiorIgual, auxiliar);

                    break;
                    case ">":
                       
                    return new Token(TipoToken.OpRelMaior, c1);

                    break;    
                        
                    case "=":
                        
                    return new Token(TipoToken.OpRelIgual, auxiliar);
                        
                        break;
                    case "!=":
                       
                        return new Token(TipoToken.OpRelDif, auxiliar);
                        
                        break;
                    //Palavra chave-------------------------------------------
                    //
                    case "FIM":
                     return new Token(TipoToken.PCFim, auxiliar);
                                
                        break;
                    case "REAl":
                        

                    return new Token(TipoToken.PCReal, auxiliar);
                                  
                        break;
                        
                    case "E":
                    
                    return new Token(TipoToken.OpBoolE, c1);
                       
                    
                    break;
                    case "ENTAO":
                    
                    return new Token(TipoToken.PCEntao, auxiliar);
                    
                    break;
                    case "ENQTO":
                    
                    return new Token(TipoToken.PCEnqto, auxiliar);
                                     
                        break;
                    case "LER":
                        
                                    return new Token(TipoToken.PCLer, auxiliar);
                           
                        break;
                    case "INI":
                        
                    return new Token(TipoToken.PCIni, auxiliar);
                    
                    break;
                    case "INT":
                                        
                    return new Token(TipoToken.PCInt, auxiliar);
                                    
                    break;
                    
                    case "IMPRIMIR":

                    return new Token(TipoToken.PCImprimir, auxiliar);
                            
                    break;
                    case "DEC":
                        
                    return new Token(TipoToken.PCDec, auxiliar);
                    
                    break;
                    case "PROG":
                    
                    return new Token(TipoToken.PCProg, auxiliar);
                                    
                    break;
                    //Atribuicao - Delimitador - Parenteses-------------------
                    case "(":
                        return new Token(TipoToken.AbrePar, c1);
                        break;
                    case ")":
                        return new Token(TipoToken.FechaPar, c1);
                        break;
                    case ":=":
                       
                    return new Token(TipoToken.Atrib, auxiliar);

                    break;
                    
                    case ":":
                    
                    return new Token(TipoToken.Delim, c1);

                    break;
                    //Operador booleano---------------------------------------
                    case "OU":
                        
                    return new Token(TipoToken.OpBoolOu, auxiliar);
                       
                    break;
                    default:
                        
                    break;
                }
                return null;
        }
        return null;
    }
}