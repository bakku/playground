package dev.bakku.daggerdiexample;

public class Main {
    public static void main(String[] args) {
        var authService = DaggerUserApplication.create().authService();

        if (authService.authUser("Paul", "secret").isPresent()) {
            System.out.println("Paul was logged in");
        }
    }
}
