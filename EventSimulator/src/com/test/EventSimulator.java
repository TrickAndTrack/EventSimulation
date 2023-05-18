/**
 * 
 */
/**
 * @author nahush
 *
 */

package com.test;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class EventSimulator {

	private final Map<String, Integer> MapOutPut;
    private final Random random;

    public EventSimulator(Map<String, Integer> MapOutPut) {
        this.MapOutPut = MapOutPut;
        this.random = new Random();
    }

    
    /**
     * 
     * @generateOutPut() method uses a random number generator to generate an output 
     * based on the given probabilities. The probabilities are weighted, and the random number is compared
     */
    
    
    public String generateOutPut() {
        int totalWeight = MapOutPut.values().stream().mapToInt(Integer::intValue).sum();
        int randomNum = random.nextInt(totalWeight) + 1;
        int cumulativeWeight = 0;

        for (Map.Entry<String, Integer> entry : MapOutPut.entrySet()) {
            cumulativeWeight += entry.getValue();
            if (randomNum <= cumulativeWeight) {
                return entry.getKey();
            }
        }
        /**
         * This should not happen if the probabilities are correctly specified
         *
         */

        throw new IllegalStateException("Unable to generate an outcome.");
    }
    public static void main(String[] args) {
    	
    	/**
         * 
         * Examples 1: Rolling of a six-faced biased dice
         *
         */
       Map<String, Integer> diceOutput = new LinkedHashMap<>();
       diceOutput.put("1", 10);
       diceOutput.put("2", 30);
       diceOutput.put("3", 15);
       diceOutput.put("4", 15);
       diceOutput.put("5", 30);
       diceOutput.put("6", 0);

        EventSimulator diceSimulator = new EventSimulator(diceOutput);

       
        /**
         * Count the occurrences of each dice result 
         * for Rolling of a six-faced biased dice
         *
         */
        Map<String, Integer> diceResults = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            String outcome = diceSimulator.generateOutPut();
            diceResults.put(outcome, diceResults.getOrDefault(outcome, 0) + 1);
        }
        /**
         * Print the observed probability distribution
         *
         */

        System.out.println("Dice Results:");
        for (Map.Entry<String, Integer> entry : diceResults.entrySet()) {
            String outcome = entry.getKey();
            int frequency = entry.getValue();
            double probability = (double) frequency / 1000 * 100;
            System.out.println(outcome + ": " + frequency + " occurrences (" + probability + "%)");
        }

        /**
         * Examples 2: Flipping of a coin
         *
         */
        
        Map<String, Integer> coinOutput = new LinkedHashMap<>();
        coinOutput.put("Head", 35);
        coinOutput.put("Tail", 65);

        EventSimulator eventSimulator = new EventSimulator(coinOutput);

        /**
         * As per rule number 4 Generating 1000 occurrences and count the frequency of each outcome
         *for Flipping of a coin
         */
        
        Map<String, Integer> coinResults = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            String coinOutputTwo = eventSimulator.generateOutPut();
            coinResults.put(coinOutputTwo, coinResults.getOrDefault(coinOutputTwo, 0) + 1);
        }

        /**
         * Print the frequency distribution
         * Rule No 1 Probabilities given are as integers and percentages.
         *
         */
        System.out.println("\nCoin Results:");
        for (Map.Entry<String, Integer> entry : coinResults.entrySet()) {
            String outPut = entry.getKey();
            int frequency = entry.getValue();
            double probability = (double) frequency / 1000 * 100;
            System.out.println(outPut + ": " + frequency + " occurrences (" + probability + "%)");
        }
    }
    
}