package ru.job4j.gc;


public class User {

    private String name;
    private int score;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) {
        User student = new User("Ivan", 20);
        User passenger = new User("Igor", 22);
        User citizen = new User("Ilya", 18);
    }
    }
