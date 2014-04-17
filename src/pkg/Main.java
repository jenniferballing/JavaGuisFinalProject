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
    private JPanel buttonPanel, containerPanel, imagePanel, definitionsPanel, titlePanel, gridPanel;
    private JButton currentMortButton, futureMortButton, calcButton3, calcTotals, earlyPayoffButton, backToStartButton;
    private JLabel titleLabel, definitionsLabel, principleLabel, rateLabel, termLabel, downPayLabel, payoffLabel, toStartLabel, princDir, rateDir, termDir, downPayDir;
    private ImageIcon housePic;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    int [] paymentNum;
    double [] paymentAmount;
    double [] principalPayed;
    double [] interestPayed;
    double [] remainingBalance;
    final JTextField principleTF = new JTextField();
    final JTextField rateTF = new JTextField();
    final JTextField downPayTF = new JTextField();
    final JTextField termTF = new JTextField();



    public Main(){

        openingScreen();


        setVisible(true);
        setSize(WIDTH+100, HEIGHT+150);
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


        principleTF.setEditable(true);

        rateTF.setEditable(true);

        downPayTF.setEditable(true);
        termTF.setEditable(true);//DO SOME ERROR CHECKING!

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.green);
        buttonPanel.setPreferredSize(new Dimension(400, 100));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        currentMortButton = new JButton("Current Mortgage");
        futureMortButton = new JButton("Future Mortgage");
        calcButton3 = new JButton("Calculate 3 Years of Payments");
        earlyPayoffButton = new JButton("Early Payoff");
        backToStartButton = new JButton("Back to Start");
        calcTotals = new JButton("Calculate Loan Totals");

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

        princDir = new JLabel("Principal: ");
        termDir = new JLabel("Term: ");
        rateDir = new JLabel("Rate: ");
        downPayDir = new JLabel("Down Payment: ");

        housePic = new ImageIcon();
        //housePic picture

        //Color.HSBtoRGB((float) 0.952, (float) 0.522, (float) 1.000);

        imagePanel = new JPanel();
        imagePanel.setBackground(Color.GRAY);//(Color.getHSBColor((float) 0.000,(float) 0.811, (float) 0.699));
        imagePanel.setPreferredSize(new Dimension(400, 350));
        imagePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //imagePanel.add(housePic);

        buttonPanel.add(currentMortButton);
        buttonPanel.add(futureMortButton);

        containerPanel = new JPanel();
        containerPanel.setBackground(Color.BLACK);//(Color.getHSBColor((float) 0.00,(float) 0.00, (float) 0.565));//(Color.getHSBColor((float) 0.923, (float) 0.607, (float) 0.957));
        containerPanel.setVisible(true);
        containerPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        containerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        containerPanel.add(titleLabel);
        containerPanel.add(imagePanel);
        containerPanel.add(buttonPanel);


        futureHandler futureH = new futureHandler();
        currentHandler currentH = new currentHandler();

        futureMortButton.addActionListener(futureH);
        currentMortButton.addActionListener(currentH);

        calcButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int t = Integer.parseInt(termTF.getText());
                double r = Double.parseDouble(rateTF.getText());
                double p = Double.parseDouble(principleTF.getText());
                double d = Double.parseDouble(downPayTF.getText());

                t = t*12;
                r = (r/100)/12;
                p = p-d;

                double partial = Math.pow((1+r), t);
                double monthlyPayment = (r*p*partial)/(partial-1);

                PrintFirstThreeYearSchedule(monthlyPayment, r, t, p);

            }
        });
       calcTotals.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               int t = Integer.parseInt(termTF.getText());
               double r = Double.parseDouble(rateTF.getText());
               double p = Double.parseDouble(principleTF.getText());
               double d = Double.parseDouble(downPayTF.getText());

               t = t*12;
               r = (r/100)/12;
               p = p-d;

               double partial = Math.pow((1+r), t);
               double monthlyPayment = (r*p*partial)/(partial-1);

               PrintTotals(monthlyPayment, r, t, p);
           }
       });


        add(containerPanel);

        update(this.getGraphics());

        revalidate();
        repaint();
   }
    public void afterFutureButton(){

        buttonPanel.removeAll();
        buttonPanel.add(calcTotals);
        buttonPanel.add(calcButton3);

        imagePanel.setPreferredSize(new Dimension(WIDTH-200, HEIGHT-300));
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
        titlePanel.setPreferredSize(new Dimension(500, 75));
        titleLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);


        containerPanel.remove(currentMortButton);
        containerPanel.remove(futureMortButton);
        containerPanel.remove(titleLabel);
        containerPanel.remove(imagePanel);

        containerPanel.setLayout(new BorderLayout());
        imagePanel.setLayout(new GridLayout(5, 2));
        definitionsPanel.setPreferredSize(new Dimension(100, 200));
        definitionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        containerPanel.add(titlePanel, BorderLayout.NORTH);
        containerPanel.add(definitionsPanel, BorderLayout.WEST);
        containerPanel.add(imagePanel, BorderLayout.EAST);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

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

        titlePanel = new JPanel();
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
    public void PrintTotals(double Monthly, double rate, int t, double p){
        containerPanel.remove(imagePanel);
        containerPanel.remove(calcButton3);
        containerPanel.remove(definitionsPanel);

        String m = String.format("%.2f", (double)t);
        JLabel numL = new JLabel("Total Number of Payments: ");
        JLabel num = new JLabel(m);

        double dollarsPaid = Monthly*t;
        String d = String.format("%.2f", dollarsPaid);
        JLabel payL = new JLabel("Total Dollars Paid: ");
        JLabel doll = new JLabel(d);

        double storeInterest = dollarsPaid - p;
        JLabel intL = new JLabel("Total Interest Paid: ");
        String i = String.format("%.2f", storeInterest);
        JLabel inT = new JLabel(i);

        gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(400, 400));
        gridPanel.setBackground(Color.white);
        gridPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        gridPanel.add(numL);
        gridPanel.add(num);
        gridPanel.add(payL);
        gridPanel.add(doll);
        gridPanel.add(intL);
        gridPanel.add(inT);

        containerPanel.add(gridPanel);

        revalidate();
        repaint();
    }
    public void PrintFirstThreeYearSchedule(double Monthly, double rate, int t, double p){

        containerPanel.remove(imagePanel);
        containerPanel.remove(calcButton3);
        containerPanel.remove(definitionsPanel);
        int termNum = 36;

        //CREATE ARRAYS OF INFO

        paymentNum = new int[termNum];//[term*12];
        for(int i=0; i<termNum; i++){
            paymentNum[i] = i+1;
        }

        paymentAmount = new double[termNum];//[term*12];
        for(int i=0; i<termNum; i++){
            String m = String.format("%.2f", Monthly);
            double monthly = Double.parseDouble(m);
            paymentAmount[i] = monthly;
        }

        principalPayed = new double[termNum];//[term*12];
        double tempP = p;
        for(int i=0; i<termNum; i++){
            double payed =  tempP*rate;
            double prince = Monthly-payed;
            String m = String.format("%2.2f", prince);
            double num = Double.parseDouble(m);
            principalPayed[i] = num;
            tempP-=Monthly;
        }
        interestPayed = new double[termNum];//[term*12];
        tempP = p;
        for(int i=0; i<termNum; i++){
            double payed =  tempP*rate;
            String m = String.format("%2.2f", payed);
            double num = Double.parseDouble(m);
            interestPayed[i] = num;
            tempP-=Monthly;
        }
        remainingBalance = new double[termNum];//[term*12];
        tempP = p;
        for(int i=0; i<termNum; i++){
            String m = String.format("%2.2f", tempP);
            double num = Double.parseDouble(m);
            remainingBalance[i] = num;
            tempP-= Monthly;
        }

        //CREATE NEW GRIDDED PANEL

        JLabel numL = new JLabel("Payment Number: ");
        JLabel payL = new JLabel("Payment Amount: ");
        JLabel prinL = new JLabel("Principal Amount: ");
        JLabel intL = new JLabel("Interest Amount: ");
        JLabel remainL = new JLabel("Remaining Balance: ");

        gridPanel = new JPanel();
        containerPanel.setPreferredSize(new Dimension(800, 800));
        gridPanel.setPreferredSize(new Dimension(WIDTH+200, HEIGHT));
        gridPanel.setLayout(new GridLayout(termNum+1, 5));

        gridPanel.add(numL);
        gridPanel.add(payL);
        gridPanel.add(prinL);
        gridPanel.add(intL);
        gridPanel.add(remainL);

        for(int j=0; j<termNum; j++){
            //Num
            JLabel label = new JLabel();
            label.setText(Integer.toString(paymentNum[j]));
            gridPanel.add(label);

            //Payment amount
            JLabel pLabel = new JLabel();
            pLabel.setText(Double.toString(paymentAmount[j]));
            gridPanel.add(pLabel);

            //Principal amount
            JLabel prLabel = new JLabel();
            prLabel.setText(Double.toString(principalPayed[j]));
            gridPanel.add(prLabel);

            //Interest amount
            JLabel iLabel = new JLabel();
            iLabel.setText(Double.toString(interestPayed[j]));
            gridPanel.add(iLabel);

            //Remaining amount
            JLabel rLabel = new JLabel();
            rLabel.setText(Double.toString(remainingBalance[j]));
            gridPanel.add(rLabel);

        }
        titleLabel.setText("First Three Years of Payments: ");
        containerPanel.add(gridPanel);
        revalidate();
        repaint();
    }
}
