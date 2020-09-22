package com.tiansirk.joketeller;

import java.util.ArrayList;
import java.util.Random;

public class JokeTeller {

    private ArrayList<String> jokes;

    public JokeTeller() {
        this.jokes = new ArrayList<>();
        populateJokes();
    }

    public String getJoke() {
        return jokes.get(randomGenerator(jokes.size()));
    }

    public void setJokes(ArrayList<String> jokes) {
        this.jokes = jokes;
    }

    private void populateJokes(){
        this.jokes.add("What’s the best thing about Switzerland?\n" +
                "I don’t know, but the flag is a big plus.");
        this.jokes.add("How many times can you subtract 10 from 100?\n" +
                "Once. The next time you would be subtracting 10 from 90.");
        this.jokes.add("Did you hear about the claustrophobic astronaut?\n" +
                "He just needed a little space.");
        this.jokes.add("Why don’t scientists trust atoms?\n" +
                "Because they make up everything.");
        this.jokes.add("Where are average things manufactured?\n" +
                "The satisfactory.");
        this.jokes.add("Why don’t Calculus majors throw house parties?\n" +
                "Because you should never drink and derive.");
        this.jokes.add("What did the left eye say to the right eye?\n" +
                "Between you and me, something smells.");
        this.jokes.add("What do you call a fake noodle?\n" +
                "An impasta.");
        this.jokes.add("How do you make a tissue dance?\n" +
                "Put a little boogie in it.");
        this.jokes.add("What did the 0 say to the 8?\n" +
                "Nice belt!");
    }

    private static int randomGenerator(int max){
        int min = 0;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}