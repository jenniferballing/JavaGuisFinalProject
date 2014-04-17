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
    private JButton currentMortButton, futureMortButton, calcButton3, calcTotals2, calcTotals, earlyPayoffButton, backToStartButton;
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
    private JPanel scrollPanel;


    public Main(){

        SetUp();


        setVisible(true);
        setSize(WIDTH+100, HEIGHT+100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public static void main (String [] args){
        new Main();

    }
   public void SetUp(){

        //Initialize Variables
        titleLabel= new JLabel("Amortization Schedule");

        buttonPanel = new JPanel();

        currentMortButton = new JButton("Current Mortgage");
        futureMortButton = new JButton("Future Mortgage");
        calcButton3 = new JButton("Calculate 3 Years of Payments");
        earlyPayoffButton = new JButton("Early Payoff");
        backToStartButton = new JButton("Back to Start");
        calcTotals = new JButton("Calculate Loan Totals");
        calcTotals2 = new JButton("Calculate Loan Totals");

        payoffLabel = new JLabel();
        toStartLabel = new JLabel();

        definitionsLabel = new JLabel("Definitions: ");
        principleLabel = new JLabel("Insert definitions here: ");
        rateLabel = new JLabel("Insert definitions here: ");
        termLabel = new JLabel("Insert definitions here: ");
        downPayLabel = new JLabel("Insert definitions here: ");

        definitionsPanel = new JPanel();

        princDir = new JLabel("Principal: ");
        termDir = new JLabel("Term: ");
        rateDir = new JLabel("Rate: ");
        downPayDir = new JLabel("Down Payment: ");

        housePic = new ImageIcon();
        //housePic picture

        //Color.HSBtoRGB((float) 0.952, (float) 0.522, (float) 1.000);

        imagePanel = new JPanel();

        containerPanel = new JPanel();

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

                AllPayments(monthlyPayment, r, t, p);

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

       calcTotals2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               int t = Integer.parseInt(termTF.getText());
               double r = Double.parseDouble(rateTF.getText());
               double p = Double.parseDouble(principleTF.getText());

               r = (r/100)/12;

               double partial = Math.pow((1+r), t);
               double monthlyPayment = (r*p*partial)/(partial-1);

               PrintTotals(monthlyPayment, r, t, p);
           }
       });
       earlyPayoffButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

           }
       });
       backToStartButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               containerPanel.removeAll();
               OpeningScreen();
           }
       });

       OpeningScreen();
   }
    public void OpeningScreen (){

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLUE);
        titlePanel.setPreferredSize(new Dimension(500, 75));
        titleLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        titleLabel.setFont(titleLabel.getFont().deriveFont(25f));
        titleLabel.setForeground(Color.white);//(Color.getHSBColor((float) .0867,(float) .137, (float) 1.0));
        titlePanel.add(titleLabel);

        principleTF.setEditable(true);
        rateTF.setEditable(true);
        downPayTF.setEditable(true);
        termTF.setEditable(true);//DO SOME ERROR CHECKING!

        buttonPanel.setBackground(Color.green);
        buttonPanel.setPreferredSize(new Dimension(400, 100));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        imagePanel.setBackground(Color.GRAY);//(Color.getHSBColor((float) 0.000,(float) 0.811, (float) 0.699));
        imagePanel.setPreferredSize(new Dimension(400, 350));
        imagePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //imagePanel.add(housePic);

        buttonPanel.add(currentMortButton);
        buttonPanel.add(futureMortButton);

        containerPanel.setBackground(Color.BLACK);//(Color.getHSBColor((float) 0.00,(float) 0.00, (float) 0.565));//(Color.getHSBColor((float) 0.923, (float) 0.607, (float) 0.957));
        containerPanel.setVisible(true);
        containerPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        containerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        containerPanel.add(imagePanel);
        containerPanel.add(buttonPanel);

        containerPanel.add(titlePanel, BorderLayout.NORTH);
        //containerPanel.add(definitionsPanel, BorderLayout.WEST);
        containerPanel.add(imagePanel, BorderLayout.CENTER);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

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

        containerPanel.remove(currentMortButton);
        containerPanel.remove(futureMortButton);

        definitionsPanel.setBackground(Color.darkGray);
        definitionsPanel.setPreferredSize(new Dimension(125, 350));
        definitionsPanel.add(definitionsLabel);
        definitionsPanel.add(principleLabel);
        definitionsPanel.add(rateLabel);
        definitionsPanel.add(downPayLabel);
        definitionsPanel.add(termLabel);

        containerPanel.setLayout(new BorderLayout());
        imagePanel.setLayout(new GridLayout(5, 2));
        definitionsPanel.setPreferredSize(new Dimension(100, 200));
        definitionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        containerPanel.add(titlePanel, BorderLayout.NORTH);
        containerPanel.add(definitionsPanel, BorderLayout.WEST);
        containerPanel.add(imagePanel, BorderLayout.EAST);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(containerPanel);

        revalidate();
        repaint();

    }
    public void afterCurrentButton(){

        buttonPanel.removeAll();
        buttonPanel.add(calcTotals2);
        buttonPanel.add(calcButton3);

        imagePanel.setPreferredSize(new Dimension(WIDTH-150, HEIGHT-150));
        imagePanel.add(termDir);
        termDir.setText("Months Remaining: ");
        imagePanel.add(termTF);
        imagePanel.add(rateDir);
        imagePanel.add(rateTF);
        princDir.setText("Remaining Principal: ");
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
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);


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
    public void AllPayments(double Monthly, double rate, int t, double p){

        containerPanel.remove(imagePanel);
        containerPanel.remove(calcButton3);
        containerPanel.remove(definitionsPanel);

        earlyPayoffButton.setText("Run Numbers for Early Payoff");
        backToStartButton.setText("Back to Start");
        buttonPanel.removeAll();
        buttonPanel.add(earlyPayoffButton);
        buttonPanel.add(backToStartButton);

        //CREATE ARRAYS OF INFO

        paymentNum = new int[t];//[term*12];
        for(int i=0; i<t; i++){
            paymentNum[i] = i+1;
        }

        paymentAmount = new double[t];//[term*12];
        for(int i=0; i<t; i++){
            String m = String.format("%.2f", Monthly);
            double monthly = Double.parseDouble(m);
            paymentAmount[i] = monthly;
        }

        principalPayed = new double[t];
        remainingBalance = new double[t];
        interestPayed = new double[t];
        double tempP = p;
        for(int i=0; i<t; i++){
            double payed =  tempP*rate;
            double prince = Monthly-payed;
            String m = String.format("%2.2f", prince);
            double num = Double.parseDouble(m);
            principalPayed[i] = num;

            double re = tempP - num;
            String n = String.format("%2.2f", re);
            double remain = Double.parseDouble(n);
            remainingBalance[i] = remain;

            double in = Monthly - num;
            String inter = String.format("%2.2f", in);
            double interest = Double.parseDouble(inter);
            interestPayed [i] = interest;

            tempP-=num;
        }

        //CREATE NEW GRIDDED PANEL

        JLabel numL = new JLabel("Payment Number: ");
        JLabel payL = new JLabel("Payment Amount: ");
        JLabel prinL = new JLabel("Principal Amount: ");
        JLabel intL = new JLabel("Interest Amount: ");
        JLabel remainL = new JLabel("Remaining Balance: ");

        gridPanel = new JPanel();
        containerPanel.setPreferredSize(new Dimension(800, 800));
        gridPanel.setPreferredSize(new Dimension(WIDTH+200, HEIGHT+4000));
        gridPanel.setLayout(new GridLayout(t+1, 5));

        gridPanel.add(numL);
        gridPanel.add(payL);
        gridPanel.add(prinL);
        gridPanel.add(intL);
        gridPanel.add(remainL);

        for(int j=0; j<t; j++){
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
        titleLabel.setText("All Payments: ");

        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BorderLayout());
        //getContentPane().add(scrollPanel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(gridPanel);
        scrollPanel.add(scrollPane, BorderLayout.CENTER);

        containerPanel.add(scrollPanel);
        //containerPanel.add(scroll, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
