package model;
import java.util.Random;

public class Play {

    public String[][] dashboard;
    private Random rnd;
    private Pipeline line;

    public Play () {

        dashboard = new String[8][8];
        rnd = new Random();
        line = new Pipeline();

    }

    public String[][] generateDashBoard () {

        for (int i = 0; i < dashboard.length; i++) {

            for (int a = 0; a < dashboard[i].length; a++){

                dashboard[i][a] = "X";

            }

        }

        dashboard[rnd.nextInt(8)][rnd.nextInt(8)] = "F";
        dashboard[rnd.nextInt(8)][rnd.nextInt(8)] = "D";

        for (int i = 0; i < dashboard.length; i++) {

            for (int a = 0; a < dashboard.length; a ++ ) {

                if (dashboard[i][a].equalsIgnoreCase("F")) {

                    line.head = (new Node(dashboard[i][a], a, i));
                    line.head.setNext((new Node(dashboard[i][a], a, i)));
                    line.head.setPrev((new Node(dashboard[i][a], a, i)));;
                    line.tail = (new Node(dashboard[i][a], a, i));
                    line.tail.setPrev(new Node(dashboard[i][a], a, i));
                    System.out.println("Head pos Y: " + line.head.getPosY() + ", Pos X: " + line.head.getPosX());
                    System.out.println("Tail pos Y: " + line.tail.getPosY() + ", Pos X: " + line.tail.getPosX());
                    System.out.println("Tail prev pos Y: " + line.tail.getPrev().getPosY() + ", Pos X: " + line.tail.getPrev().getPosX());

                } else if (dashboard[i][a].equalsIgnoreCase("D")) {

                    System.out.println("Tipo: " + dashboard[i][a] + "posicion X: " + a + "posicion Y:" + i);

                }

            }

        }

        return dashboard;

    }

    public String putPipe (int posA, int posB, String pipeType) {

        String msg = "Error";

        if (dashboard[posA][posB].equalsIgnoreCase("F") || dashboard[posA][posB].equalsIgnoreCase("D")) {

            msg = "invalid position, there's a node here";

        } else if (posA < 0 || posA > 7 || posB < 0 || posB > 7){

            msg = "You should put a valid position";

        } else {

            dashboard[posA][posB] = pipeType;
            msg = "Pipe correctly added";

        }

        return msg;

    }

    public String printBoard () {

        String printBoard = "";

        for (int i = 0; i < dashboard.length ; i ++) {

            printBoard += "\n";

            for (int a = 0; a < dashboard[i].length; a++) {


                printBoard += dashboard[i][a] + " ";

            }

        }

        return printBoard;

    }

