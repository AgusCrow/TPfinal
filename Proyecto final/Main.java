import java.util.Scanner;

public class Main {

    public static int width = 4;
    public static int height = 4;
    public static int generations = 400;
    public static int speed = 1;
    public static String population = "111#100#010";

    public static int inputMatrix[][] = {
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
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
                    event(acc);
                case 2:
                    acc.inicializarAleatoriamente();
                    event(acc);
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 3);

        sc.close();
    }
    
    public static void event(Matrix acc) {
        Scanner sc = new Scanner(System.in);

        int g = 1;
        boolean game=true;

        System.out.println("\nGeneration 0: \n");
        acc.printMatrix();

        while (true) {
            // try {
            //     Thread.sleep(speed);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            System.out.println("Presione enter para continuar o 'q' para salir");
            String respuesta = sc.nextLine();

            if (respuesta.equals("q")) {
                sc.close();
                game = false;
            } else{
                game = true;
            }

            System.out.println("\nGeneration "  +g + ": " + "\n");

            acc.nextGeneration();

            g++;
    
           
        }

    }

}
