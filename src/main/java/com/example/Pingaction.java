package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CommandInjectionApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename to list: ");
        String filename = scanner.nextLine();

        // ❌ Command Injection Vulnerability
        String command = "ls " + filename;

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error executing command: " + e.getMessage());
        }
    }
}
