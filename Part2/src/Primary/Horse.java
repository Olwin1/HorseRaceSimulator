package Primary;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The [Horse] class is used to provide all the various different methods
 * and instance variables related to an individual race horse.
 * 
 * @author Oliver Munn
 * @version 2.0
 */
public class Horse {
    /////////////////////////////////////////
    // Instance variables for horse class. //
    /////////////////////////////////////////

    // Constructor-initialised fields

    /// Used to determine how the horse is represented on the screen. 
    private char horseSymbol;

    /// The name of the horse that will be announced upon victory.  
    private String horseName;

    /// Determines the speed of the horse and likelihood of falling.  (from 0-1)
    /// 
    /// - A higher confidence means the horse runs faster but is also more prone to falling.  
    /// - A lower confidence means the horse runs slower but is more stable.  

    private double horseConfidence;

    // Implicitly initialised fields

    /// A field that determines weather or not the horse has fallen.  
    private boolean hasFallen = false;

    /// A field that is used to store the horse's progress during the race.  
    private int distanceTravelled = 0;

    /// Determines the colour of the horse that will race
    private HorseColour horseColour;

    private boolean hasSaddle = false;

    /// Keeps track of the previous victories (First index is oldest - last index is most recent win)
    private List<Integer> recentTrends = new ArrayList<>();

    /////////////////////////////////////
    // Horse class method definitions. //
    /////////////////////////////////////

    // Constructor of class Horse
    /**
     * Creates a new instance of [Horse] taking three arguments: a [horseSymbol],
     * [horseName], and [horseConfidence]
     * 
     * @param horseSymbol     Will be used to determine how the horse is represented
     *                        on the screen.
     * @param horseName       Is used as the name of the horse.
     * @param horseConfidence Determines the speed of the horse and likelihood of
     *                        falling.
     * 
     * @throws IllegalArgumentException Must be a value between 0 and 1 otherwise an
     *                                  [IllegalArgumentException] is thrown.
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence, HorseColour colour)
            throws IllegalArgumentException {
        if (horseConfidence > 1 || horseConfidence < 0) {
            throw new IllegalArgumentException("Confidence must be a value between 0 and 1.");
        }

        // Set the provided arguments to the corresponding instance variables
        this.horseSymbol = horseSymbol;
        this.horseConfidence = horseConfidence;
        this.horseName = horseName;
        this.horseColour = colour;

    }

    // Generic methods of class Horse

    /// Gets the horse's colour
    public HorseColour getColour() {
        return this.horseColour;
    }

    /// Sets the [hasFallen] flag to true.  
    public void fall() {
        this.hasFallen = true;
    }

    /**
     * Gets the [horseConfidence] field.
     * 
     * A higher confidence means the horse runs faster but is also more prone to
     * falling.
     * A lower confidence means the horse runs slower but is more stable.
     * 
     * @return The horse confidence.
     */
    public double getConfidence() {
        return this.horseConfidence;
    }

    /**
     * Gets the total distance the horse has travelled throughout the race.
     * 
     * @return The distance travelled.
     */
    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    /**
     * Gets the horse's name.
     * 
     * @return The horse's name.
     */
    public String getName() {
        return this.horseName;
    }

    /**
     * Gets the symbol that is used to represent the horse during the race.
     * 
     * @return The horse's racing symbol.
     */
    public char getSymbol() {
        return this.horseSymbol;
    }

    /**
     * Gets a boolean denoting whether the horse should have a saddle or not.
     * 
     * @return a boolean
     */
    public boolean getSaddle() {
        return this.hasSaddle;
    }

