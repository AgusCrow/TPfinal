public class Matrix {

    public boolean[][] matrix;
    public boolean[][] newMatrix;



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
        boolean nextState = false;
        if (matrix[i][j]) {
            nextState = n_neighbors == 2 || n_neighbors == 3;
        } else {
            nextState = n_neighbors == 3;
        }
        return nextState;
    }

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
                    "The dimensions of the input matrix do not match the dimensions of the original matrix.");
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (inputMatrix[i][j] == 1) {
                    matrix[i][j] = true;
                } else if (inputMatrix[i][j] == 0) {
                    matrix[i][j] = false;
                } else {
                    throw new IllegalArgumentException(
                            "Unvalid entry:" + inputMatrix[i][j] + "Expected '1' o '0'.");
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