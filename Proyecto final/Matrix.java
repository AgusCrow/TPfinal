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

    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    // Muestra el tablero
    public void printMatrix(int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] ? " X " : " 0 ");
            }
            System.out.println();
        }
    }

    // private int neighbors_count(int i, int j) {
    //     // Inicio de la contabilizacion de vecinos
    //     int n_neighbors = 0;

    //     for (int neighborI = i - 1; neighborI <= i + 1; neighborI++) {
    //         for (int neighborJ = j - 1; neighborJ <= j + 1; neighborJ++) {
    //             neighborI = (neighborI + i + width);
    //             neighborJ = (neighborJ + j + height);
    //             if (neighborI == i && neighborJ == j) {
    //                 continue;
    //             }
    //             if (neighborI >= 0 && neighborI < height && neighborJ >= 0 & neighborJ < width) {
    //                 if (matrix[neighborI][neighborJ]) {
    //                     n_neighbors++;
    //                 }
    //             }
    //         }
    //     }
    //     if (matrix[i][j]) {
    //         n_neighbors--;
    //     }
    //     return n_neighbors;
    // }

    private int neighbors_count(int i, int j) {
        int neigbors_n = 0;

        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int neigborsI = i + di;
                int neigborsJ = j + dj;

                // Si el vecino está dentro del tablero
                if (neigborsI >= 0 && neigborsI < height && neigborsJ >= 0 && neigborsJ < width) {
                    // Si el vecino está vivo
                    if (matrix[neigborsI][neigborsJ]) {
                        neigbors_n++;
                    }
                }
            }
        }

        // No contar la célula actual como vecina
        if (matrix[i][j]) {
            neigbors_n--;
        }

        return neigbors_n;
    }


    // CALCULANDO LA PROXIMA GENERACION
    private boolean calculateNextGeneration(int i, int j) {
        int n_neighbors = neighbors_count(i, j);
        boolean nextState = false; // Initialize next state as false
            
        if (matrix[i][j]) {
            nextState = n_neighbors == 2 || n_neighbors == 3;
        } else {
            nextState = n_neighbors == 3;
        }
        return nextState;
    }
        // if (matrix[i][j]) {
        //     return n_neighbors == 2 || n_neighbors == 3;
        // } else {
        //     return n_neighbors == 3;
        // }

    // }

    void nextGeneration(int height, int width) {
        newMatrix = new boolean[width][height];


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newMatrix[i][j] = calculateNextGeneration(i, j);
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }

    public void copyNextGenPrint(int height, int width) {
        // // Copiar el estado de la siguiente generación al tablero actual
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = newMatrix[i][j];

            }
        }
        printMatrix(height, width);
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
    public void startRandomGeneration() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = Math.random() < 0.5;
            }
        }
    }

}