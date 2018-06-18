/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatoAnalisadorSintatico;

/**
 *
 * @author juanvmr
 */
public class TuplaTabela {
    
    public int[] cross;
    public int[] tupla;
    
    public TuplaTabela(int[] cross, int[] tupla){
        this.cross = cross;
        this.tupla = tupla;
    }
    
    public int[] getCross(){
        return this.cross;
    }
    
    public int[] getTupla(){
        return this.tupla;
    }
}
