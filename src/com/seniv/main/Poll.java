package com.seniv.main;

import com.seniv.VotingSystem.Ballot;
import com.seniv.VotingSystem.Borda;
import com.seniv.VotingSystem.Condorcet;
import com.seniv.VotingSystem.VotingSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Poll {

    private final String COMMAND = "\\go";
    private VotingSystem system;

    public Poll() {
        System.out.println(
                "Enter 1 for for the Borda Count, 2 for the Condorcet Method");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        System.out.println(
                "HRYHORII SENIV\nLab 3\nText from file seniv_23variant.txt:");

        ArrayList<Ballot> b = parseInput();
        switch (choice) {
            case 1:
                system = new Borda(b.toArray(new Ballot[b.size()]));
                System.out.println("WINNER: " + system.computeWinner());
                System.out.println(system.results());
                break;
            case 2:
                system = new Condorcet(b.toArray(new Ballot[b.size()]));
                System.out.println("WINNER: " + system.computeWinner());
                System.out.println(system.results());
                break;
        }
    }

    private ArrayList<Ballot> parseInput() {
        ArrayList<Ballot> ballots = new ArrayList<Ballot>();

        String ballot;

        try {
            File file = new File("C:\\Users\\Grisha\\Desktop\\Ну ЛП\\4 курс\\1 семестр\\Decision Theory\\Lab3\\Voting System\\src\\com\\seniv\\resources\\seniv_23variant.txt");
            //Create Object FileReader for Object File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while ((ballot = line) != null) {
                System.out.println(line);
                // считываем остальные строки в цикле

                if (ballot.equalsIgnoreCase(COMMAND)) {
                    return ballots;
                }

                int n = 1; //Number of ballots in the line

                int index = ballot.indexOf(' ');

                if (index != -1 && index < ballot.length() - 1) {
                    try {
                        n = Integer.parseInt(ballot.substring(0, index));
                        ballot = ballot.substring(index);
                    } catch (NumberFormatException e) {
                        n = 1;//try removing this
                    }
                }

                for (int i = 0; i < n; i++) {
                    if (ballot.length() > 0)
                        ballots.add(new Ballot(ballot));
                }

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ballots;
    }

    public static void main(String[] args) {
        Poll p = new Poll();
    }
}
