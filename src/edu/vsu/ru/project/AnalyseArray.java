package edu.vsu.ru.project;

public class AnalyseArray {
    public static boolean analyseArrayForMonotone(int[][] array) {
        EMonotone monotone = EMonotone.Equals;

        int startPlace = array.length - 2; //если четно, то начинаем с конца, если нечёт - с начала
        int finalPlace = -1;
        int step = -1;
        int colsLength = array[0].length;

        for (int cols = 0; (cols < colsLength); cols++) {
            int rows;
            for (rows = startPlace; rows != finalPlace; rows += step) {
                switch (monotone) {
                    case Equals -> {
                        if (array[rows - step][cols] < array[rows][cols]) {
                            monotone = EMonotone.Ascending;
                        } else if (array[rows - step][cols] > array[rows][cols]) {
                            monotone = EMonotone.Descending;
                        }
                    }

                    case Ascending -> {
                        if (array[rows - step][cols] > array[rows][cols]) {
                            return false;
                        }
                    }

                    case Descending -> {
                        if (array[rows - step][cols] < array[rows][cols]) {
                            return false;
                        }
                    }
                }
            }

            rows = rows - step;

            if (cols != colsLength - 1) {
                switch (monotone) {
                    case Equals -> {
                        if (array[rows][cols + 1] > array[rows][cols]) {
                            monotone = EMonotone.Ascending;
                        } else if (array[rows][cols + 1] < array[rows][cols]) {
                            monotone = EMonotone.Descending;
                        }
                    }

                    case Ascending -> {
                        if (array[rows][cols + 1] < array[rows][cols]) {
                            return false;
                        }
                    }

                    case Descending -> {
                        if (array[rows][cols + 1] > array[rows][cols]) {
                            return false;
                        }
                    }
                }
            }
            startPlace = startPlace + (array.length) * step - 3 * step;
            step = -step;
            finalPlace = array.length - 1 - finalPlace;

        }
        return true;
    }

    private enum EMonotone {
        Descending,
        Equals,
        Ascending
    }
}
