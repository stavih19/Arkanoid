/**
 * The type Score info.
 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param nameN  the name n
     * @param scoreN the score n
     */
    public ScoreInfo(String nameN, int scoreN) {
        name = nameN;
        score = scoreN;
    }

    /**
     * Instantiates a new Score info.
     */
    public ScoreInfo() {
        name = null;
        score = 0;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets name.
     *
     * @param nameN the name n
     */
    public void setName(String nameN) {
        name = nameN;
    }

    /**
     * Gets score info.
     *
     * @return the score info
     */
    public ScoreInfo getScoreInfo() {
        return this;
    }

    /**
     * Sets score.
     *
     * @param scoreN the score n
     */
    public void setScore(int scoreN) {
        score = scoreN;
    }

    /**
     * Sets score info.
     *
     * @param infoN the info n
     */
    public void setScoreInfo(ScoreInfo infoN) {
        name = infoN.getName();
        score = infoN.getScore();
    }
}