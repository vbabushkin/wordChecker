/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordchecker;

/**
 *
 * @author Wild
 */
public class word {
    private String engWord;
    private String rusWord;
    word(){
        this.engWord="";
        this.rusWord="";
    }
    word(String engWord,String rusWord){
        this.engWord=engWord;
        this.rusWord=rusWord;
    }
    public String getWord(){
        return engWord+" "+rusWord;
    }
    public void setWord(String engWord,String rusWord){
        this.engWord=engWord;
        this.rusWord=rusWord;
    }
    public boolean checkWord(String word){
        return rusWord.equals(word);
    }
    
}
