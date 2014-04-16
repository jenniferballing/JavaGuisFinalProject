package pkg;


import sun.management.resources.agent_fr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JenniferBalling on 4/11/14.
 */
public class Main extends JFrame implements ActionListener{
    private JPanel containerPanel, imagePanel, definitionsPanel;
    private JButton currentMortButton, futureMortButton, enterButton, earlyPayoffButton, backToStartButton;
    private JLabel titleLabel, definitionsLabel, principleLabel, rateLabel, termLabel, downPayLabel, payoffLabel, toStartLabel, princDir, rateDir, termDir, downPayDir;
    private JTextField principleTF, rateTF, termTF, downPayTF;
    private ImageIcon housePic;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private int term, principle, downPayment;
    private double rate;


    public Main(){

        openingScreen();

        setVisible(true);
        setSize(WIDTH+10, HEIGHT+150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public static void main (String [] args){
        new Main();

    }
    public void openingScreen(){

        //Initialize Variables

        titleLabel= new JLabel("Amortization Schedule");
        titleLabel.setFont(titleLabel.getFont().deriveFont(25f));
        titleLabel.setForeground(Color.white);//(Color.getHSBColor((float) .0867,(float) .137, (float) 1.0));

        principleTF = new JTextField();
        principleTF.setEditable(true);
        rateTF = new JTextField();
        rateTF.setEditable(true);
        downPayTF = new JTextField();
        downPayTF.setEditable(true);
        termTF = new JTextField();
        termTF.setEditable(true);//DO SOME ERROR CHECKING!

        currentMortButton = new JButton("Current Mortgage");
        futureMortButton = new JButton("Future Mortgage");
        enterButton = new JButton("Enter");
        earlyPayoffButton = new JButton("Early Payoff");
        backToStartButton = new JButton("Back to Start");

        payoffLabel = new JLabel();
        toStartLabel = new JLabel();

        definitionsLabel = new JLabel("Definitions: ");
        principleLabel = new JLabel("Insert definitions here: ");
        rateLabel = new JLabel("Insert definitions here: ");
        termLabel = new JLabel("Insert definitions here: ");
        downPayLabel = new JLabel("Insert definitions here: ");

        definitionsPanel = new JPanel();
        definitionsPanel.setBackground(Color.darkGray);
        definitionsPanel.setPreferredSize(new Dimension(125, 350));
        definitionsPanel.add(definitionsLabel);
        definitionsPanel.add(principleLabel);
        definitionsPanel.add(rateLabel);
        definitionsPanel.add(downPayLabel);

        definitionsPanel.add(termLabel);

        princDir = new JLabel("Directions here: ");
        termDir = new JLabel("Directions here: ");
        rateDir = new JLabel("Directions here: ");
        downPayDir = new JLabel("Directions here: ");

        housePic = new ImageIcon();
        //housePic picture

        //Color.HSBtoRGB((float) 0.952, (float) 0.522, (float) 1.000);

        imagePanel = new JPanel();
        imagePanel.setBackground(Color.GRAY);//(Color.getHSBColor((float) 0.000,(float) 0.811, (float) 0.699));
        imagePanel.setPreferredSize(new Dimension(400, 350));
        imagePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //imagePanel.add(housePic);

        containerPanel = new JPanel();
        containerPanel.setBackground(Color.BLACK);//(Color.getHSBColor((float) 0.00,(float) 0.00, (float) 0.565));//(Color.getHSBColor((float) 0.923, (float) 0.607, (float) 0.957));
        containerPanel.setVisible(true);
        containerPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        containerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        containerPanel.add(titleLabel);
        containerPanel.add(imagePanel);
        containerPanel.add(currentMortButton);
        containerPanel.add(futureMortButton);

        futureHandler futureH = new futureHandler();
        currentHandler currentH = new currentHandler();
        rateHandler rateH = new rateHandler();
        termHandler termH = new termHandler();
        principleHandler princeH = new principleHandler();
        downPayHandler downPayH = new downPayHandler();

        futureMortButton.addActionListener(futureH);
        currentMortButton.addActionListener(currentH);
        rateTF.addActionListener(rateH);
        termTF.addActionListener(termH);
        principleTF.addActionListener(princeH);
        downPayTF.addActionListener(downPayH);


        add(containerPanel);

        //update(this.getGraphics());

        revalidate();
        repaint();
    }
    public void afterFutureButton(){

        imagePanel.setPreferredSize(new Dimension(WIDTH-150, HEIGHT-150));
        imagePanel.add(termDir);
        imagePanel.add(termTF);
        imagePanel.add(rateDir);
        imagePanel.add(rateTF);
        imagePanel.add(downPayDir);
        imagePanel.add(downPayTF);
        imagePanel.add(princDir);
        imagePanel.add(principleTF);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLUE);
        titlePanel.setPreferredSize(new Dimension(500, 100));
        titleLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);


        containerPanel.remove(currentMortButton);
        containerPanel.remove(futureMortButton);
        containerPanel.remove(titleLabel);
        containerPanel.remove(imagePanel);

        containerPanel.setLayout(new BorderLayout());
        imagePanel.setLayout(new GridLayout(5, 2));
        definitionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        containerPanel.add(titlePanel, BorderLayout.NORTH);
        containerPanel.add(definitionsPanel, BorderLayout.WEST);
        containerPanel.add(imagePanel, BorderLayout.EAST);


        revalidate();
        repaint();

    }
    public void afterCurrentButton(){

        imagePanel.setPreferredSize(new Dimension(WIDTH-150, HEIGHT-150));
        imagePanel.add(termDir);
        imagePanel.add(termTF);
        imagePanel.add(rateDir);
        imagePanel.add(rateTF);
        imagePanel.add(princDir);
        imagePanel.add(principleTF);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLUE);
        titlePanel.setPreferredSize(new Dimension(500, 100));
        titleLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);


        containerPanel.remove(currentMortButton);
        containerPanel.remove(futureMortButton);
        containerPanel.remove(titleLabel);
        containerPanel.remove(imagePanel);

        containerPanel.setLayout(new BorderLayout());
        imagePanel.setLayout(new GridLayout(5, 2));
        definitionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        containerPanel.add(titlePanel, BorderLayout.NORTH);
        containerPanel.add(definitionsPanel, BorderLayout.WEST);
        containerPanel.add(imagePanel, BorderLayout.EAST);


        revalidate();
        repaint();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public class futureHandler implements ActionListener{
        public void actionPerformed (ActionEvent e){
            afterFutureButton();
        }
    }
    public class currentHandler implements ActionListener{
        public void actionPerformed (ActionEvent e){
            afterCurrentButton();
        }
    }
    public class rateHandler implements ActionListener{
        public void actionPerformed (ActionEvent e){
            rate = Double.parseDouble(rateTF.getText());
            rate= rate/100;

        }
    }
    public class termHandler implements ActionListener{
        public void actionPerformed (ActionEvent e){
            term = Integer.parseInt(termTF.getText());

        }
    }
    public class downPayHandler implements ActionListener{
        public void actionPerformed (ActionEvent e){
        downPayment = Integer.parseInt(downPayTF.getText());
        }
    }
    public class principleHandler implements ActionListener{
        public void actionPerformed (ActionEvent e){
            principle = Integer.parseInt(principleTF.getText());

        }
    }
    public double MonthlyPayment(){
        int n = term*12;
        double payment = ((rate*principle)/(1-Math.pow((1+rate),- n)+1));
        return payment;
    }
    public double InterestPayment(double principal){
        return  principal*rate;
    }
    public double PrincipalPayment (double principal){
        double monthly = MonthlyPayment();
        double interest = InterestPayment(principal);
        return monthly - interest;
    }

    public void PrintSchedule(){

        int [] paymentNum = new int[term*12];
        for(int i=1; i<=term*12; i++){
            paymentNum[i] = i;
        }
        double [] paymentAmount = new double[term*12];
        for(int i=0; i<(term*12); i++){
            paymentAmount[i] = MonthlyPayment();
        }
        double [] PrincipalPayed = new double[term*12];
        double tempP = principle;
        for(int i=0; i<term*12; i++){

            PrincipalPayed[i]=PrincipalPayment(tempP);
            tempP-=MonthlyPayment();
        }
        double [] InterestPayed = new double[term*12];
        tempP = principle;
        for(int i=0; i<term*12; i++){

            InterestPayed[i] = InterestPayment(tempP);
            tempP-=MonthlyPayment();
        }
        double [] RemainingBalance = new double[term*12];

    }
}
