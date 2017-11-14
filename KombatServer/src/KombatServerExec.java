
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class KombatServerExec {

    public static final int SPEED = 8;
    public List<Player> players = new ArrayList<Player>();
    boolean btR = false, btL = false, btU = false, btD = false, atk, atkf,lose;
    PrintWriter out;
    ServerSocket ss;

    public static void main(String[] args) {
        System.out.println("Starting...");
        KombatServerExec kse = new KombatServerExec();
        kse.waitForPlayer();
    }
    
    public void waitForPlayer() {
        try {
            ss = new ServerSocket(8880);
            Socket s;
            while (true) {
                s = ss.accept();
                Player player = new Player(s, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

        //monta protocolo atualizar player
    public void updateP(Player p) {
        for (Player player1 : players) {
            player1.sendMessage("/player " + p.order + " " + p.x + "," + p.y + "," + p.pontos);
        }
    }

    public void updatePlayer(Player p) {
        for (Player player1 : players) {
            player1.sendMessage("/up " + p.order + " " + p.x + "," + p.y);
            
        }
    }
    
    public void updateAtaque(Player p) {

        for (Player player1 : players) {
            if (atk) {
                player1.sendMessage("/atk " + p.order + " " + p.x + "," + p.y);
            }
            if (atkf) {
                player1.sendMessage("/atkf " + p.order + " " + p.x + "," + p.y + "," + p.pontos);
            }
            if(lose){
               player1.sendMessage("/finish " + p.order + " " + p.x + "," + p.y + "," + p.pontos);
            }
        }
    }
    
    //  registar player jogador e a ordem
    void register(Player aThis) {
        aThis.order = players.size() + 1;
        if (aThis.order < 2) {
            aThis.x = 172;
            aThis.y = 252;
            aThis.pontos = 100;
        } else {
            aThis.x = 544;
            aThis.y = 252;
            aThis.pontos = 100;
        }
        players.add(aThis);
    }

    void atacar(Player atk) {
        atk.sendMessage("/atk " + atk.order + " " + atk.x + "," + atk.y);
        updateAtaque(atk);

    }

    void atacadu(Player atkf) {
        calculaPontos(atkf);
        atkf.sendMessage("/atkf " + atkf.order + " " + atkf.x + "," + atkf.y);

    }

    public void atualizaClientes() {
        for (Player player : players) {
            this.updateP(player);
        }
    }

    // contabiliza placar
    public void calculaPontos(Player atacante) {
        for (Player player : players) {
            if (player.order != atacante.order) {

                if (Math.abs(Math.abs(atacante.x) - Math.abs(player.x)) < 40) {
                    player.pontos -= 10;;
                    System.out.println("===================================== " + player.order + " " + player.pontos);
                    updateAtaque(player);
                    
                    if(player.pontos==0){                    
                       player.sendMessage("/finish " + player.order + " " + player.x + "," + player.y + "," + player.pontos);
                       lose = true;
                       updateAtaque(player);
                    }
                   
                }
            }
        }

    }

}
