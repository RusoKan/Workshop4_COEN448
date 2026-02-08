package util;

public class GraphParser {

    public static int[][] parse(String input) {
        if (input == null || input.equals("null")) {
            throw new IllegalArgumentException("Graph is null");
        }

        input = input.trim();
        if (input.equals("{}")) {
            return new int[0][0];
        }

        // Remove outer braces
        input = input.substring(1, input.length() - 1).trim();

        if (input.isEmpty()) {
            return new int[0][0];
        }

        String[] rows = input.split("\\},\\{");
        int[][] graph = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i]
                    .replace("{", "")
                    .replace("}", "")
                    .trim();

            if (row.isEmpty()) {
                graph[i] = new int[0];
            } else {
                String[] nums = row.split(",");
                graph[i] = new int[nums.length];
                for (int j = 0; j < nums.length; j++) {
                    graph[i][j] = Integer.parseInt(nums[j].trim());
                }
            }
        }
        return graph;
    }
}
