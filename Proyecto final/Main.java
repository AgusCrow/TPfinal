import java.util.*;

public class Main {

    public static int width = 10;
    public static int height = 10;
    public static int generations = 20;
    public static int speed = 1000;
    public static String population = "111#100#010";

    public static int inputMatrix[][] = {
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 1, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
    };

    public static void main(String[] args) {

        Matrix gol = new Matrix(width, height);

        mainMenu(gol);

    }

    public static void mainMenu(Matrix acc) {
        Scanner sc = new Scanner(System.in);

        int option;

        do {
            System.out.println("Menu" + "\n");
            System.out.println("1- Manual Matrix game");
            System.out.println("2- Random generation");
            System.out.print("3- Exit" + "\n\n");
            System.out.print("Select option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    acc.setMatrix(inputMatrix);
                    secondMenu(acc);
                    break;
                case 2:
                    acc.startRandomGeneration();
                    secondMenu(acc);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 3);

        sc.close();
    }

    // menu para seleccionar el modo de juego
    public static void secondMenu(Matrix acc){
        Scanner sc = new Scanner(System.in);

        int option;

        do {
            System.out.println("Game mode" + "\n");
            System.out.println("1- Normal Mode");
            System.out.println("2- Manual Generation");
            System.out.println("3- Infinite Mode");
            System.out.print("4- Exit" + "\n\n");
            System.out.print("Select option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    normalMode(acc);
                    break;
                case 2:
                    manualIteration(acc);
                    break;
                case 3:
                    InfiniteMode(acc);
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 4);

        sc.close();
    }



    public static void InfiniteMode(Matrix acc) {
        Scanner sc = new Scanner(System.in);

        int g = 1;
        boolean game = true;
        System.out.println("\nGeneration 0: \n");
        acc.printMatrix(height, width);
        int speed2 = 3000;

        System.out.println("\nYou chose the infinite mode it will start in a few seconds");

            try {
                Thread.sleep(speed2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        while (game == true) {
            
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println("\nGeneration " + g + ": " + "\n");

            acc.nextGeneration(height, width);
            acc.copyNextGenPrint(height, width);
            
            g++;         
            
        }
        sc.close();
    }

    public static void manualIteration(Matrix acc) {
        Scanner sc = new Scanner(System.in);

        int g = 1;
        boolean game = true;

        System.out.println("\nGeneration 0: \n");
        acc.printMatrix(height, width);

        while (game == true) {

            System.out.println("\nPresione enter para continuar o 'q' para salir");
            String respuesta = sc.nextLine();

            if (respuesta.equals("q")) {

                game = false;
            } else {
                game = true;
            }

            System.out.println("\nGeneration " + g + ": " + "\n");

            acc.nextGeneration(height, width);
            acc.copyNextGenPrint(height, width);

            g++;

        }
        sc.close();
    }

    public static void normalMode(Matrix acc) {
        Scanner sc = new Scanner(System.in);

        int g = 1;

        System.out.println("\nGeneration 0: \n");
        acc.printMatrix(height, width);

        while (g > 0 && g <= generations) {

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\nGeneration " + g + ": " + "\n");

            acc.nextGeneration(height, width);
            acc.copyNextGenPrint(height, width);

            g++;

        }
        sc.close();
    }
}
