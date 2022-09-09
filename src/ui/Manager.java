package ui;
import java.util.Scanner;
import model.Controller;

public class Manager {

    public static Scanner sc;
    public static Controller controller;

    public static void main(String[] args) {

        init();
        menu();

    }

    public static void init () {

        sc = new Scanner (System.in);
        controller = new Controller();

    }

    public static void menu () {

        boolean fin = false;
        int opt = 0;

        System.out.println("Welcome to the main menu");
        while (!fin) {

            System.out.println("Type an option \n" +
                    "1) New game \n" +
                    "2) Print score \n" +
                    "3) Exit");

            opt = sc.nextInt();

            switch (opt) {
                case 1 -> {
                    System.out.println("Type your nickname: ");
                    String name = sc.next();
                    play(name);
                }
                case 2 -> score();
                case 3 -> {
                    System.out.println("Thanks for using");
                    fin = true;
                }
                default -> System.out.println("Type a valid option");
            }

        }

    }

    public static void play (String name) {

        boolean fin = false;
        int opt = 0;
        controller.createPlayer(name);
        controller.createBoard();

        while (!fin) {

            System.out.println("Welcome player " + name + ", this is your dashboard: \n" + controller.printDashboard() + "\n" +
                    "Please select an option: \n" +
                    "1) Put pipe \n" +
                    "2) Evaluate \n" +
                    "3) Exit");
            opt = sc.nextInt();

            switch (opt) {

                case 1:

                    boolean finpipe = false;

                    System.out.println("Type the position where you want to put the pipe: \n" +
                            "Y Position");
                    int posA = sc.nextInt();
                    System.out.println("X Position");
                    int posB = sc.nextInt();
                    String pipeOpt = "";

                    while (!finpipe) {
                        System.out.println("Please type one of the next options: =, ||, o");
                        pipeOpt = sc.next();

                        if (!pipeOpt.equals("=") && !pipeOpt.equals("||") && !pipeOpt.equalsIgnoreCase("o")) {

                            System.out.println("type a valid option");

                        } else {finpipe = true;}

                    }

                    System.out.println(controller.callPutPipe(posA,posB,pipeOpt));
                    break;

                case 2:

                    if (controller.evaluate()) {

                        System.out.println("Congratulations");

                    } else {

                        System.out.println("Its wrong");

                    }

                    break;

                case 3:

                    fin = true;
                    break;

                default:

                    System.out.println("select a valid option");

            }

        }

    }

    public static void score () {

        System.out.println("Type the user nickname whose score you want to know");

    }

}
