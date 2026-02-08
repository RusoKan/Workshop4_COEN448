package coen448.assignment1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import util.GraphParser;

public class ECC_Testing {

    /* ==============================
     * SolutionOne
     * ============================== */
    @ParameterizedTest
    @CsvFileSource(resources = "/ecc_tests.csv", numLinesToSkip = 1)
    void testSolutionOne_ECC(String graphStr, String expected) {
        runTest(new SolutionOne(), graphStr, expected);
    }

    /* ==============================
     * SolutionTwo
     * ============================== */
    @ParameterizedTest
    @CsvFileSource(resources = "/ecc_tests.csv", numLinesToSkip = 1)
    void testSolutionTwo_ECC(String graphStr, String expected) {
        runTest(new SolutionTwo(), graphStr, expected);
    }

    /* ==============================
     * SolutionOneWithBug
     * ============================== */
    @ParameterizedTest
    @CsvFileSource(resources = "/ecc_tests.csv", numLinesToSkip = 1)
    void testSolutionOneWithBug_ECC(String graphStr, String expected) {
        runTest(new SolutionOneWithBug(), graphStr, expected);
    }

    /* ==============================
     * Shared test logic
     * ============================== */
    private void runTest(Object solution, String graphStr, String expected) {
        if (expected.equals("exception")) {
            assertThrows(IllegalArgumentException.class, () -> {
                int[][] graph = GraphParser.parse(graphStr);
                invoke(solution, graph);
            });
        } else {
            int[][] graph = GraphParser.parse(graphStr);
            boolean exp = Boolean.parseBoolean(expected);
            boolean actual = invoke(solution, graph);
            assertEquals(exp, actual);
        }
    }

    /* ==============================
     * Dynamic dispatch helper
     * ============================== */
    private boolean invoke(Object solution, int[][] graph) {
        if (solution instanceof SolutionOne) {
            return ((SolutionOne) solution).isBipartite(graph);
        }
        if (solution instanceof SolutionTwo) {
            return ((SolutionTwo) solution).isBipartite(graph);
        }
        if (solution instanceof SolutionOneWithBug) {
            return ((SolutionOneWithBug) solution).isBipartite(graph);
        }
        throw new IllegalArgumentException("Unknown solution type");
    }
}
