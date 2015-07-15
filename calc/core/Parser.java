package calc.core;
import java.io.*;

import calc.textui.edit.InvalidCellRange;

public class Parser {

    public static Spread parseFile(String nomeFicheiro) throws IOException, FileNotFoundException, InvalidCellRange {
	BufferedReader reader = null;

	try {
	    reader = new BufferedReader(new FileReader(nomeFicheiro));
	    String linha;
	    int linhas, colunas;
	    String[] args;
	    Spread spread = new Spread();
	    
	    linha = reader.readLine();
	    args = linha.split("=");
	    linhas = Integer.parseInt(args[1]);
	    
	    linha = reader.readLine();
	    args = linha.split("=");
	    colunas = Integer.parseInt(args[1]);
	    
	    Matrix matriz = new Matrix(linhas,colunas); // criar uma folha de calculo com tamanho linhas colunas
	    spread.setMatrix(matriz);
	    
	    while ((linha = reader.readLine()) != null) {
		parseExpressao(matriz, linha);
	    }
	    return spread;
	} finally {
	    if (reader != null)
		reader.close();
	}
    }

    public static void parseExpressao(Matrix matriz, String expressao) throws InvalidCellRange {
	String[] args = expressao.split("\\|");
	int[] endereco = parseEndereco(args[0]);
	Conteudo cont = null;

	if (args.length > 1)
	    cont = parseConteudo(matriz.getCell(endereco[0], endereco[1]), args[1]);
	
	matriz.addCell(endereco[0], endereco[1], cont); // coloca conteudo cont na linha e coluna pretendidas da folha de calculo
    }

    public static int[] parseEndereco(String endereco) {
    	if (endereco.contains(":")){ //se for um intervalo
    		return parseIntervalo(endereco);
    	}
    	else{ //se for uma celula
    		return parseCelula(endereco);
    	}
    }

    public static int[] parseIntervalo(String endereco) {
		String[] args = endereco.split(":");
		int[] args0 = parseCelula(args[0]);
		int[] args1 = parseCelula(args[1]);
		int[] vecInt = {args0[0], args0[1], args1[0], args1[1]};
		return vecInt;
    }
    public static int[] parseCelula(String endereco) {
		String[] args = endereco.split(";");
		int[] vecCell = {Integer.parseInt(args[0]), Integer.parseInt(args[1])};
		return vecCell;
    }
    public static Conteudo parseConteudo(Cell c, String conteudo) throws InvalidCellRange {
	if (conteudo.contains("(")) { // é uma função
	    String funcao = conteudo.substring(1); // remove =
	    String nomeFuncao = parseNomeFuncao(funcao);
	    String argumento = parseArgumentoFuncao(funcao);

	    if (conteudo.contains(","))
	    	return parseFuncaoBinaria(c, nomeFuncao, argumento);
	    else
	    	return parseFuncaoIntervalo(c, nomeFuncao, argumento);
	} else if (conteudo.contains("=")) {  // é uma referencia 
	    return parseReferencia(c, conteudo.substring(1));
	} else
	    return parseLiteral(conteudo);
    }

    private static String parseNomeFuncao(String funcao) {
	return funcao.substring(0, funcao.indexOf("("));
    }

    private static String parseArgumentoFuncao(String funcao) {
	return funcao.substring(funcao.indexOf("(") + 1, funcao.indexOf(")"));
    }

    public static FuncaoBin parseFuncaoBinaria(Cell c, String nomeFuncao, String argumento) throws InvalidCellRange {
	String[] argumentos = argumento.split(",");

	Parametro arg1 = parseParametro(c, argumentos[0]);
	Parametro arg2 = parseParametro(c, argumentos[1]);

	if(nomeFuncao.contains("MUL"))
	    return new MUL(c, arg1, arg2);
	if(nomeFuncao.contains("DIV"))
		return new DIV(c, arg1, arg2);
	if(nomeFuncao.contains("SUB"))
		return new SUB(c, arg1, arg2);
	if(nomeFuncao.contains("ADD"))
		return new ADD(c, arg1, arg2);
	return null;
    }

    public static FuncaoInt parseFuncaoIntervalo(Cell c, String nomeFuncao, String argumento) throws InvalidCellRange {
    if(nomeFuncao.contains("PRD"))
	    return new PRD(new Intervalo(parseEndereco(argumento),c.getMatrix()), c);
	if(nomeFuncao.contains("AVG"))
	    return new AVG(new Intervalo(parseEndereco(argumento),c.getMatrix()), c);
	return null;
    }

    public static Parametro parseParametro(Cell c, String argumento) throws InvalidCellRange {
	if (argumento.contains(";")) {
	    return parseReferencia(c, argumento);
	}
	
	return parseLiteral(argumento);
	
    }


    public static Literal parseLiteral(String literal) {
	return new Literal(Integer.parseInt(literal));
    }

    public static Referencia parseReferencia(Cell c, String referencia) throws InvalidCellRange {
	int[] vec = parseEndereco(referencia);
	return new Referencia(c, vec[0], vec[1]);
    }
}