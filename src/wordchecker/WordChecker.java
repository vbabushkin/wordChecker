/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordchecker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;


/**
 *
 * @author Wild
 */
public class WordChecker extends JFrame{
    private JTextField jtfEnglish = new JTextField();
    private JTextField jtfRussian = new JTextField();
    private JLabel jtfResult = new JLabel();
    private JButton jbtCheck = new JButton("Check");
    
                   ArrayList<String> englishSet = new ArrayList<String>();
                   File engWords = new File("./words.txt");
                   
                   
    public int index ;        
                   ArrayList<String> russianSet = new ArrayList<>();
                   File rusWords = new File("./translations.txt");
                   
                      
               
    public WordChecker(){
        try {
            JPanel p1 = new JPanel(new GridLayout(3,2));
            p1.add(new JLabel("Current word: "));
            p1.add(jtfEnglish);
            p1.add(new JLabel("Enter translation: "));
            p1.add(jtfRussian);
            p1.add(new JLabel("Your suggestion is "));
            p1.add(jtfResult);
            p1.setBorder(new TitledBorder("Check your vocabulary"));
          
            JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            p2.add(jbtCheck);
          
            add(p1,BorderLayout.CENTER);
            add(p2,BorderLayout.SOUTH);
            
            Scanner input= new Scanner(engWords);
                       int i=0;
                       while (input.hasNext()){
                           String s1=input.nextLine();
                           englishSet.add(i, s1);
                           i++;
                       }
            input.close();
            Scanner input1= new Scanner(rusWords);
                       i=0;
                       while (input1.hasNext()){
                            String s2=input1.nextLine();
                            russianSet.add(i, s2);
                            i++;
                       }
            input1.close();
            
         restart();  
         jbtCheck.addActionListener(new ButtonListener());
         
         jtfRussian.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                //while(e.getKeyCode() != KeyEvent.VK_ENTER){
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                word currentWord = new word(englishSet.get(index),russianSet.get(index));
                //System.out.println(text);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                
                    //System.out.println("Pressed");
                    if(currentWord.checkWord(text))
                   {
                       jtfResult.setText("correct");
                       jtfRussian.setText("");
                       restart();
                   }
                   else
                   {
                       jtfResult.setText("incorrect");
                       jtfRussian.setText("");
                   }
                
            }
 
            public void keyTyped(KeyEvent e) {
                // TODO: Do something for the keyTyped event
            }
 
            public void keyPressed(KeyEvent e) {
                // TODO: Do something for the keyPressed event
            }
        });    
         
         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void restart(){
            index = (int)(Math.random()*englishSet.size());           
            jtfEnglish.setText(englishSet.get(index));
            
        }
    
    private class ButtonListener implements ActionListener{
           @Override
           public void actionPerformed(ActionEvent e){
                   String russianWord = jtfRussian.getText();
                   //System.out.println(russianWord);
                   word currentWord = new word(englishSet.get(index),russianSet.get(index));
                   if(currentWord.checkWord(russianWord))
                   {
                       jtfResult.setText("correct");
                       jtfRussian.setText("");
                       restart();
                   }
                   else
                   {
                       jtfResult.setText("incorrect");
                       jtfRussian.setText("");
                   }
           }
       }       
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
         WordChecker frame = new WordChecker();
         frame.pack();
         frame.setTitle("WordChecker");
         frame.setLocationRelativeTo(null); // Center the frame
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
    }
}
