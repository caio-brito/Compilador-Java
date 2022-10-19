package com.myname.analisadorlex;

public class TipoToken1 {//classe criada para referenciar tiposToken em GyhSyntatic e diminuir repetição
	TipoToken PCDec, PCProg, PCInt, PCReal, PCLer, PCImprimir, PCSe,PCSenao, PCEntao, PCEnqto, PCIni, PCFim,
	OpAritMult, OpAritDiv, OpAritSoma, OpAritSub,
	OpRelMenor, OpRelMenorIgual, OpRelMaior, OpRelMaiorIgual, OpRelIgual, OpRelDif,
	OpBoolE, OpBoolOu,
	 Delim, 
	 Atrib,
	 AbrePar, FechaPar,
	 Var,
	 NumInt,
	 NumReal,
	 Cadeia,
         ErroNaLinha,
	 Fim;
}