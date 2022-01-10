package edu.vsu.ru.project;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.util.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestsOfDifferentSituations {
    @Test
    public void testOfAscendingArray() {
        int[][] arrayForTest = ArrayUtils.readIntArray2FromFile("TestCase/TestOfAscendingArray.txt");
        assert arrayForTest != null;
        String resultOfProgram = countResultOfProgram(arrayForTest);
        String validResult = readResultFromFile(new File("TestResult/ResultOfAscendingArray.txt"));
        Assert.assertEquals(resultOfProgram, validResult);
    }

    @Test
    public void testOfDescendingArray() {
        int[][] arrayForTest = ArrayUtils.readIntArray2FromFile("TestCase/TestOfDescendingArray.txt");
        assert arrayForTest != null;
        String resultOfProgram = countResultOfProgram(arrayForTest);
        String validResult = readResultFromFile(new File("TestResult/ResultOfDescendingArray.txt"));
        Assert.assertEquals(resultOfProgram, validResult);
    }

    @Test
    public void testOfEqualsArray() {
        int[][] arrayForTest = ArrayUtils.readIntArray2FromFile("TestCase/TestOfEqualsArray.txt");
        assert arrayForTest != null;
        String resultOfProgram = countResultOfProgram(arrayForTest);
        String validResult = readResultFromFile(new File("TestResult/ResultOfEqualsArray.txt"));
        Assert.assertEquals(resultOfProgram, validResult);
    }

    @Test
    public void testOfNotMonotoneArray() {
        int[][] arrayForTest = ArrayUtils.readIntArray2FromFile("TestCase/testOfNotMonotoneArray.txt");
        assert arrayForTest != null;
        String resultOfProgram = countResultOfProgram(arrayForTest);
        String validResult = readResultFromFile(new File("TestResult/ResultOfNotMonotoneArray.txt"));
        Assert.assertEquals(resultOfProgram, validResult);
    }

    @Test
    public void testArrayWithBoundaryValues() {
        int[][] arrayForTest = ArrayUtils.readIntArray2FromFile("TestCase/testArrayWithBoundaryValues.txt");
        assert arrayForTest != null;
        String resultOfProgram = countResultOfProgram(arrayForTest);
        String validResult = readResultFromFile(new File("TestResult/ResultOfArrayWithBoundaryValues.txt"));
        Assert.assertEquals(resultOfProgram, validResult);
    }

    private String readResultFromFile(File resultFile) {
        try {
            FileReader fileReader = new FileReader(resultFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String validResult = bufferedReader.readLine();
            while (validResult != null) {
                return validResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    private String countResultOfProgram(int[][] arrayForTest) {
        if (AnalyseArray.analyseArrayForMonotone(arrayForTest)) {
            return "Array is monotone";
        } else {
            return "Array isn't monotone";
        }
    }
}
