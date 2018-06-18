/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatoAnalisadorLexico;

import java.util.Stack;
import AutomatoException.GeneralException;

public class AnalisadorLexico {
    String auxPalavra;
    String auxChar;
    int posicao;
    int linha;
    int auxPosicao;
    Integer token;
    Stack<Integer> Pilha;
    
    /*
        Estado 0: Estado inicial
        Estado 1: Palavra reservada
        Estado 2: Numérico
        Estado 3: Simbolo
        Estado 4: Literal
        Estado 5: Identificador
    */
    //int estadoPalavra;
    
    /*
    Erros:
    65536: Não é identificador nem palavra reservada, ou erro mágico
    65535: Não é número inteiro
    65534: Número além dos limites
    65533: Literal maior que o limite
    65532: Comentário não finalizado
    65531: Identificador maior que o limite
    65530: Token espaço
    */
    
    public AnalisadorLexico(){
        Pilha = new Stack<>();
        auxPalavra = new String();
    }
    
    /*public Stack<Integer> percorrerFita(String fita){
        this.fita = fita;
        posicao = 0;
        
        mainfor:for(; posicao<fita.length(); posicao++){
            
            if(fita.substring(posicao, posicao+1).compareTo("(*") == 0){
                int auxPosicao = testeComentario(fita.substring(posicao));
                if(auxPosicao == -1){
                    token = 65532;
                    Pilha.push(token);
                    posicao = fita.length()-1;
                    auxPalavra = "";
                    continue;
                }
                else{
                    posicao += auxPosicao;
                    continue;
                }
            }
            if(tokenDelimitador(fita.charAt(posicao)) != 65536){
                Pilha.push(token);
                auxPalavra = "";
                continue;
            }
            else{
                auxPalavra += fita.charAt(posicao);
            }
            
            if(auxPalavra.matches(reservedRegex)){
                token = tokenReserved(auxPalavra);
            }
            
            if(auxPalavra.matches(numericRegex)){
                token = tokenNumerico(auxPalavra);
            }
            
            if(auxPalavra.matches(identifierRegex)){
                token = tokenIdentificador(auxPalavra);
                if(token == 65531){
                    System.out.println("Identificador maior que o limite");
                    break mainfor;
                }
            }
            
            if(auxPalavra.matches(simbolRegex)){
                token = tokenSinais(auxPalavra);
            }
            //token = tokenSinais(auxPalavra);
        }
        
        return this.Pilha;
    }*/
    
    private int tokenReserved(String palavra){
        int indice;
        if(palavra.length() == 2){
            switch(palavra){
                case "OF": indice = 10; break;
                case "IF": indice = 13; break;
                case "DO": indice = 17; break;
                case "OR": indice = 22; break;
                case "TO": indice = 28; break;
                default: indice = 25; break;
            }
        }
        else if(palavra.length() == 3){
            switch(palavra){
                case "VAR": indice = 4; break;
                case "END": indice = 7; break;
                case "AND": indice = 23; break;
                case "NOT": indice = 24; break;
                case "FOR": indice = 27; break;
                default: indice = 25; break;
            }
        }
        else if(palavra.length() == 4){
            switch(palavra){
                case "CALL": indice = 11; break;
                case "GOTO": indice = 12; break;
                case "THEN": indice = 14; break;
                case "ELSE": indice = 15; break;
                case "CASE": indice = 29; break;
                default: indice = 25; break;
            }
        }
        else if(palavra.length() == 5){
            switch(palavra){
                case "LABEL": indice = 2; break;
                case "CONST": indice = 3; break;
                case "BEGIN": indice = 6; break;
                case "ARRAY": indice = 9; break;
                case "WHILE": indice = 16; break;
                case "UNTIL": indice = 19; break;
                default: indice = 25; break;
           }
        }
        else{
            switch(palavra){
                case "PROGRAM": indice = 1; break;
                case "PROCEDURE": indice = 5; break;
                case "INTEGER": indice = 8; break;
                case "REPEAT": indice = 18; break;
                case "READLN": indice = 20; break;
                case "WRITELN": indice = 21; break;
                default: indice = 25; break;
            }
        }
        
        return indice;
    }
    
    private int tokenIdentificador(String palavra){
        if(palavra.length() > 30){
            return 65531;
        }
        
        return 25;
    }
    
    private int tokenNumerico(String palavra){
        Integer numero;
        numero = Integer.parseInt(palavra);
        
        if(numero > 32767){
            return 65534;
        }
        
        return 26;
    }
    
