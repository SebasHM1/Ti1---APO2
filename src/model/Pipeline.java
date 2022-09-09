package model;

public class Pipeline {

    public Node head;
    public Node tail;

    public Pipeline () {

    }

    public void addLast(Node input){

        if (tail.getType().equalsIgnoreCase("F")) {

            tail = input;

        } else {



        }

    }



}