    /**
     * Resets the horse's position in the race,
     * sending them back to the start by setting the [distanceTravelled] to 0
     * This will also reset their [hasFallen] flag.
     */
    public void goBackToStart() {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    /**
     * Gets the flag that determines if the horse has fallen or not.
     * 
     * @return A boolean [hasFallen] indicator.
     */
    public boolean hasFallen() {
        return this.hasFallen;
    }

    /**
     * Moves the horse forwards 1 by
     * incrementing [distanceTravelled] by 1.
     */
    public void moveForward() {
        this.distanceTravelled += 1;
    }

    /**
     * Setter to set a new horse confidence.
     * 
     * @param newConfidence
     *                      A higher confidence means the horse runs faster but is
     *                      also more prone to falling.
     *                      A lower confidence means the horse runs slower but is
     *                      more stable.
     * 
     * @throws IllegalArgumentException If number is out of range. Must be a double
     *                                  between 0 and 1.
     */
    public void setConfidence(double newConfidence) throws IllegalArgumentException {
        if (newConfidence > 1 || newConfidence < 0) {
            throw new IllegalArgumentException("Confidence must be a value between 0 and 1.");
        }
        this.horseConfidence = newConfidence;
    }

    /**
     * Setter to set a new horse symbol.
     * This is used to change the symbol that is used to represent the horse during
     * the race.
     * 
     * @param newSymbol A character parameter that will replace the existing horse
     *                  symbol on the screen.
     */
    public void setSymbol(char newSymbol) {
        this.horseSymbol = newSymbol;
    }

    /**
     * Setter to set a new horse name.
     * This is used to change the name that is used to represent the horse during
     * the race.
     * 
     * @param newName A string parameter that will replace the existing horse
     *                name on the screen.
     */
    public void setName(String newName) {
        this.horseName = newName;
    }

    /**
     * Setter to set a new horse colour.
     * This is used to change the colour that is used to represent the horse during
     * the race.
     * 
     * @param newColour A [HorseColour] parameter that will replace the existing
     *                  horse
     *                  colour on the screen.
     */
    public void setColour(HorseColour newColour) {
        this.horseColour = newColour;
    }

    /**
     * Sets a boolean denoting whether the horse should have a saddle or not.
     * Will update the horse confidence to reflect this change
     * 
     * @param newHasSaddle the new value to use
     */
    public void setSaddle(boolean newHasSaddle) {
        // If the value has changed then update the horse confidence
        if (this.hasSaddle != newHasSaddle) {
            if (newHasSaddle) {
                // If the saddle is added then increase the confidence by 0.025 (or less if it
                // reaches 1)
                try {
                    setConfidence(horseConfidence + 0.025);
                } catch (IllegalArgumentException e) {
                    setConfidence(1);
                }
            } else {
                // If the saddle is removed then decrease the confidence by 0.025 (or less if it
                // reaches 0)
                try {
                    setConfidence(horseConfidence - 0.025);
                } catch (IllegalArgumentException e) {
                    setConfidence(0);
                }
            }
        }
        this.hasSaddle = newHasSaddle;
    }

    /**
     * Simple method to help in resolving an actual colour from a [HorseColour]
     * 
     * @param colour The [HorseColour] that you want to parse
     * @return an actual [Color] instance representing an approximation of the
     *         [HorseColour]
     */
    public static Color parseHorseColour(HorseColour colour) {
        // Declare the parsedColour variable
        Color parsedColour;
        // Switch-case to assign it.
        switch (colour) {
            case HorseColour.BLUE:
                parsedColour = Color.BLUE;
                break;
            case HorseColour.GREEN:
                parsedColour = Color.GREEN;
                break;
            case HorseColour.PURPLE:
                parsedColour = Color.MAGENTA;
                break;
            case HorseColour.RED:
                parsedColour = Color.RED;
                break;
            default:
                parsedColour = Color.darkGray;
                break;

        }
        // Return the approximation of colour back to the caller
        return parsedColour;
    }

    /**
     * Method to get all history of race positions for the horse.
     * Most recent positions will be at the start of the list.
     * Oldest will be towards the end.
     * 
     * @return [List<Integer>] of the positions achieved each race from most recent
     *         to least recent.
     */
    public List<Integer> getRecentTrends() {
        // Return the recent trends back to the caller but reversed to the 1st index is
        // the most recent.
        return this.recentTrends.reversed();
    }

    /**
     * Method to get the position of the horse in their most recent race.
     * 
     * @return an [Integer] denoting the horse's position
     */
    public Integer getMostRecentPosition() {
        if(getRecentTrends().size() == 0) {
            return null;
        }
        return getRecentTrends().getFirst();
    }

    /**
     * Method to add a new position attained by the horse to the history.
     * This should only be called when a race is over to announce the winner to the
     * rest of the program.
     * 
     * @param position an [Integer] denoting the position the horse came in the
     *                 race.
     */
    public void addNewTrend(Integer position) {
        // Add the latest position to the trends
        recentTrends.add(position);
    }

}
