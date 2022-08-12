import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {

    public static final int NO_INDEX_FOUND = -1;
    public static final int[] SAMPLE_ARRAY = {1, 4, 2, 1, 7, 6};

    public static void main(String[] args) {
    }

    private int[] solve(int[] array){
        var solution = new int[array.length];
        for (int i = 0; i < array.length; i++){
            solution[i] = findIndexOfGreatestNearest(array, i);
        }
        return solution;
    }

    private int findIndexOfGreatestNearest(int[] array, int givenIndex) {
        var greatestNearestAtTheRight = findIndexOfGreatestNearestToRight(array, givenIndex);
        var greatestNearestAtTheLeft = findIndexOfGreatestNearestToLeft(array, givenIndex);

        return decideNearest(greatestNearestAtTheLeft, greatestNearestAtTheRight, givenIndex);

    }

    private int decideNearest(int leftIndex, int rightIndex, int givenIndex) {
        if (leftIndex == NO_INDEX_FOUND && rightIndex == NO_INDEX_FOUND){
            return NO_INDEX_FOUND;
        }
        if (leftIndex == NO_INDEX_FOUND){
            return rightIndex;
        }
        if (rightIndex == NO_INDEX_FOUND){
            return leftIndex;
        }
        var distanceToTheLeft = Math.abs(givenIndex - leftIndex);
        var distanceToTheRight = Math.abs(givenIndex - rightIndex);

        if (distanceToTheLeft == distanceToTheRight){
            return leftIndex;
        }
        if (distanceToTheLeft < distanceToTheRight){
            return leftIndex;
        } else {
            return rightIndex;
        }
    }

    private int findIndexOfGreatestNearestToRight(int[] array, int givenIndex) {
        var indexOfNextElementToTheRight = givenIndex + 1;
        for (int i = indexOfNextElementToTheRight; i < array.length; i++) {
            if (array[i] > array[givenIndex]){
                return i;
            }
        }
        return NO_INDEX_FOUND;
    }

    private int findIndexOfGreatestNearestToLeft(int[] array, int givenIndex) {
        var indexOfNextElementToTheRight = givenIndex - 1;
        for (int i = indexOfNextElementToTheRight; i > 0; i--) {
            if (array[i] > array[givenIndex]){
                return i;
            }
        }
        return NO_INDEX_FOUND;
    }

    @Test
    void testSolution() {
        Assertions.assertArrayEquals(new int[] {1, 4, 1, 2, -1, 4}, solve(new int[] {1, 4, 2, 1, 7, 6}));
    }

    @Test
    void testFindTheIndexOfTheGreatestNearestAtTheRight(){
        var result = findIndexOfGreatestNearestToRight(SAMPLE_ARRAY, 0);
        Assertions.assertEquals(1, result, "Works when the number is just in the next spot");

        result = findIndexOfGreatestNearestToRight(SAMPLE_ARRAY, 1);
        Assertions.assertEquals(4, result, "Works when the number is not just in the next spot");

        result = findIndexOfGreatestNearestToRight(SAMPLE_ARRAY, 4);
        Assertions.assertEquals(NO_INDEX_FOUND, result, "Works when that number doesn't exist");
    }

    @Test
    void testFindTheIndexOfTheGreatestNearestAtTheLeft(){
        var result = findIndexOfGreatestNearestToLeft(SAMPLE_ARRAY, 5);
        Assertions.assertEquals(4, result, "Works when the number is just in the next spot");

        result = findIndexOfGreatestNearestToLeft(SAMPLE_ARRAY, 4);
        Assertions.assertEquals(NO_INDEX_FOUND, result, "Works when that number doesn't exist");
    }

    @Test
    void testDecideMostNearestNearest(){
        var result = decideNearest(1, 4, 3);
        Assertions.assertEquals(4, result, "Find when the number is at the right");

        result = decideNearest(1, 4, 2);
        Assertions.assertEquals(1, result, "Find when the number is at the left");
    }

    @Test
    void testFindIndexOfGreatestNearest(){
        var result = findIndexOfGreatestNearest(SAMPLE_ARRAY, 0);
        Assertions.assertEquals(1, result, "");

        result = findIndexOfGreatestNearest(SAMPLE_ARRAY, 2);
        Assertions.assertEquals(1, result, "");

        result = findIndexOfGreatestNearest(SAMPLE_ARRAY, 3);
        Assertions.assertEquals(2, result, "");

        result = findIndexOfGreatestNearest(SAMPLE_ARRAY, 5);
        Assertions.assertEquals(4, result, "");
    }
}