    private int tokenSinais(String palavra){
        int indice;
        switch(palavra){
            case "+": indice = 30; break;
            case "-": indice = 31; break;
            case "*": indice = 32; break;
            case "/": indice = 33; break;
            case "[": indice = 34; break;
            case "]": indice = 35; break;
            case "(": indice = 36; break;
            case ")": indice = 37; break;
            case ":=": indice = 38; break;
            case ":": indice = 39; break;
            case "=": indice = 40; break;
            case ">": indice = 41; break;
            case ">=": indice = 42; break;
            case "<": indice = 43; break;
            case "<=": indice = 44; break;
            case "<>": indice = 45; break;
            case ",": indice = 46; break;
            case ";": indice = 47; break;
            case ".": indice = 49; break;
            case "..": indice = 50; break;
            case "$": indice = 51; break;
            default: indice = 65536; break;
        }
        
        return indice;
    }
    
    private int testeLiteral(String palavra){
        if(palavra.length() < 257){
            return palavra.indexOf("\"");
        }
        return 65533;
    }
    
    private int testeComentario(String palavra){
        return palavra.indexOf("*)");
    }
    
    private boolean espacoVazio(char espaco){
        if(espaco == ' ' || espaco == '\t'){
            return true;
        }
        return false;
    }
    
    public Stack<Integer> getPilhaLexica(){
        return Pilha;
    }
    
    public Stack<Integer> percorrerFita(String fita) throws GeneralException{
        linha = 0;
        auxPalavra = "";
        for(posicao = 0; posicao<fita.length(); posicao++){
            //System.out.println("Posicao:"+posicao);
            //System.out.println("Linha:"+linha);
            //System.out.println("Palavra:"+auxPalavra);
            //System.out.println("Char:"+fita.charAt(posicao));
            //System.out.println("Token:"+token);
            token = -1;
            
            if(fita.charAt(posicao) == '('){
                if(fita.charAt(posicao+1) == '*'){
                    auxPosicao = testeComentario(fita.substring(posicao+2));
                    if(auxPosicao == -1){
                        posicao = fita.length() -1;
                        throw new GeneralException("Comentário não finalizado");
                    }
                    else{
                        posicao += (auxPosicao + 3);
                        token = 0;
                    }
                }
                else{
                    token = 36;
                }
            }
            else if(fita.charAt(posicao) == '"'){
                auxPosicao = testeLiteral(fita.substring(posicao));
                if(auxPosicao == 65533){
                    throw new GeneralException("Literal maior que o limite"+linha);
                }
                else{
                    token = 48;
                    posicao = auxPosicao;
                }
            }
            else if(fita.charAt(posicao) == '\n'){
                linha++;
                continue;
            }
            else if(fita.charAt(posicao) == ' ' || fita.charAt(posicao) == '\t'){
                continue;
            }
            else if(Character.isDigit(fita.charAt(posicao))){
                while(posicao < fita.length()){
                    if(!Character.isDigit(fita.charAt(posicao))){
                        token = tokenNumerico(auxPalavra);
                        if(token == 65534){
                            throw new GeneralException("Numérico maior que o limite:"+linha);
                        }
                        posicao--;
                        auxPalavra = "";
                        break;
                    }
                    else{
                        auxPalavra += fita.charAt(posicao);
                        posicao++;
                    }
                }
            }
            else if(Character.isAlphabetic(fita.charAt(posicao))){
                auxPalavra = "";
                while(posicao < fita.length()){
                    if(Character.isAlphabetic(fita.charAt(posicao)) || 
                            Character.isDigit(fita.charAt(posicao)) || 
                            fita.charAt(posicao) == '_'){
                        auxPalavra += fita.charAt(posicao);
                        posicao++;
                    }
                    else{
                        if(fita.charAt(posicao) == '\n'){
                            linha++;
                        }
                        token = tokenReserved(auxPalavra);
                        if(token == 25){
                            token = tokenIdentificador(auxPalavra);
                            if(token == 65531){
                                throw new GeneralException("Identificador maior que o limite");
                            }
                        }
                        auxPalavra = "";
                        posicao--;
                        break;
                    }
                }
            }
            else{
                while(posicao < fita.length()){
                    if(Character.isAlphabetic(fita.charAt(posicao)) || 
                       Character.isDigit(fita.charAt(posicao)) || 
                       espacoVazio(fita.charAt(posicao))){
                        auxPalavra = "";
                        posicao--;
                        break;
                    }
                    else if(fita.charAt(posicao) == '\n'){
                        auxPalavra = "";
                        linha++;
                        break;
                    }
                    else{
                        auxPalavra += fita.charAt(posicao);
                        posicao++;
                    }
                    token = tokenSinais(auxPalavra);
                    if(token == 65536){
                        //throw new GeneralException("Erro no sinal:"+linha);
                        auxPalavra = "";
                        posicao--;
                    }
                }
            }
            
            if(token == -1){
                throw new GeneralException("Token não encontrado:" + linha);
            }
            else if(token != 0){
                Pilha.push(token);
            }
        }
        
        return Pilha;
    }
    
}
