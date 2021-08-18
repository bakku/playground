package dev.bakku.daggerdiexample.models;

public final class User {
    private final String username;
    private final String password;
    private final int age;

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public boolean passwordMatches(String otherPassword) {
        return password.equals(otherPassword);
    }
}
