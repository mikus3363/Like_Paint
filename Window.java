package kursJava.Like_Paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class Window extends JFrame implements KeyListener{
    private Kanwa kanwa;
    private ButtonPanel buttonPanel;
    private Color kolor = Color.BLACK;

    public Window() {
        this.setSize(1280,720);
        this.setTitle("Like Paint");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);

        kanwa = new Kanwa();
        buttonPanel = new ButtonPanel();

        this.getContentPane().add(kanwa);
        this.getContentPane().add(buttonPanel);
        this.setFocusable(true);//!!!!!!
        this.setFocusTraversalKeysEnabled(true);
        this.addKeyListener(this);

        kanwa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(buttonPanel.linia){
                    kanwa.lista.add(new Ellipse2D.Double(e.getX()-3,e.getY()-3,6,6));
                    kanwa.kolory.add(kolor);
                    kanwa.repaint();
                }
                if(buttonPanel.kwadrat) {
                    kanwa.lista.add(new Rectangle(e.getX() - 50, e.getY() - 50, 100, 100));
                    kanwa.kolory.add(kolor);
                    kanwa.repaint();
                }
                if(buttonPanel.kolo) {
                    kanwa.lista.add(new Ellipse2D.Double(e.getX() - 50, e.getY() - 50, 100, 100));
                    kanwa.kolory.add(kolor);
                    kanwa.repaint();
                }
            }
        });
        kanwa.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(buttonPanel.linia){
                    kanwa.lista.add(new Ellipse2D.Double(e.getX()-3,e.getY()-3,6,6));
                    kanwa.kolory.add(kolor);
                    kanwa.repaint();
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        buttonPanel.bReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonPanel.linia = buttonPanel.kwadrat = buttonPanel.kolo = false;
                buttonPanel.bLinia.setSelected(false);
                buttonPanel.bKwadrat.setSelected(false);
                buttonPanel.bKolo.setSelected(false);

                kanwa.lista.clear();
                kanwa.repaint();
            }
        });
        buttonPanel.bKolory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                kolor = JColorChooser.showDialog(kanwa,"Hello",Color.BLACK);
            }
        });

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Z && !kanwa.lista.isEmpty()){
            kanwa.lista.remove(kanwa.lista.size() - 1);
            kanwa.kolory.remove(kanwa.kolory.size() - 1);
            System.out.println("yomanik");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
