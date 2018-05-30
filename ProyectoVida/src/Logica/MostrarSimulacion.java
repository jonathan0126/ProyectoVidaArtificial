package Logica;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class MostrarSimulacion extends JFrame{
    
    Simulacion laSimulacion = new Simulacion();
    ArrayList<Oveja> lasOvejas = new ArrayList<>();
    ArrayList<Lobo> losLobos = new ArrayList<>();
    ArrayList<Pasto> elPasto = new ArrayList<>();
    Thread hiloUno;
    Graphics buffer;
    BufferedImage dibujo;
    Image ovejo = new ImageIcon(getClass().getResource("/img/ovejo.png")).getImage();
    Image oveja = new ImageIcon(getClass().getResource("/img/oveja.png")).getImage();
    Image lobo = new ImageIcon(getClass().getResource("/img/lobo.png")).getImage();
    Image pasto = new ImageIcon(getClass().getResource("/img/pasto.png")).getImage();
    Image fondo = new ImageIcon(getClass().getResource("/img/fondo.png")).getImage();
    int cantOvejas;
    int cantLobos;
    
    public MostrarSimulacion(){
        
        cantOvejas = Integer.parseInt(FrameSimulacion.textFieldCantidadO.getText());
        cantLobos = Integer.parseInt(FrameSimulacion.textFieldCantidadL.getText());
        lasOvejas = laSimulacion.Oveja(cantOvejas);
        losLobos = laSimulacion.Lobo(cantLobos);
        elPasto = laSimulacion.Pasto();
        setTitle("Lobos y Ovejas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 1200, 700);
        dibujo = new BufferedImage(1200, 700, BufferedImage.TYPE_INT_RGB);
        buffer = dibujo.createGraphics();
        setLocationRelativeTo(null);
        setVisible(true);        
        setResizable(false);
        repaint();
        
        hiloUno = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            moverOveja();
                            moverLobo();
                            repaint();
                            Thread.sleep(45);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
}
 
    
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        buffer.drawImage(fondo, 0, 0, this);

        for (int i = 0; i < lasOvejas.size(); i++) {
            if(i%2==0){
                buffer.drawImage(oveja, lasOvejas.get(i).getX(), lasOvejas.get(i).getY(), this);
            }
            else{
                buffer.drawImage(ovejo, lasOvejas.get(i).getX(), lasOvejas.get(i).getY(), this);
            }
            
        }
        for (int j = 0; j < losLobos.size(); j++) {
            buffer.drawImage(lobo, losLobos.get(j).getX(), losLobos.get(j).getY(), this);
        }
        for (int k = 0; k < elPasto.size(); k++) {
            buffer.drawImage(pasto, elPasto.get(k).getX(), elPasto.get(k).getY(), this);
        }
        g.drawImage(dibujo, 0, 0, this);
    }
    
    
    public void movimiento() {
        hiloUno.start(); // inicia toda la animacion en pantalla;
    }
    
    public void moverOveja() {
        
        for (int i = 0; i < lasOvejas.size(); i++) {
            int randomX = (int) (Math.random() * 3);
            int randomY = (int) (Math.random() * 3);
            if (randomX % 2 == 0) {
                lasOvejas.get(i).setX(lasOvejas.get(i).getX()-randomX);
            } else {
                lasOvejas.get(i).setX(lasOvejas.get(i).getX()+randomX);
            }
            if (randomY % 2 == 0) {
                lasOvejas.get(i).setY(lasOvejas.get(i).getY()-randomY);
            } else {
                lasOvejas.get(i).setY(lasOvejas.get(i).getY()+randomY);
            }
        }
    }
    
    public void moverLobo() {
        for (int i = 0; i < losLobos.size(); i++) {
            int randomX = (int) (Math.random() * 3);
            int randomY = (int) (Math.random() * 3);
            if (randomX % 2 == 0) {
                losLobos.get(i).setX(losLobos.get(i).getX()-randomX);
            } else {
                losLobos.get(i).setX(losLobos.get(i).getX()+randomX);
            }
            if (randomY % 2 == 0) {
                losLobos.get(i).setY(losLobos.get(i).getY()-randomY);
            } else {
                losLobos.get(i).setY(losLobos.get(i).getY()+randomY);
            }
        }
    }
    
}
