
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.security.spec.ECField;

import javax.swing.*;
import javax.swing.border.StrokeBorder;

/**
 * The type Draw shapes.
 */
public class DrawShapes extends JFrame {

    private static final long serialVersionUID = 1L;
    /**
     * The T 1.
     */
    Thread t1 = null;
    /**
     * The T 2.
     */
    Thread t2 = null;
    /**
     * The T 3.
     */
    Thread t3 = null;

    /**
     * Instantiates a new Draw shapes.
     *
     * @param evolution  the evolution
     * @param evolution1 the evolution 1
     * @param evolution2 the evolution 2
     */
    public DrawShapes(Evolution evolution, Evolution evolution1, Evolution evolution2) {

        setSize(new Dimension(1200, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        Shape[] circles = new Shape[evolution.population.getPopulationSize()];
        Shape[] circles1 = new Shape[evolution1.population.getPopulationSize()];
        Shape[] circles2 = new Shape[evolution2.population.getPopulationSize()];

        evolution.setGraph(this);
        evolution1.setGraph(this);
        evolution2.setGraph(this);

        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;
                Shape x1 = new Line2D.Double(310, 310, 10, 310);
                Shape y1 = new Line2D.Double(10, 10, 10, 310);
                g2.draw(x1);
                g2.draw(y1);
                g2.drawString("Average fitness: " + evolution.population.averageFitness(), 20, 20);
                g2.drawString("Fittest: " + evolution.population.getFittest().getFitness(), 20, 32);
                g2.drawString("Generation: " + evolution.generation, 20, 44);

                //table 1 numbers
                g2.drawString("5", 0,10);
                g2.drawString("4", 0,70);
                g2.drawString("3", 0,130);
                g2.drawString("2", 0,190);
                g2.drawString("1", 0,250);

                g2.drawString("1", 70,320);
                g2.drawString("2", 130,320);
                g2.drawString("3", 190,320);
                g2.drawString("4", 250,320);
                g2.drawString("5", 310,320);

                //table 2 numbers
                g2.drawString("5", 310,10);
                g2.drawString("4", 310,70);
                g2.drawString("3", 310,130);
                g2.drawString("2", 310,190);
                g2.drawString("1", 310,250);

                g2.drawString("1", 380,320);
                g2.drawString("2", 440,320);
                g2.drawString("3", 500,320);
                g2.drawString("4", 560,320);
                g2.drawString("5", 620,320);

                //table 3 numbers
                g2.drawString("5", 620,10);
                g2.drawString("4", 620,70);
                g2.drawString("3", 620,130);
                g2.drawString("2", 620,190);
                g2.drawString("1", 620,250);

                g2.drawString("1", 690,320);
                g2.drawString("2", 750,320);
                g2.drawString("3", 810,320);
                g2.drawString("4", 870,320);
                g2.drawString("5", 930,320);


                Shape x2 = new Line2D.Double(620, 310, 320, 310);
                Shape y2 = new Line2D.Double(320, 10, 320, 310);
                g2.draw(x2);
                g2.draw(y2);
                g2.drawString("Average fitness: " + evolution1.population.averageFitness(), 330, 20);
                g2.drawString("Fittest: " + evolution1.population.getFittest().getFitness(), 330, 32);
                g2.drawString("Generation: " + evolution1.generation, 330, 44);

                Shape x3 = new Line2D.Double(930, 310, 630, 310);
                Shape y3 = new Line2D.Double(630, 10, 630, 310);
                g2.draw(x3);
                g2.draw(y3);
                g2.drawString("Average fitness: " + evolution2.population.averageFitness(), 640, 20);
                g2.drawString("Fittest: " + evolution2.population.getFittest().getFitness(), 640, 32);
                g2.drawString("Generation: " + evolution2.generation, 640, 44);

                g2.drawString("Green one is the version one.", 950,20);
                g2.drawString("Roulette wheel selection", 950,32);
                g2.drawString("One point xover", 950, 44);


                g2.drawString("Blue one is the version two.", 950,80);
                g2.drawString("Rank selection", 950, 92);
                g2.drawString("Two point xover", 950, 104);

                g2.drawString("Turquois one is the version three.", 950,140);
                g2.drawString("Tournament selection", 950, 152);
                g2.drawString("One point xover", 950, 164);

                g2.drawString("The higher fitness", 950, 200);
                g2.drawString("The brighter colors!", 950, 212);

                g2.drawString("Horizontal line -> x2 values", 950, 240);
                g2.drawString("Vertical line   -> x1 values", 950, 252);

                //This is for first one
                for (int i = 0; i < evolution.population.getPopulationSize(); i++) {
                    circles[i] = new Ellipse2D.Double(10 + evolution.population.getIndividuals()[i].getValueOfChromosomeX1() * 60,
                            310 - evolution.population.getIndividuals()[i].getValueOfChromosomeX2() * 60,
                            evolution.population.getIndividuals()[i].getFitness(),
                            evolution.population.getIndividuals()[i].getFitness());
                }
                Color c = new Color((int) evolution.population.averageFitness() / 255, 2 * (int) evolution.population.averageFitness(), 0);
                g.setColor(c);
                for (int i = 0; i < evolution.population.getPopulationSize(); i++)
                    g2.draw(circles[i]);

                //this is for second one
                for (int i = 0; i < evolution1.population.getPopulationSize(); i++) {
                    circles1[i] = new Ellipse2D.Double(320 + evolution1.population.getIndividuals()[i].getValueOfChromosomeX1() * 60,
                            310 - evolution1.population.getIndividuals()[i].getValueOfChromosomeX2() * 60,
                            evolution1.population.getIndividuals()[i].getFitness(),
                            evolution1.population.getIndividuals()[i].getFitness());
                }
                Color c1 = new Color((int) evolution1.population.averageFitness() / 255, 0, 2 * (int) evolution1.population.averageFitness());
                g.setColor(c1);
                for (int i = 0; i < evolution1.population.getPopulationSize(); i++)
                    g2.draw(circles1[i]);

                //this is for third one

                for (int i = 0; i < evolution2.population.getPopulationSize(); i++) {
                    circles2[i] = new Ellipse2D.Double(630 + evolution2.population.getIndividuals()[i].getValueOfChromosomeX1() * 60,
                            310 - evolution2.population.getIndividuals()[i].getValueOfChromosomeX2() * 60,
                            evolution2.population.getIndividuals()[i].getFitness(),
                            evolution2.population.getIndividuals()[i].getFitness());
                }
                Color c2 = new Color((int) evolution2.population.averageFitness() / 255, (int) evolution2.population.averageFitness(), 2 * (int) evolution2.population.averageFitness());
                g.setColor(c2);
                for (int i = 0; i < evolution2.population.getPopulationSize(); i++)
                    g2.draw(circles2[i]);


            }
        };
        setTitle("My Shapes");
        this.getContentPane().add(p);
        JButton startButton = new JButton("Start");
        startButton.setLocation(10, 340);
        startButton.setSize(930, 60);
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(59, 89, 182));
        startButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.getContentPane().add(startButton);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                t1 = new Thread(evolution);
                t2 = new Thread(evolution1);
                t3 = new Thread(evolution2);
                t1.start();
                t2.start();
                t3.start();
            }
        });

    }


}
