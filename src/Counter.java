/**
 * Counter class.
 */
public class Counter {
    private int counter = 0;

    // add number to current count.

    /**
     * increase add number for the counting.
     *
     * @param number the add number
     */
    public void increase(int number) {
        counter += number;
    }

    // subtract number from current count.

    /**
     * decrease less number for the counting.
     *
     * @param number the less number
     */
    public void decrease(int number) {
        counter -= number;
        if (counter < 0) {
            counter = 0;
        }
    }

    // get current count.

    /**
     * getValue return the number counting.
     *
     * @return Int value
     */
    public int getValue() {
        return counter;
    }
}