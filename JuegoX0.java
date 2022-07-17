import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class JuegoX0 implements ActionListener {

    public static JFrame ventana;
    public static JButton botonTemp;
    public static JButton[] botones;
    public static int bandera1, probabilidad, coordenadaMovimientoMaquina, movimientosMaquina,
            movimientosJugador, puntosMaquina = 0, puntosJugador = 0;
    public static String[] botonesMenuIniciar = new String[2];
    public static String marcaJugador, marcaMaquina;
    public static JLabel labelJugadores;
    public static JTextField txt_jugador, txt_maquina;
    public static Random rnd = new Random();
    public static Boolean maquinaJuega, maquinaGano = null;

    public static void main(String[] args) {

        // Botones Menu Iniciar
        botonesMenuIniciar[0] = "Iniciar";
        botonesMenuIniciar[1] = "Salir";

        // Menu de arranque
        bandera1 = 0;
        do {
            bandera1 = JOptionPane.showOptionDialog(null, "Escoja una opcion", "Menu de Inicio", JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, botonesMenuIniciar, 0);
        } while (bandera1 == -1);

        // Si Inicia el Juego
        if (bandera1 == 0) {
            new JuegoX0();
        } else {
            JOptionPane.showMessageDialog(null, "Juego Cerrado");
            System.exit(0);
        }

    }

    public JuegoX0() {

        // Creacion de Ventana
        ventana = new JFrame("Juego X 0");
        ventana.setSize(550, 550);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Movimientos
        movimientosJugador = 0;
        movimientosMaquina = 0;

        // Creacion de Botones
        botones = new JButton[9];
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton();
            botones[i].setBounds(115 + 105 * (i % 3), 80 + 105 * (i / 3), 100, 100);
            botones[i].setOpaque(false);
            botones[i].addActionListener(this);

            ventana.add(botones[i]);
        }

        // Etiqueta Jugador vs maquina
        labelJugadores = new JLabel(
                "Jugador                                                                            Maquina");
        labelJugadores.setBounds(100, 420, 400, 30);
        ventana.add(labelJugadores);

        // JTextFields
        txt_jugador = new JTextField(String.valueOf(puntosJugador));
        txt_jugador.setEditable(false);
        txt_jugador.setBounds(105, 460, 40, 30);
        ventana.add(txt_jugador);

        txt_maquina = new JTextField(String.valueOf(puntosMaquina));
        txt_maquina.setEditable(false);
        txt_maquina.setBounds(380, 460, 40, 30);
        ventana.add(txt_maquina);

        // Escojer X o 0
        botonesMenuIniciar[0] = "X";
        botonesMenuIniciar[1] = "0";
        bandera1 = 0;
        do {
            bandera1 = JOptionPane.showOptionDialog(null, "Escoja su marca", "Menu de Marca", JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, botonesMenuIniciar, 0);
        } while (bandera1 == -1);

        if (bandera1 == 0) {
            marcaJugador = "<html><h1><font color=\"#ff0000 \">X</font></h1></html>";
            marcaMaquina = "<html><h1><font color=\"#0000ff\">0</font></h1></html>";
        } else {
            marcaJugador = "<html><h1><font color=\"#ff0000 \">0</font></h1></html>";
            marcaMaquina = "<html><h1><font color=\"#0000ff\">X</font></h1></html>";
        }

        // Random Maquina VS Jugador
        probabilidad = rnd.nextInt(10);

        if (probabilidad < 6) {
            JOptionPane.showMessageDialog(null, "Inicia la Maquina");
            maquinaJuega = true;
        } else {
            JOptionPane.showMessageDialog(null, "Inicia el Jugador");
            maquinaJuega = false;
        }

        ventana.setVisible(true);
        hacerMovimiento(maquinaJuega);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        botonTemp = (JButton) e.getSource();

        botonTemp.setText(marcaJugador);
        botonTemp.setEnabled(false);
        maquinaJuega = true;
        hacerMovimiento(maquinaJuega);

    }

    public void hacerMovimiento(boolean x) {

        if (x) {

            // Desabilito los botones un momento
            for (int i = 0; i < botones.length; i++) {
                botones[i].setEnabled(false);
            }

            esperar(1.0);

            if (movimientosMaquina == 0) {
                
                primerMovimiento();

            } else {
                //Dependiendo de la ultima posicion escogida, este continuara

                switch (coordenadaMovimientoMaquina) {
                    case 0:
                        
                        //Revisa 1,4,3
                        if (botones[1].getText().equals("")) {
                            botones[1].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 1;
                        } else if (botones[3].getText().equals("")) {
                            botones[3].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 3;
                        } else if (botones[4].getText().equals("") && !botones[1].getText().equals("") && !botones[3].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[1].getText().equals("") && !botones[3].getText().equals("") && !botones[4].getText().equals("")) {
                            movimientoRandom();
                        }

                    break;
                
                    case 1:
                        
                        //Revisa 0,4,2
                        if (botones[0].getText().equals("")) {
                            botones[0].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 0;
                        } else if (botones[2].getText().equals("")) {
                            botones[2].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 2;
                        } else if (botones[4].getText().equals("") && !botones[0].getText().equals("") && !botones[2].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[0].getText().equals("") && !botones[2].getText().equals("") && !botones[4].getText().equals("")) {
                            movimientoRandom();
                        }

                    break;
                
                    case 2:
                        
                        //Revisa 1,4,5
                        if (botones[1].getText().equals("")) {
                            botones[1].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 1;
                        } else if (botones[5].getText().equals("")) {
                            botones[5].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 5;
                        } else if (botones[4].getText().equals("") && !botones[1].getText().equals("") && !botones[5].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[1].getText().equals("") && !botones[5].getText().equals("") && !botones[4].getText().equals("")) {
                            movimientoRandom();
                        }

                    break;

                    case 3:
                        
                        //Revisa 0,4,6
                        if (botones[0].getText().equals("")) {
                            botones[0].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 0;
                        } else if (botones[6].getText().equals("")) {
                            botones[6].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 6;
                        } else if (botones[4].getText().equals("") && !botones[0].getText().equals("") && !botones[6].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[0].getText().equals("") && !botones[6].getText().equals("") && !botones[4].getText().equals("")) {
                            movimientoRandom();
                        }

                    break;

                    case 4:

                        //Revisa 1,3,5,7
                        if ((botones[1].getText().equals("") && botones[7].getText().equals(marcaMaquina)) || (!botones[3].getText().equals("") && !botones[5].getText().equals("") && !botones[0].getText().equals("") && !botones[2].getText().equals("") && !botones[6].getText().equals("") && !botones[8].getText().equals(""))) {
                            botones[1].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 1;
                        } else if ((botones[3].getText().equals("") && botones[5].getText().equals(marcaMaquina)) || (!botones[1].getText().equals("") && !botones[7].getText().equals("") && !botones[0].getText().equals("") && !botones[2].getText().equals("") && !botones[6].getText().equals("") && !botones[8].getText().equals(""))) {
                            botones[3].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 3;
                        } else if ((botones[5].getText().equals("") && botones[3].getText().equals(marcaMaquina)) || (!botones[7].getText().equals("") && !botones[1].getText().equals("") && !botones[0].getText().equals("") && !botones[2].getText().equals("") && !botones[6].getText().equals("") && !botones[8].getText().equals(""))) {
                            botones[5].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 5;
                        } else if ((botones[7].getText().equals("") && botones[1].getText().equals(marcaMaquina)) || (!botones[3].getText().equals("") && !botones[5].getText().equals("") && !botones[0].getText().equals("") && !botones[2].getText().equals("") && !botones[6].getText().equals("") && !botones[8].getText().equals(""))) {
                            botones[7].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 7;
                        } 
                        
                        //Revisar 0,2,6,8
                        else if (botones[0].getText().equals("") && botones[8].getText().equals(marcaMaquina)){
                            botones[0].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 0;
                        }  else if (botones[2].getText().equals("") && botones[6].getText().equals(marcaMaquina)) {
                            botones[2].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 2;
                        }  else if (botones[6].getText().equals("") && botones[2].getText().equals(marcaMaquina)) {
                            botones[6].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 6;
                        }  else if (botones[8].getText().equals("") && botones[0].getText().equals(marcaMaquina) ) {
                            botones[8].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 8;
                        } else {
                            movimientoRandom();
                        }

                    break;
                
                    case 5:
                        
                        //Revisa 2,4,8
                        if (botones[2].getText().equals("")) {
                            botones[2].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 2;
                        } else if (botones[8].getText().equals("")) {
                            botones[8].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 8;
                        } else if (botones[4].getText().equals("") && !botones[2].getText().equals("") && !botones[8].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[2].getText().equals("") && !botones[8].getText().equals("") && !botones[4].getText().equals("")) {
                            movimientoRandom();
                        }

                    break;

                    case 6:
                        
                        //Revisa 3,4,7
                        if (botones[3].getText().equals("")) {
                            botones[3].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 3;
                        } else if (botones[7].getText().equals("")) {
                            botones[7].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 7;
                        } else if (botones[4].getText().equals("") && !botones[3].getText().equals("") && !botones[7].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[3].getText().equals("") && !botones[7].getText().equals("") && !botones[4].getText().equals("")) {
                            movimientoRandom();
                        }

                    break;

                    case 7:

                        //Revisa 6,4,8
                        if (botones[6].getText().equals("")) {
                            botones[6].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 6;
                        } else if (botones[8].getText().equals("")) {
                            botones[8].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 8;
                        } else if (botones[4].getText().equals("") && !botones[6].getText().equals("") && !botones[8].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[6].getText().equals("") && !botones[4].getText().equals("") && !botones[8].getText().equals("")) {
                            movimientoRandom();
                        }

                    break;

                    case 8:

                        //Revisa 7,4,5
                        if (botones[7].getText().equals("")) {
                            botones[7].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 7;
                        } else if (botones[5].getText().equals("")) {
                            botones[5].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 5;
                        } else if (botones[4].getText().equals("") && !botones[7].getText().equals("") && !botones[5].getText().equals("")) {
                            botones[4].setText(marcaMaquina);
                            coordenadaMovimientoMaquina = 4;
                        } else if (!botones[7].getText().equals("") && !botones[5].getText().equals("") && !botones[4].getText().equals("")) {
                            movimientoRandom();
                        }
                        
                    break;
                }
                
                
            }

            //Revisar si gano alguien
            revisarEstatus();
            maquinaJuega = false;
            hacerMovimiento(maquinaJuega);

        } else {

            // Habilito los botones que no tienen la marca de la maquina
            for (int i = 0; i < botones.length; i++) {
                
                if (botones[i].getText().equals("")) {
                    botones[i].setEnabled(true);
                }

            }

        }

    }

    public void esperar(double tiempo){
        try {
            Thread.sleep((int)tiempo*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Movimiento de maquina en esquinas
    public void primerMovimiento(){

        probabilidad = rnd.nextInt(4);

            switch (probabilidad) {
                case 0:
                    
                if (botones[0].getText().equals("")) {
                    botones[0].setText(marcaMaquina);
                    coordenadaMovimientoMaquina = 0;
                } 

                break;

                case 1:
                
                    if (botones[2].getText().equals("")) {
                        botones[2].setText(marcaMaquina);
                        coordenadaMovimientoMaquina = 2;
                    } 

                break;
            
                case 2:
                    
                    if (botones[6].getText().equals("")) {
                        botones[6].setText(marcaMaquina);
                        coordenadaMovimientoMaquina = 6;
                    } 

                break;

                case 3:
                    
                    if (botones[8].getText().equals("")) {
                        botones[8].setText(marcaMaquina);
                        coordenadaMovimientoMaquina = 8;
                    } 

                break;
            }
            movimientosMaquina++;
    }

    //Movimiento por si la maquina no encuentra un lugar estrategico, que solo marque en un boton vacio cualquiera
    public void movimientoRandom(){
        for (int i = 0; i < botones.length; i++) {
            if (botones[i].getText().equals("")) {
                botones[i].setText(marcaMaquina);
                i = botones.length;
            }
        }
    }

    //Metodo para saber si alguien gano o hubo empate
    public void revisarEstatus(){
        int contador = 0;
        for (int i = 0; i < botones.length; i++) {
            if (!botones[i].getText().equals("")) {
                contador++;
            }
        }
        
        //Si gano maquina
        if (botones[0].getText().equals(marcaMaquina) && botones[1].getText().equals(marcaMaquina) && botones[2].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }

        if (botones[3].getText().equals(marcaMaquina) && botones[4].getText().equals(marcaMaquina) && botones[5].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }

        if (botones[6].getText().equals(marcaMaquina) && botones[7].getText().equals(marcaMaquina) && botones[8].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }

        if (botones[1].getText().equals(marcaMaquina) && botones[4].getText().equals(marcaMaquina) && botones[7].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }

        if (botones[0].getText().equals(marcaMaquina) && botones[3].getText().equals(marcaMaquina) && botones[6].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }

        if (botones[2].getText().equals(marcaMaquina) && botones[5].getText().equals(marcaMaquina) && botones[8].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }

        if (botones[0].getText().equals(marcaMaquina) && botones[4].getText().equals(marcaMaquina) && botones[8].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }

        if (botones[6].getText().equals(marcaMaquina) && botones[4].getText().equals(marcaMaquina) && botones[2].getText().equals(marcaMaquina)) {
            maquinaGano = true;
        }


        //Si gano jugador
        if (botones[0].getText().equals(marcaJugador) && botones[1].getText().equals(marcaJugador) && botones[2].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (botones[3].getText().equals(marcaJugador) && botones[4].getText().equals(marcaJugador) && botones[5].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (botones[6].getText().equals(marcaJugador) && botones[7].getText().equals(marcaJugador) && botones[8].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (botones[1].getText().equals(marcaJugador) && botones[4].getText().equals(marcaJugador) && botones[7].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (botones[0].getText().equals(marcaJugador) && botones[3].getText().equals(marcaJugador) && botones[6].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (botones[2].getText().equals(marcaJugador) && botones[5].getText().equals(marcaJugador) && botones[8].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (botones[0].getText().equals(marcaJugador) && botones[4].getText().equals(marcaJugador) && botones[8].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (botones[6].getText().equals(marcaJugador) && botones[4].getText().equals(marcaJugador) && botones[2].getText().equals(marcaJugador)) {
            maquinaGano = false;
        }

        if (contador == 9 && maquinaGano == null) {
            JOptionPane.showMessageDialog(null,"Hubo empate");
        } else {

            if (maquinaGano != null) {
                if (maquinaGano) {
                    puntosMaquina++;
                    txt_maquina.setText(String.valueOf(puntosMaquina));
                    JOptionPane.showMessageDialog(null,"Maquina Gano");
                } else {
                    puntosJugador++;
                    txt_jugador.setText(String.valueOf(puntosJugador));
                    JOptionPane.showMessageDialog(null,"Jugador Gano");
                }

                do {
                    bandera1 = JOptionPane.showConfirmDialog(null, "Quiere volver a empezar?", "AVISO", JOptionPane.YES_NO_OPTION);
                } while (bandera1 == -1);

                if (bandera1 == 0) {
                    maquinaGano = null;
                    ventana.dispose();
                    new JuegoX0();
                } else if (bandera1 == 1){
                    JOptionPane.showMessageDialog(null,"Juego Finalizado. Cerrando...");
                    System.exit(0);
                }

            }

        }

    }
}
