import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
class HighScoresTable {
    private ArrayList<ScoreInfo> table;
    private static int size;
    private String fileName = "highscores";
    private File file;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        HighScoresTable table = new HighScoresTable(3);

        File file = new File("C:\\Users\\stavi\\IdeaProjects\\ass7\\highscores");

        try {
            table.load(file);
        } catch (IOException e) {
            System.out.println("wrong in save-main func");
        }
    }

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.

    /**
     * Instantiates a new High scores table.
     *
     * @param sizeN the size n
     */
    public HighScoresTable(int sizeN) {
        table = new ArrayList<>();
        size = sizeN;
        file = new File(fileName);
        try {
            load(file);
        } catch (IOException e) {
            System.out.println("Fail in creating new File");
        } finally {
            try {
                PrintWriter writer = new PrintWriter(fileName, "UTF-8");
                load(file);
            } catch (IOException e2) {
                System.out.println("Fail in creating new file");
            }
        }
    }

    /**
     * Instantiates a new High scores table.
     *
     * @param sizeN  the size n
     * @param tableN the table n
     */
    public HighScoresTable(int sizeN, ArrayList<ScoreInfo> tableN) {
        size = sizeN;
        table = tableN;
    }

    /**
     * Add.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        int index = getIndex(score.getScore());
        if (index < size) {
            pushScoreDown(index);
            table.get(index).setScoreInfo(score);
        }
        try {
            save(file);
        } catch (IOException e) {
            System.out.println("fail in saving");
        }
    }

    /**
     * Push score down.
     *
     * @param index the index
     */
    public void pushScoreDown(int index) {
        for (int i = (size - 1); i > index; i--) {
            if (table.size() >= i && i != 0) {
                if (i >= table.size()) {
                    table.add(new ScoreInfo());
                }
                table.get(i).setScoreInfo(table.get(i - 1).getScoreInfo());
            }
        }
        if (index == table.size()) {
            table.add(new ScoreInfo());
        }
    }

    /**
     * Gets index.
     *
     * @param newScore the new score
     * @return the index
     */
    public int getIndex(int newScore) {
        for (int i = 0; i < size; i++) {
            if (table.size() == i) {
                return i;
            }
            if (table.get(i).getScore() < newScore) {
                return i;
            }
        }

        return size;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public ArrayList<ScoreInfo> getTable() {
        return table;
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return table;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        for (int i = 0; i < size; i++) {
            if (table.get(i).getScore() == score) {
                return (i + 1);
            }
        }

        return (size + 1);
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        table.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        BufferedReader is = null;
        try {
            is = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                insertData(line, table);
            }
        } catch (IOException e) {
            System.out.println("fail while opening file in reading");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("fail while closing file in reading");
                }
            }
        }
    }

    /**
     * Insert data.
     *
     * @param line      the line
     * @param tableCell the table cell
     */
    public static void insertData(String line, ArrayList<ScoreInfo> tableCell) {
        String[] values = line.split(",");
        ScoreInfo member = new ScoreInfo(values[0], Integer.valueOf(values[1]));
        tableCell.add(member);
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(filename)));
            for (ScoreInfo member : table) {
                os.printf(member.getName() + "," + member.getScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("fail while opening file in writing");
        } finally {
            if (os != null) {
                if (os != null) {
                    os.close();
                }
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        ArrayList<ScoreInfo> tableTemp = new ArrayList<>();

        BufferedReader is = null;
        try {
            is = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                insertData(line, tableTemp);
            }
        } catch (IOException e) {
            System.out.println("fail while opening file in reading");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("fail while closing file in reading");
                }
            }
        }

        return new HighScoresTable(tableTemp.size(), tableTemp);
    }
}