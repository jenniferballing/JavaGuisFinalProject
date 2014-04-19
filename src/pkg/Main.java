package pkg;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by JenniferBalling on 4/11/14.
 */
public class Main extends JFrame implements ActionListener{
    private JPanel buttonPanel, gridP, imageP, containerPanel, imagePanel, definitionsPanel, titlePanel, gridPanel, scrollPanel;
    private JButton currentMortButton, futureMortButton, calcButtonF, calcButtonC, calcTotalsC, calcTotalsF, earlyPayoffButton, backToStartButton, calcButton;
    private JLabel titleLabel, houseLabel, disclaimerLabel, definitionsLabel, principleLabel, rateLabel, termLabel, downPayLabel, princDir, rateDir, termDir, downPayDir;
    private ImageIcon housePic;
    private static final int WIDTH = 700, HEIGHT = 600;
    int [] paymentNum;
    double [] paymentAmount, principalPayed, interestPayed, remainingBalance ;
    private JTextField monTF;
    private TestTexture containerPanelT, titlePT, gridPT;
    final JTextField principleTF = new JTextField();
    final JTextField rateTF = new JTextField();
    final JTextField downPayTF = new JTextField();
    final JTextField termTF = new JTextField();

    public Main(){

        SetUp();
        pack();
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main (String [] args){
        new Main();
    }
   public void SetUp(){

        //Initialize Variables
        currentMortButton = new JButton("Current Mortgage");
        futureMortButton = new JButton("Future Mortgage");
        calcButtonF = new JButton("Calculate All Payments");
        calcButtonC = new JButton("Calculate All Payments");
        earlyPayoffButton = new JButton("Early Payoff");
        backToStartButton = new JButton("Back to Start");
        calcTotalsF = new JButton("Calculate Loan Totals");
        calcTotalsC = new JButton("Calculate Loan Totals");
        calcButton = new JButton("Calculate New Totals");
        futureHandler futureH = new futureHandler();

        futureMortButton.addActionListener(futureH);

        definitionsLabel = new JLabel("Definitions: ");
        principleLabel = new JLabel("Insert definitions here: ");
        rateLabel = new JLabel("Insert definitions here: ");
        termLabel = new JLabel("Insert definitions here: ");
        downPayLabel = new JLabel("Insert definitions here: ");
        titleLabel= new JLabel("Amortization Schedule");
        princDir = new JLabel("Principal: ");
        termDir = new JLabel("Term: ");
        rateDir = new JLabel("Rate: ");
        downPayDir = new JLabel("Down Payment: ");
        disclaimerLabel = new JLabel("* The information provided here is pertinent only to fixed rate homes loans. Interests-only, ARM, and the doubly irresponsible interest only - ARM will not be considered here.");

        definitionsPanel = new JPanel();
        imagePanel = new JPanel();
        containerPanel = new JPanel();
        buttonPanel = new JPanel();
        titlePanel = new JPanel();

        calcButtonF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int t = Integer.parseInt(termTF.getText());
                double r = Double.parseDouble(rateTF.getText());
                double p = Double.parseDouble(principleTF.getText());
                double d = Double.parseDouble(downPayTF.getText());

                t = t * 12;
                r = (r / 100) / 12;
                p = p - d;

                double partial = Math.pow((1 + r), t);
                double monthlyPayment = (r * p * partial) / (partial - 1);

                AllPayments(monthlyPayment, r, t, p);

            }
        });

       calcTotalsF.addActionListener(new ActionListener() {
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


       earlyPayoffButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               EarlyPayoff();
           }
       });
       backToStartButton.addActionListener(new ActionListener () {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);

           }
       });
       calcButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               double userMonthlyIncrease;
               if(monTF.getText() != null){
                   userMonthlyIncrease = Double.parseDouble(monTF.getText());
               }else{
                   userMonthlyIncrease = 0;
               }

               double p = Double.parseDouble(principleTF.getText());
               double r = Double.parseDouble(rateTF.getText());
               double d = Double.parseDouble(downPayTF.getText());
               double t = Integer.parseInt(termTF.getText());

               r = (r/100)/12;
               t*=12;
               p-=d;
               double partial = Math.pow((1+r), t);
               double Monthly = (r*p*partial)/(partial-1);


               String m = String.format("%.2f", (double)t);
               JLabel numL = new JLabel("Total Number of Payments: ");
               JLabel num = new JLabel(m);

               double dollarsPaid = Monthly*t;
               String dollar = String.format("%.2f", dollarsPaid);
               JLabel payL = new JLabel("Total Dollars Paid: ");
               JLabel doll = new JLabel(dollar);

               double storeInterest = dollarsPaid - p;
               JLabel intL = new JLabel("Total Interest Paid: ");
               String i = String.format("%.2f", storeInterest);
               JLabel inT = new JLabel(i);

               double newMonthly = Monthly+userMonthlyIncrease;
               double tempP = p;
               double interest, prince,totalI=0;
               int paymentCount=0;
               while(tempP>0){
                   interest = tempP*r;
                   totalI+=interest;
                   prince = Monthly-interest;
                   prince += userMonthlyIncrease;
                   paymentCount+=1;
                   tempP-=prince;
               }

               double totalPaid = p+totalI;

               String tp = String.format("%2.2f", totalPaid );
               String inter = String.format("%2.2f", totalI);
               String newMonth = String.format("%2.2f", newMonthly);
               String paymentNum = String.format("%2.2f", (double)paymentCount);

               JLabel newTotals = new JLabel("New Totals");
               JLabel newPayment = new JLabel("New Monthly Payment: " + newMonth);
               JLabel newTotalDoll = new JLabel("New Total Dollars Paid: "+tp);
               JLabel newInterest = new JLabel("New Total Interest Paid: " + inter);
               JLabel newPayNum = new JLabel("New Total Payments Made: " + paymentNum);

               imagePanel.add(newTotals);
               imagePanel.add(newPayment);
               imagePanel.add(newPayNum);
               imagePanel.add(newInterest);
               imagePanel.add(newTotalDoll);

               buttonPanel.removeAll();
               buttonPanel.add(backToStartButton);

               revalidate();
               repaint();
           }
       });

       OpeningScreen();
   }
    public void OpeningScreen (){

        titlePanel.setBackground(Color.BLACK);
        titlePanel.setPreferredSize(new Dimension(380, 45));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);
        titlePanel.add(titleLabel);

        titleLabel.setText("Amortization Schedule");
        titleLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titleLabel.setFont(titleLabel.getFont().deriveFont(32f));
        titleLabel.setForeground(Color.getHSBColor((float) .0867,(float) .137, (float) 1.0));

        principleTF.setEditable(true);
        principleTF.setText(null);
        rateTF.setEditable(true);
        rateTF.setText(null);
        downPayTF.setEditable(true);
        downPayTF.setText(null);
        termTF.setEditable(true);
        termTF.setText(null);


        buttonPanel.setBackground(Color.black);
        buttonPanel.setPreferredSize(new Dimension(110, 45));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        futureMortButton.setText("Start");
        futureMortButton.setPreferredSize(new Dimension(100, 35));

        buttonPanel.add(futureMortButton);


        housePic = new ImageIcon("house2.png");
        houseLabel = new JLabel(housePic);
        houseLabel.setIcon(housePic);
        imageP = new JPanel();
        imageP.setBackground(Color.BLACK);
        imageP.setPreferredSize(new Dimension(615, 410));
        imageP.setLayout(new FlowLayout(FlowLayout.CENTER));
        imageP.add(houseLabel);

        containerPanelT = new TestTexture("brownBackG.png");
        containerPanelT.setVisible(true);
        containerPanelT.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        containerPanelT.setLayout(new FlowLayout());
        containerPanelT.add(titlePanel, BorderLayout.NORTH);
        containerPanelT.add(imageP, BorderLayout.CENTER);
        containerPanelT.add(buttonPanel, BorderLayout.SOUTH);

        add(containerPanelT);
        update(this.getGraphics());
        revalidate();
        repaint();
    }
    public void afterFutureButton(){

        containerPanelT.removeAll();//(imageP);
        titlePanel.setBackground(Color.getHSBColor((float) .0867,(float) .137, (float) 1.0));//(Color.getHSBColor((float) 0.0906,(float) 0.235, (float) 0.912));
//        (Color.getHSBColor((float) .0867, (float) .137, (float) 1.0));
        titlePanel.setPreferredSize(new Dimension(350, 55));
        titleLabel.setForeground(Color.BLACK);
        buttonPanel.removeAll();
        buttonPanel.setBackground(Color.getHSBColor((float) .0867, (float) .137, (float) 1.0));
        buttonPanel.setPreferredSize(new Dimension(100, 50));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(calcTotalsF);
        buttonPanel.add(calcButtonF);
        buttonPanel.add(disclaimerLabel);//, BorderLayout.SOUTH);

        gridPT = new TestTexture("brownBackG.png");
        gridPT.setVisible(true);
        gridPT.setPreferredSize(new Dimension(300, 300));
        gridPT.setLayout(new GridLayout(10, 2));//FlowLayout(FlowLayout.CENTER));
        //gridP.setBackground(Color.getHSBColor((float) 0.0906,(float) 0.235, (float) 0.912));
//        (Color.getHSBColor((float) 0.931,(float) 0.057, (float) 0.970));//(Color.getHSBColor((float) 0.0431,(float) 0.215, (float) 0.390));

        gridP = new JPanel();
        gridPT.setBackground(Color.BLACK);
        //gridP.add(gridPT);
        JLabel space = new JLabel(" ");
        JLabel space1 = new JLabel(" ");
        JLabel space2 = new JLabel(" ");
        JLabel space3 = new JLabel(" ");
        JLabel space4 = new JLabel(" ");
        JLabel space5 = new JLabel(" ");
        JLabel space6 = new JLabel(" ");
        JLabel space7 = new JLabel(" ");
        JLabel space8 = new JLabel(" ");
        JLabel space9 = new JLabel(" ");



        termTF.setPreferredSize(new Dimension(80, 25));
        rateTF.setPreferredSize(new Dimension(80, 25));
        principleTF.setPreferredSize(new Dimension(80, 25));
        downPayTF.setPreferredSize(new Dimension(80, 25));

        termDir.setFont(termDir.getFont().deriveFont(15f));
        termDir.setForeground(Color.WHITE);
        rateDir.setFont(rateDir.getFont().deriveFont(15f));
        rateDir.setForeground(Color.WHITE);
        princDir.setFont(princDir.getFont().deriveFont(15f));
        princDir.setForeground(Color.WHITE);
        downPayDir.setFont(downPayDir.getFont().deriveFont(15f));
        downPayDir.setForeground(Color.WHITE);

        gridPT.add(space);
        gridPT.add(space1);
        gridPT.add(termDir);
        gridPT.add(termTF);
        gridPT.add(space2);
        gridPT.add(space3);
        gridPT.add(rateDir);
        gridPT.add(rateTF);
        gridPT.add(space4);
        gridPT.add(space5);
        gridPT.add(downPayDir);
        gridPT.add(downPayTF);
        gridPT.add(space6);
        gridPT.add(space7);
        gridPT.add(princDir);
        gridPT.add(principleTF);
        gridPT.add(space8);
        gridPT.add(space9);

        termLabel.setText("TERM- The period of time which covers the life of ");
        JLabel termLabel1 = new JLabel("the loan from inception to pay-off.");

        principleLabel.setText("PRINCIPAL – The base amount borrowed from the ");
        JLabel principleLabel1 = new JLabel("lender, excluding interest.");
        //principleLabel.setPreferredSize(new Dimension(350, 50));
        rateLabel.setText("INTEREST RATE – The rate of interest a lender ");
        JLabel rateLabel1 = new JLabel("receives for allowing the borrower to use money.");
        //rateLabel.setPreferredSize(new Dimension(350, 50));

        downPayLabel.setText("DOWN PAYMENT – The percentage of your home's ");
        JLabel dp1 = new JLabel("purchase price that is paid at closing. ");
        //downPayLabel.setPreferredSize(new Dimension(350, 50));
        definitionsPanel.setBackground(Color.getHSBColor((float) 0.575, (float) 0.452, (float) 1.000));

//        ackground(Color.getHSBColor((float) 0.0621,(float) 0.200, (float) 0.940));
        definitionsPanel.setPreferredSize(new Dimension(300, 100));
        definitionsPanel.setLayout(new GridLayout(12, 1));//FlowLayout.LEFT));//GridLayout(5,1));
        definitionsLabel.setText("DEFINITIONS");
        definitionsLabel.setFont(definitionsLabel.getFont().deriveFont(15f));
        definitionsPanel.add(definitionsLabel);
        definitionsPanel.add(principleLabel);
        definitionsPanel.add(principleLabel1);
        definitionsPanel.add(rateLabel);
        definitionsPanel.add(rateLabel1);
        definitionsPanel.add(downPayLabel);
        definitionsPanel.add(dp1);
        definitionsPanel.add(termLabel);
        definitionsPanel.add(termLabel1);
        //definitionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        disclaimerLabel.setFont(disclaimerLabel.getFont().deriveFont(10f));
        titleLabel.setLayout(new FlowLayout(FlowLayout.CENTER));

        containerPanelT.setLayout(new BorderLayout());
        containerPanelT.add(titlePanel, BorderLayout.NORTH);
        containerPanelT.add(definitionsPanel, BorderLayout.WEST);
        containerPanelT.add(gridPT,BorderLayout.EAST);
        containerPanelT.add(buttonPanel, BorderLayout.SOUTH);
        //containerPanelT.add(disclaimerLabel, BorderLayout.SOUTH);

        add(containerPanelT);
        update(this.getGraphics());
        revalidate();
        repaint();
    }
    public void EarlyPayoff(){
        containerPanelT.removeAll();

        titleLabel.setText("Early Payoff Options");
        titlePanel.removeAll();

        titlePT = new TestTexture("lightMarble.png");
        //titlePanel.setBackground(Color.getHSBColor((float) 0.0431, (float) 0.325, (float) 0.720));
        //titlePanel.setPreferredSize(new Dimension(385, 55));
        //titlePanel.add(titleLabel);
        titlePT.add(titleLabel);

        JLabel currentTotals = new JLabel("Current Totals");
        double p = Double.parseDouble(principleTF.getText());
        double r = Double.parseDouble(rateTF.getText());
        double d = Double.parseDouble(downPayTF.getText());
        double t = Integer.parseInt(termTF.getText());

        r = (r/100)/12;
        t*=12;
        p-=d;
        double partial = Math.pow((1+r), t);
        double Monthly = (r*p*partial)/(partial-1);

        String mon = String.format("%.2f", Monthly);
        JLabel nuMon = new JLabel("Monthly Payment: " + mon);

        String m = String.format("%.2f", (double)t);
        JLabel numL = new JLabel("Total Number of Payments: " + m);

        double dollarsPaid = Monthly*t;
        String dollar = String.format("%.2f", dollarsPaid);
        JLabel payL = new JLabel("Total Dollars Paid: " + dollar);

        double storeInterest = dollarsPaid - p;
        String i = String.format("%.2f", storeInterest);
        JLabel intL = new JLabel("Total Interest Paid: " + i);

        JLabel monn = new JLabel("Monthly Addition to Payment: ");

        monTF = new JTextField();
        monTF.setPreferredSize(new Dimension(20, 10));
        monTF.setEditable(true);

        buttonPanel.removeAll();
        buttonPanel.add(calcButton);

        //imagePanelT = new TestTexture("lightMarble.png");
        imagePanel.removeAll();
        imagePanel.setBackground(Color.getHSBColor((float) .0867,(float) .137, (float) 1.0));//(Color.getHSBColor((float) 0.575, (float) 0.452, (float) 1.000));

        imagePanel.setLayout(new GridLayout(12, 1));
        imagePanel.add(currentTotals);
        imagePanel.add(nuMon);
        imagePanel.add(numL);
        imagePanel.add(intL);
        imagePanel.add(payL);
        imagePanel.add(monn);
        imagePanel.add(monTF);


        buttonPanel.setBackground(Color.getHSBColor((float) 0.0431, (float) 0.325, (float) 0.720));

        containerPanelT.add(titlePT, BorderLayout.NORTH);
        containerPanelT.add(imagePanel, BorderLayout.CENTER);
        containerPanelT.add(buttonPanel, BorderLayout.SOUTH);

        add(containerPanelT);
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
    public void PrintTotals(double Monthly, double rate, int t, double p){
        containerPanelT.remove(imagePanel);
        containerPanelT.remove(calcButtonF);
        containerPanelT.remove(calcButtonC);
        containerPanelT.remove(definitionsPanel);
        containerPanelT.remove(gridPT);
        buttonPanel.removeAll();
        containerPanelT.remove(definitionsPanel);

        String m = String.format("%.2f", (double)t);
        JLabel numL = new JLabel("Total Number of Payments: $" + m);
        numL.setFont(numL.getFont().deriveFont(20f));
        numL.setForeground(Color.DARK_GRAY);

        double dollarsPaid = Monthly*t;
        String d = String.format("%.2f", dollarsPaid);
        d = "Total Dollars Paid: $" + d;
        //JLabel doll = new JLabel(d);
        JLabel payL = new JLabel(d);
        //JLabel doll = new JLabel(d);
        payL.setFont(payL.getFont().deriveFont(20f));
        payL.setForeground(Color.DARK_GRAY);

        double storeInterest = dollarsPaid - p;
        String i = String.format("%.2f", storeInterest);
        JLabel intL = new JLabel("Total Interest Paid: $" + i);
        intL.setFont(intL.getFont().deriveFont(20f));
        intL.setForeground(Color.DARK_GRAY);

        gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(400, 100));
        gridPanel.setBackground(Color.getHSBColor((float) .0867,(float) .137, (float) 1.0));
//        Color.getHSBColor((float) 0.575, (float) 0.452, (float) 1.000));
        gridPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        gridPanel.add(numL);

        gridPanel.add(payL);

        gridPanel.add(intL);

        backToStartButton.setText("Exit");
        buttonPanel.add(earlyPayoffButton);
        buttonPanel.add(backToStartButton);

        containerPanelT.add(gridPanel);

        revalidate();
        repaint();
    }
    public void AllPayments(double Monthly, double rate, int t, double p){

        containerPanelT.remove(imagePanel);
        containerPanelT.remove(calcButtonF);
        containerPanelT.remove(calcButtonC);
        containerPanelT.remove(calcButtonC);
        containerPanelT.remove(gridPT);
        containerPanelT.remove(definitionsPanel);
        containerPanelT.remove(gridP);

        earlyPayoffButton.setText("Run Numbers for Early Payoff");
        backToStartButton.setText("Exit");
        buttonPanel.removeAll();
        buttonPanel.add(earlyPayoffButton);
        buttonPanel.add(backToStartButton);

        //CREATE ARRAYS OF INFO

        paymentNum = new int[t];
        for(int i=0; i<t; i++){
            paymentNum[i] = i+1;
        }

        paymentAmount = new double[t];
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
            if(remain<0){
                remainingBalance[i]=0;
            }else{
                remainingBalance[i] = remain;
            }

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
        gridPanel.setPreferredSize(new Dimension(WIDTH+100, HEIGHT*10));
        gridPanel.setLayout(new GridLayout(t+1, 5));
        gridPanel.setBackground(Color.white);

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
        scrollPanel.setPreferredSize(new Dimension(500, t+2));

        Dimension d = gridPanel.getPreferredSize();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(d.width, d.height));
        scrollPane.getViewport().add(gridPanel);
        scrollPanel.add(scrollPane, BorderLayout.CENTER);

        containerPanelT.add(scrollPanel);
        update(this.getGraphics());
        revalidate();
        repaint();
    }
    public class TestTexture extends JPanel
    {
        java.io.File fileTexture;
        public TestTexture(String x){

            fileTexture  = new  java.io.File(x);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if (g2==null)
            {
                System.out.println("error");
                return;
            }
            try
            {
                BufferedImage mImage = ImageIO.read(fileTexture);
                java.awt.geom.Rectangle2D tr = new   java.awt.geom.Rectangle2D.Double(0, 0, mImage.getWidth(), mImage.getHeight());
                TexturePaint tp = new TexturePaint(mImage, tr);
                g2.setPaint(tp);
                java.awt.geom.Rectangle2D  r =  (java.awt.geom.Rectangle2D)this.getBounds();
                g2.fill(r);
            }
            catch (java.io.IOException ex) {}

        }
    }
}

