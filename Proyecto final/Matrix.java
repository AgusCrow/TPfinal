public class Matrix {

    public boolean[][] matrix;
    public boolean[][] newMatrix;

    public int ALIVE_CELL = 1;
    public int DEAD_CELL = 0;

    int width;
    int height;

    // Constructor
    public Matrix(int w, int h) {
        width = w;
        height = h;
        matrix = new boolean[width][height];
        newMatrix = new boolean[width][height];
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    // Muestra el tablero
    public void printMatrix() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] ? " X " : " 0 ");
            }
            System.out.println();
        }
    }

    private int neighbors_count(int i, int j) {
        // Inicio de la contabilizacion de vecinos
        int n_neighbors = 0;

        for (int neighborI = i - 1; neighborI <= i + 1; neighborI++) {
            for (int neighborJ = j - 1; neighborJ <= j + 1; neighborJ++) {
                neighborI = (neighborI + i + width) % width; // Circular
                neighborJ = (neighborJ + j + height) % height;
                if (neighborI == i && neighborJ == j) {
                    continue;
                }
                if (neighborI >= 0 && neighborI < height && neighborJ >= 0 & neighborJ < width) {
                    if (matrix[neighborI][neighborJ]) {
                        n_neighbors++;
                    }
                }
            }
        }
        if (matrix[i][j]) {
            n_neighbors--;
        }
        return n_neighbors;
    }

    // CALCULANDO LA PROXIMA GENERACION
    private boolean calculateNextGeneration(int i, int j) {
        int n_neighbors = neighbors_count(i, j);

        if (matrix[i][j]) {
            return n_neighbors == 2 || n_neighbors == 3;
        } else {
            return n_neighbors == 3;
        }
    }

    void nextGeneration() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newMatrix[i][j] = calculateNextGeneration(i, j);
            }
        }
        copyNextGenPrint();

    }

    public void copyNextGenPrint() {
        // // Copiar el estado de la siguiente generación al tablero actual
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
            printMatrix();
        }
    }



    // MODOS DE JUEGO
    public void setMatrix(int[][] inputMatrix) {
        if (inputMatrix.length != height || inputMatrix[0].length != width) {
            throw new IllegalArgumentException(
                    "Las imensiones de la matriz de entrada no coinciden con las imensiones de la matriz original.");
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (inputMatrix[i][j] == ALIVE_CELL) {
                    matrix[i][j] = true;
                } else if (inputMatrix[i][j] == DEAD_CELL) {
                    matrix[i][j] = false;
                } else {
                    throw new IllegalArgumentException(
                            "Entrada no válida: " + inputMatrix[i][j] + ". Se espera '1' o '0'.");
                }
            }
        }
    }

    // Por si lo quiero iniciar aletoriamente
    public void inicializarAleatoriamente() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = Math.random() < 0.5;
            }
        }
    }

}