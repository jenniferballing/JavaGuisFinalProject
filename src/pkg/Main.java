package pkg;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JenniferBalling on 4/11/14.
 */
public class Main extends JPanel implements ActionListener{
    private JPanel containerPanel, imagePanel, definitionsPanel;
    private JButton currentMortButton, futureMortButton, enterButton, earlyPayoffButton, backToStartButton;
    private JLabel titleLabel, definitionsLabel, principleLabel, rateLabel, termLabel, payoffLabel, toStartLabel;
    private JTextField principleTF, rateTF, termTF;
    private ImageIcon housePic;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;


    public Main(){

    }
    public static void main (String [] args){

    }
    public void openingScreen(){

        //Initialize Variables

        definitionsLabel = new JLabel("Definitions: ");
        principleLabel = new JLabel("Insert definitions here: ");
        rateLabel = new JLabel("Insert definitions here: ");
        termLabel = new JLabel("Insert definitions here: ");

        definitionsPanel = new JPanel();
        definitionsPanel.setBackground(Color.darkGray);
        definitionsPanel.setPreferredSize(new Dimension(100, 500));
        definitionsPanel.add(definitionsLabel);
        definitionsPanel.add(principleLabel);
        definitionsPanel.add(rateLabel);
        definitionsPanel.add(termLabel);

        housePic = new ImageIcon();
        //housePic picture

        imagePanel = new JPanel();
        imagePanel.setBackground(Color.getHSBColor((float)3.2,(float) 8.9, (float) 9.3));
        imagePanel.setPreferredSize(new Dimension(400, 500));
        imagePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //imagePanel.add(housePic);

        containerPanel = new JPanel();
        containerPanel.setBackground(Color.darkGray);
        containerPanel.setVisible(true);
        containerPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));




    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
