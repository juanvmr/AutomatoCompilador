/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatoAnalisadorSintatico;

import FileManager.FileManager;
import XMLParsing.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author juanvmr
 */
public class AnalisadorSintatico {
    private Stack<Integer> pilhaSintatica;
    private Stack<Integer> pilhaLexica;
    private Integer sint;
    private Integer lex;
    private int erro;
    
    private File XMLFile;
    private SAXParserFactory saxFactory;
    private SAXParser saxParser;
    private FileManager fileManager;
    
    public AnalisadorSintatico(Stack<Integer> pilhaLexica){
        pilhaSintatica = new Stack<>();
        pilhaSintatica.push(52);
        this.pilhaLexica = pilhaLexica;
        fileManager = new FileManager("tabelaParsing.xml", true);
        erro = 0;
    }
    
    private void empilharParse(){
        Integer derivacao[] = new Integer[15];
        String sintDerivacao,
                dev[];
        int cont = 0,
                pos = 0;
        
        main:while((sintDerivacao = fileManager.fileRead()) != null){
            dev = sintDerivacao.split(" ");
            for(; cont<dev.length; cont++){
                if(dev[cont].contains("codigo=")){
                    if(dev[cont].contains("\"" + pilhaSintatica.peek() + "," + pilhaLexica.peek() + "\"")){
                        cont++;
                        if(dev[cont].contains("derivacao=")){
                            for(String k : dev[cont].substring(dev[cont].indexOf("derivacao=")+11, dev[cont].length()-2).split("|")){
                                if("null".compareTo(k) == 1){
                                    derivacao[pos] = null;
                                }
                                else{
                                    derivacao[pos] = Integer.parseInt(k);
                                }
                                pos++;
                            }
                        }
                        else{
                            System.out.println("Erro derivacao");
                        }
                        break main;
                    }
                }
            }
        }
        
        for(Integer k : derivacao){
            pilhaSintatica.push(k);
        }
    }
    
    /*private void empilharSintaticaParse(Integer[] derivacao){
        for(Integer k : derivacao){
            pilhaSintatica.push(k);
        }
    }*/
    
    private Stack<Integer> inverterPilha(Stack<Integer> pilha){
        Stack<Integer> pilhaInversa = new Stack();
        pilha.forEach((elemento) -> {
            pilhaInversa.push(elemento);
        });
        
        return pilhaInversa;
    }
    
    public void XMLparseTabela(){
        try{
            XMLFile = new File("tabelaParsing.xml");
            saxFactory = SAXParserFactory.newInstance();
            saxParser = saxFactory.newSAXParser();
            TabelaParsingXML tabelaParse = new TabelaParsingXML();
            saxParser.parse(XMLFile, tabelaParse);
        } 
        catch(IOException | ParserConfigurationException | SAXException e){
            for (StackTraceElement s : e.getStackTrace()) {
                System.out.println(s.toString());
            }
        }
    }
    
    public void XMLparseGramatica(){
        try{
            XMLFile = new File("gramatica.xml");
            saxFactory = SAXParserFactory.newInstance();
            saxParser = saxFactory.newSAXParser();
            TabelaParsingXML gramaticaParse = new TabelaParsingXML();
            saxParser.parse(XMLFile, gramaticaParse);
        } 
        catch(IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
    }
    public void analiseSintatica(){
        
        while(!pilhaLexica.empty() && !pilhaSintatica.empty()){
            sint = pilhaSintatica.peek();
            lex = pilhaLexica.peek();
            
            if(sint < 52 || sint == null){
                if(Objects.equals(sint, lex)){
                    pilhaLexica.pop();
                    pilhaSintatica.pop();
                }
                else{
                    erro = 65530;
                    break;
                }
            }
            else{
                empilharParse();
                pilhaSintatica.pop();
            }
        }
    }
}
