
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Maristela de Paula
 */
public class Player implements Runnable {

    public int x = 0, y = 0, w = 90, h = 127, pontos;
    public boolean btR = false, btL = false, btU = false, btD = false, btBK = false;
    public int order;
    boolean atk;
    
    Socket internal_socket;
    KombatServerExec main_server;
    BufferedReader in;
    PrintWriter out;
    Thread th;

    public Player(Socket s, KombatServerExec server) {
        main_server = server;
        internal_socket = s;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            th = new Thread(this);
            th.start();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
        out.flush();
    }

    public String recebe() {
        try {
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {  
        main_server.register(this); 
        main_server.atualizaClientes();
        

        String command = "";
        try {
            while (!(command = recebe()).equals("exit")) {
                if (command.equals("PR_R")) {
                    btR = true;
                }

                if (command.equals("PR_L")) {
                    btL = true;

                }
                if (command.equals("PR_U")) {
                    btU = true;
                }
                if (command.equals("PR_D")) {
                    btD = true;
                }
                if (command.equals("PR_BK")) {
                    btBK = true;
                    main_server.atk=true;
                    main_server.atacar(this);
                    
                }

                if (command.equals("RE_U")) {
                    btU = false;
                }

                if (command.equals("RE_D")) {
                    btD = false;
                }

                if (command.equals("RE_L")) {
                    btL = false;
                }

                if (command.equals("RE_R")) {
                    btR = false;
                }
                if (command.equals("RE_BK")) {
                    btBK = false;
                    main_server.atkf=true;                   
                    main_server.atacadu(this);
                    
                }

                this.updatePlayer();
                main_server.updateAtaque(this);
                main_server.atk=false;
                main_server.atkf=false;
                main_server.updatePlayer(this);

            }
        } catch (Exception ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void updatePlayer() {
        try {
            if (btR) {
                this.x += main_server.SPEED;

            }
            if (btL) {
                this.x -= main_server.SPEED;

            }
            if (btU) {
                this.y -= main_server.SPEED;

            }
            if (btD) {
                this.y += main_server.SPEED;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
