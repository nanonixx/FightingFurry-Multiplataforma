package com.company.server.game;
import com.company.model.Mensaje;
import jakarta.websocket.Session;



public class Jugador {

    public Jugador oponente;
    int vida;
    String gato;
    Mazo mazo;
    Mano mano = new Mano();
    Session session;
    public boolean torn;

    public Jugador(Session session, String gato) {
        this.session = session;
        this.vida = 100;
        this.gato = gato;
        mazo = new Mazo(gato);
    }

    void send(Mensaje mensaje) {
        try {
            session.getBasicRemote().sendObject(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarCartas() {
        send(new Mensaje("CARTAS", mano.toMensaje()));
    }

    public void robar() {
        mano.cartaList.add(mazo.robar());
    }
}
