package project03;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamCombinationGenerator {
    public static void generateCombinations(String[] members, int start, List<String> currentTeam, int teamSize) {
        if (currentTeam.size() == teamSize) {
            System.out.println(currentTeam);
            return;
        }

        for (int i = start; i < members.length; i++) {
            currentTeam.add(members[i]);
            generateCombinations(members, i + 1, currentTeam, teamSize);

            currentTeam.remove(currentTeam.size() - 1);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

    System.out.println("Enter the number of team members:");
    int n = sc.nextInt();
    sc.nextLine();

    String[] members = new String[n];
    System.out.println("Enter the names of the team members:");

    for (int i = 0; i < n; i++) {
        members[i] = sc.nextLine();
    }

    System.out.println("Enter the desired team size:");
    int teamSize = sc.nextInt();

    System.out.println("\nPossible team combinations:");

    generateCombinations(members, 0, new ArrayList<>(), teamSize);
    sc.close();
    }

}