    public boolean evaluate () {

        boolean reached = false;
        boolean added = false;

        for (int p = 0; p < (dashboard.length * dashboard.length) || reached ; p++) {

            Node tail = line.tail;
            Node nodePrev = line.tail.getPrev();
            System.out.println("Nodo previo tipo: " + nodePrev.getType() + ", Posicion x: " + nodePrev.getPosX() + " Posicion Y: " + nodePrev.getPosY());
            System.out.println("Nodo cola tipo: " + tail.getType() + ", Posicion X: " + tail.getPosX() + ", Posicion Y: " + tail.getPosY());
            System.out.println("Head pos Y: " + line.head.getPosY() + ", Pos X: " + line.head.getPosX());

            for (int i = 1; i < 5 || !added; i++) {

                added = false;

                switch (i) {

                    case 1:

                        if (line.tail.getPosX() - 1 >= 0) {

                            System.out.println("Entra comprobante nodo izquierda");
                            Node nodeLeft = new Node(dashboard[line.tail.getPosY()][line.tail.getPosX() - 1] + "", line.tail.getPosX() - 1, line.tail.getPosY() + 0);
                            System.out.println("Nodo izquierda tipo: " + nodeLeft.getType() + " Posicion x: " + nodeLeft.getPosX() + " Posicion Y: " + nodeLeft.getPosY());

                            if (!nodeLeft.getType().equalsIgnoreCase("X") && nodeLeft != nodePrev) {

                                System.out.println("entra comprobante nulidad y previo iz");

                                if (tail.getType().equals("=") && (nodeLeft.equals("=") || nodeLeft.getType().equalsIgnoreCase("o"))) {

                                    System.out.println("Anade nodo izquierda con = o O");
                                    line.addLast(nodeLeft);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("o") && nodeLeft.getType().equals("=") && nodePrev.getType().equals("||")) {

                                    System.out.println("Anade nodo izquierda con = desde O");
                                    line.addLast(nodeLeft);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("F") && nodeLeft.getType().equals("=")) {

                                    System.out.println("Anade nodo izquierda con = desde fuente");
                                    line.addLast(nodeLeft);
                                    added = true;

                                } else if (nodeLeft.getType().equalsIgnoreCase("D") && tail.getType().equals("=")) {

                                    System.out.println("Anade nodo izquierda con D desde =");
                                    line.addLast(nodeLeft);
                                    added = true;
                                    reached = true;

                                }

                            }

                        }

                        break;

                    case 2:

                        if (line.tail.getPosX() + 1 <= dashboard.length) {

                            System.out.println("Entra comprobante nodo derecha");
                            Node nodeRight = new Node(dashboard[line.tail.getPosY()][line.tail.getPosX() + 1] + "", line.tail.getPosX() + 1, line.tail.getPosY() + 0);
                            System.out.println(printBoard());
                            System.out.println("Nodo derecha tipo: " + nodeRight.getType() + " Posicion x: " + nodeRight.getPosX() + " Posicion Y: " + nodeRight.getPosY());

                            if (!nodeRight.getType().equalsIgnoreCase("x") && nodeRight != nodePrev) {

                                System.out.println("entra comprobante nulidad y previo der");


                                if (tail.getType().equals("=") && (nodeRight.equals("=") || nodeRight.getType().equalsIgnoreCase("o"))) {

                                    System.out.println("Anade nodo der con = o O");
                                    line.addLast(nodeRight);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("o") && nodeRight.getType().equals("=") && nodePrev.getType().equals("||")) {

                                    System.out.println("Anade nodo der con = desde O");

                                    line.addLast(nodeRight);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("F") && nodeRight.getType().equals("=")) {
                                    System.out.println("Anade nodo der con = desde fuente");

                                    line.addLast(nodeRight);
                                    added = true;

                                } else if (nodeRight.getType().equalsIgnoreCase("D") && tail.getType().equals("=")) {
                                    System.out.println("Anade nodo der con D desde =");

                                    line.addLast(nodeRight);
                                    added = true;
                                    reached = true;

                                }


                            }

                        }

                        break;

                    case 3:

                        if (line.tail.getPosY() - 1 >= 0) {
                            System.out.println("Entra comprobante nodo arriba");
                            Node nodeUp = new Node(dashboard[line.tail.getPosY() - 1][line.tail.getPosX()], line.tail.getPosX(), line.tail.getPosY() - 1);
                            System.out.println("Nodo arriba tipo: " + nodeUp.getType() + " Posicion x: " + nodeUp.getPosX() + " Posicion Y: " + nodeUp.getPosY());

                            if (!nodeUp.getType().equalsIgnoreCase("x") && nodeUp != nodePrev) {
                                System.out.println("entra comprobante nulidad y previo ar");


                                if (tail.getType().equals("||") && (nodeUp.equals("||") || nodeUp.getType().equalsIgnoreCase("o"))) {

                                    System.out.println("Anade nodo ar con || o O");

                                    line.addLast(nodeUp);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("o") && nodeUp.getType().equals("||") && nodePrev.getType().equals("=")) {

                                    System.out.println("Anade nodo ar con || desde O");

                                    line.addLast(nodeUp);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("F") && nodeUp.getType().equals("||")) {

                                    System.out.println("Anade nodo ar con || desde Fuente");

                                    line.addLast(nodeUp);
                                    added = true;

                                } else if (nodeUp.getType().equalsIgnoreCase("D") && tail.getType().equals("||")) {

                                    System.out.println("Anade nodo ar con D desde ||");

                                    line.addLast(nodeUp);
                                    added = true;
                                    reached = true;

                                }

                            }

                        }

                        break;

                    case 4:

                        if (line.tail.getPosY() + 1 <= dashboard.length) {
                            System.out.println("Entra comprobante nodo abajo");
                            Node nodeDown = new Node(dashboard[line.tail.getPosY() + 1][line.tail.getPosX()], line.tail.getPosX(), line.tail.getPosY() + 1);
                            System.out.println("Nodo abajo tipo: " + nodeDown.getType() + " Posicion x: " + nodeDown.getPosX() + " Posicion Y: " + nodeDown.getPosY());

                            if (!nodeDown.getType().equalsIgnoreCase("x") && nodeDown != nodePrev) {
                                System.out.println("entra comprobante nulidad y previo abajo");

                                if (tail.getType().equals("||") && (nodeDown.equals("||") || nodeDown.getType().equalsIgnoreCase("o"))) {

                                    System.out.println("Anade nodo abajo con || o O");

                                    line.addLast(nodeDown);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("o") && nodeDown.getType().equals("||") && nodePrev.getType().equals("=")) {

                                    System.out.println("Anade nodo abajo con || desde O");

                                    line.addLast(nodeDown);
                                    added = true;

                                } else if (tail.getType().equalsIgnoreCase("F") && nodeDown.getType().equals("||")) {

                                    System.out.println("Anade nodo abajo con || desde Fuente");

                                    line.addLast(nodeDown);
                                    added = true;

                                } else if (nodeDown.getType().equalsIgnoreCase("D") && tail.getType().equals("||")) {

                                    System.out.println("Anade nodo abajo con D desde ||");

                                    line.addLast(nodeDown);
                                    added = true;
                                    reached = true;

                                }

                            }

                        }

                        break;

                }

                if (!added) {

                    p = dashboard.length * dashboard.length;

                }

            }

        }


        return reached;

    }

}
