# COEN 448 – Assignment 1

## Input Domain Modeling and Coverage-Based Testing for `isBipartite()`

---

## Overview

This project applies **Input Domain Modeling (IDM)** and **coverage-based testing** techniques to the method:

```java
boolean isBipartite(int[][] graph)
```

The goal is to:

* Define acceptance criteria
* Model the input domain
* Apply Each Choice Coverage (ECC) and Basic Choice Coverage (BCC)
* Implement JUnit 5 tests using CSV-based test data
* Detect defects in a buggy implementation

Three implementations are tested:

* SolutionOne (correct)
* SolutionTwo (correct)
* SolutionOneWithBug (incorrect)

---

## 1. Acceptance Criteria

**Valid Input Domain:** The method shall return true if and only if the graph is bipartite.

| ID | Description                                 | Expected Result |
| -- | ------------------------------------------- | --------------- |
| V1 | Empty graph                                 | true            |
| V2 | Single vertex, no edges                     | true            |
| V3 | Multiple vertices, no edges                 | true            |
| V4 | Connected bipartite graph (tree/even cycle) | true            |
| V5 | Disconnected bipartite graph                | true            |
| V6 | Graph with an odd-length cycle              | false           |
| V7 | Graph with a self-loop                      | false           |

**Invalid Input Domain:** For invalid inputs, the method shall throw an `IllegalArgumentException`.

| ID | Description                                 |
| -- | ------------------------------------------- |
| I1 | graph == null                               |
| I2 | graph[i] == null                            |
| I3 | Invalid vertex index (<0 or >=graph.length) |

All invalid cases are explicitly tested.

---

## 2. Input Domain Modeling (IDM)

**Program Characteristics and Block Values:**

| ID | Characteristic | IDM Type   | Block Values                          |
| -- | -------------- | ---------- | ------------------------------------- |
| C1 | Graph size     | Interface  | empty, single, multiple               |
| C2 | Connectivity   | Functional | connected, disconnected               |
| C3 | Edge structure | Functional | no edges, tree, even cycle, odd cycle |
| C4 | Self-loop      | Functional | present, absent                       |
| C5 | Input validity | Interface  | valid, invalid                        |

---

## 3. Each Choice Coverage (ECC)

Each block value for every characteristic appears at least once.

| Test | Graph Size | Connectivity | Edge Structure | Self-loop | Validity | Expected  |
| ---- | ---------- | ------------ | -------------- | --------- | -------- | --------- |
| E1   | empty      | connected    | no edges       | absent    | valid    | true      |
| E2   | single     | connected    | no edges       | absent    | valid    | true      |
| E3   | multiple   | connected    | tree           | absent    | valid    | true      |
| E4   | multiple   | connected    | even cycle     | absent    | valid    | true      |
| E5   | multiple   | connected    | odd cycle      | absent    | valid    | false     |
| E6   | multiple   | disconnected | odd cycle      | absent    | valid    | false     |
| E7   | multiple   | connected    | tree           | present   | valid    | false     |
| E8   | multiple   | connected    | tree           | absent    | invalid  | exception |

---

## 4. Basic Choice Coverage (BCC)

**Base choice:** Multiple vertices, connected, tree, no self-loop, valid input.

| Test | Variation from Base Choice | Expected  |
| ---- | -------------------------- | --------- |
| B1   | Base choice                | true      |
| B2   | Empty graph                | true      |
| B3   | Odd cycle                  | false     |
| B4   | Disconnected graph         | true      |
| B5   | Self-loop                  | false     |
| B6   | Invalid input              | exception |

---

## 5. Test Case Data Files

Test cases are stored in CSV files:

* `ecc_tests.csv` — Each Choice Coverage
* `bcc_tests.csv` — Basic Choice Coverage

Both files are located in:

```
src/test/resources
```

Each CSV row contains:

* Graph input (string format)
* Expected output (true, false, or exception)

---

## 6. JUnit 5 Test Implementation

**Techniques Used:**

* `@ParameterizedTest`
* `@CsvFileSource`
* Shared test logic for all implementations
* Independent testing of all implementations

**Test Files and Coverage:**

| Test File     | Coverage |
| ------------- | -------- |
| ECC_Testing.java  | ECC      |
| BCC_Testing.java | BCC      |

**Expected Results:**

| Implementation     | ECC  | BCC  |
| ------------------ | ---- | ---- |
| SolutionOne        | PASS | PASS |
| SolutionTwo        | PASS | PASS |
| SolutionOneWithBug | FAIL | FAIL |

Failing results correctly expose defects in `SolutionOneWithBug`.

---

## 7. Conclusion

This project demonstrates:

* Complete input domain coverage
* Correct application of IDM
* Proper ECC and BCC test generation
* Automated testing using JUnit 5 and CSV-based test data
