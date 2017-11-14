
import java.awt.Image;
import javafx.scene.paint.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {

    public int x, y;
    public int order;
    public int pontos = 100;
    boolean image = false, atk;
    boolean isRight;
    ImageIcon walkR;
    ImageIcon walkL;
    ImageIcon walkU;
    ImageIcon walkD;
    ImageIcon perdedor;
    ImageIcon vencedor;
    ImageIcon Joelhada;
    ImageIcon Finish;
    
    

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setup(KombatMainForm frame) {
        setText("12");

        if (order > 1) {
            
            walkL = new ImageIcon(new ImageIcon(getClass().getResource("walk_l.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkL);            
            frame.getContentPane().add(this);
            this.isRight = false;

        } else {
            walkR = new ImageIcon(new ImageIcon(getClass().getResource("walk_r.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkR);
            frame.getContentPane().add(this);
            this.isRight = true;
        }

        setBounds(x, y, 90, 127);
        image = true;

    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        setBounds(x, y, 90, 127);
    }

    public void setIconRight() {
        setIcon(walkR);
    }

    public void setIconLeft() {
        setIcon(walkL);
    }

    public void setIconJoelhada() {
        setIcon(Joelhada);
    }

    public void invisible(KombatMainForm frame) {
        this.getIcon();

    }

    public void joelhada(KombatMainForm frame, int g, int s) {
        if (isRight) {
            this.walkR = new ImageIcon(new ImageIcon(getClass().getResource("joelhada.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkR);
        } else {
            this.walkL = new ImageIcon(new ImageIcon(getClass().getResource("joelhada2.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkL);
        }
    }

    public void emPosicao(KombatMainForm frame, int g, int s) {
        if (isRight) {

            walkR = new ImageIcon(new ImageIcon(getClass().getResource("walk_r.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkR);
        } else {

            walkL = new ImageIcon(new ImageIcon(getClass().getResource("walk_l.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkL);
        }
    }
    
    public void Perdedor(KombatMainForm frame, int g, int s) {

        if (isRight) {
            this.walkR = new ImageIcon(new ImageIcon(getClass().getResource("perdedor.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkR);
        } else {
            this.walkL = new ImageIcon(new ImageIcon(getClass().getResource("perdedor.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT));
            setIcon(walkL);
        }
    }
    
    public void Vencedor(KombatMainForm frame, int g, int s) {
        if (isRight) {

            walkR = new ImageIcon(new ImageIcon(getClass().getResource("vencedor.gif")).getImage().getScaledInstance(100, 130, Image.SCALE_DEFAULT));
            setIcon(walkR);
        } else {
            walkL = new ImageIcon(new ImageIcon(getClass().getResource("vencedor.gif")).getImage().getScaledInstance(100, 130, Image.SCALE_DEFAULT));
            setIcon(walkL);
            
        }

        
    }
//perguntar ???
    public  void Finalizar(KombatMainForm frame) {
        
        Finish = new ImageIcon(new ImageIcon(getClass().getResource("Finish.gif")).getImage().getScaledInstance(100, 130, Image.SCALE_AREA_AVERAGING));
        setBounds(300, 300, 90, 127);
        setIcon(Finish);
        frame.getContentPane().add(this);
        
    }

}
