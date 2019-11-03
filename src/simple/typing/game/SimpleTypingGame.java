package simple.typing.game;
//importing library of Timer and TimerTask
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.event.*;

public class SimpleTypingGame implements ActionListener, KeyListener {
    Random rand = new Random();
     Timer myTimer;
     
    //needed variables
    int secondPassed = 0;
    int score = 0;
    int randomWord;
   
    String [] words = {"hello", "my ", "name", "is", "nguyen", "van", "tu", "nice", "to", "see"};
    JFrame f = new JFrame("TypingGameNVT");
//    Thread t;
    Thread t = new Thread();
    JButton but1 = new JButton("Start");
    JButton but2 = new JButton("Stop");
    JButton but3 = new JButton("Pause/Resume");
    
    JTextField jf1 = new JTextField();
    JTextField jf2 = new JTextField("");
   JLabel lbl1 = new JLabel();
    JLabel lbl3 = new JLabel("" + score);
    JLabel lbl4 = new JLabel("score");
    JLabel lbl5 = new JLabel("Time");
    public void gameForm()
    {
        f.setLayout(null);
        f.setSize(700, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
       
        
        but1.setBounds(50, 400, 100, 50);
         but1.setBackground(Color.yellow);
        but2.setBounds(400,400,100,50);
       but2.setBackground(Color.blue);
        but3.setBounds(200,400,150,50);
         but3.setBackground(Color.red);
        lbl1.setBounds(250, 150, 100, 50);
        jf2.setBounds(100, 150, 100, 50);
        lbl3.setBounds(250, 200, 100, 30);
        jf1.setBounds(100, 200, 100, 30);
        lbl4.setBounds(250,180,100,30);
        lbl5.setBounds(250,140,100,30);
        

        f.add(but1);
        f.add(but2);
        f.add(but3);
        f.add(lbl1);
        f.add(jf2);
        f.add(lbl3);
        f.add(jf1);
        f.add(lbl4);
        f.add(lbl5);
        
        
        but1.addActionListener(this);
        but2.addActionListener(this);
        but3.addActionListener(this);
        jf1.addKeyListener(this);
    }
  
    public void reset()
    {
        lbl3.setText("" + score);
        randomWord = rand.nextInt(words.length-1);
        jf2.setText(words[randomWord]);
        jf1.setText("");     
    }
    public void time(){
        myTimer = new Timer();
     
         TimerTask task = new TimerTask()
            {
                
                public void run()
                {
                    secondPassed++;
                    lbl1.setText("" + secondPassed);                  
                }
            };
            myTimer.scheduleAtFixedRate(task, 1000, 1000);
            task.run();
    }
    public void actionPerformed(ActionEvent e)
    {   
         if (e.getSource()==but2)
        {
           
            myTimer.cancel();
            jf1.setEnabled(false);
            jf2.setEnabled(false);
            lbl3.setText("0");
          lbl1.setText("0");
//           t.stop();
        }
        
         else if (e.getSource()==but3)
        {
            
            myTimer.cancel();
            jf1.setEnabled(false);
            jf2.setEnabled(false);
            
          t.suspend();
        }
       else if (e.getSource()==but1)
        {
            jf1.setEnabled(true);
            jf2.setEnabled(true);
           
            secondPassed = 0;
            score = 0;
            reset();
            jf1.requestFocus();
            time();  
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        
    }
    public void keyReleased(KeyEvent e)
    {
        if (e.getSource()==jf1)
        {
            if(jf1.getText().equals(jf2.getText()))
            {
                score++;
                reset();
            }
        }
    }
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    public static void main(String[] args) {
        new SimpleTypingGame().gameForm();
    }
}
