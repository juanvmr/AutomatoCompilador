/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author juanvmr
 */
public class FileManager{
    File file;
    FileWriter fileWriter;
    FileReader fileReader;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    
    public FileManager(String filePath, boolean append){
        if(filePath.isEmpty()){
            file = new File("notExist.txt");
        }
        else{
            file = new File(filePath);
        }
        System.out.println("File init");
        
        try{
            fileWriter = new FileWriter(file, append);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void fileWrite(String element){
        try{
            if(element.isEmpty()){
                bufferedWriter.write("EMPTYSTRING");
            }
            else{
                bufferedWriter.write(element);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String fileRead(){
        try{
            return bufferedReader.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void closeFile(){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(fileReader != null){
                fileReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(fileWriter != null){
               fileWriter.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
