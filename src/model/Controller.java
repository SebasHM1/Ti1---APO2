package model;

import java.util.ArrayList;

public class Controller {

    ArrayList<Player> playerList;
    Play play = new Play ();

    public Controller () {

        this.playerList = new ArrayList<Player>();

    }

    public ArrayList<Player> getPlayerList () {return playerList;}

    public void createPlayer (String name) {

        Player player1 = new Player(name);

    }

    public String printDashboard () {

        String dashboardPrint = play.printBoard();
        return dashboardPrint;

    }

    public String callPutPipe (int posA, int posB, String pipeType) {

        String msg = play.putPipe(posA,posB,pipeType);
        return msg;
    }


    public boolean evaluate () {

        boolean reached = play.evaluate();

        return reached;

    }

    public void createBoard () {

        play.generateDashBoard();

    }

}
