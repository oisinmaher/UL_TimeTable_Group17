package rooms;

/**
 * LabRoom class.
 *  - Everything in this class is inherited from Room, with 2 overridden methods
 */
public class LabRoom extends Room{

    /**
     * Constructor that inherits all of its instance variables from Room
     * @param roomID room ID number
     * @param roomType type of room
     * @param maxCapacity maximum number of students that this lab room can hold
     */
    public LabRoom(String roomID, String roomType, int maxCapacity) {
        super(roomID, roomType, maxCapacity);
    }

    /**
     * Sets the maximum capacity of a lab room within the range of 1 - 1000
     * @param maxCapacity maximum number of students that the room can hold
     * @throws IllegalArgumentException if maxCapacity is less than or equal to 0
     * @throws IllegalArgumentException if maxCapacity is greater than 1000
     */
    @Override
    public void setMaxCapacity(int maxCapacity) {
        try {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be zero or negative.");
        } 
        if (maxCapacity > 1000) {
            throw new IllegalArgumentException("Capacity cannot exceed 1000.");
        }
        this.maxCapacity = maxCapacity;
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Gets the types of classes that the lab room can hold.
     * @param roomType type of room
     * @throws IllegalArgumentException if the given room type is null or empty
     * @throws IllegalArgumentException if the incorrect room type is given
     * @return the type of classes (Lectures, Labs, Tutorials) that can be facilitated in a lab room
     */
    @Override
    public String canHold(String roomType) {
        try {
            if (roomType == null || roomType.isEmpty()) {
                throw new IllegalArgumentException("rooms.Room type cannot be empty.");
            }

            // Only "Lab rooms.Room" allowed (case-insensitive)
            if (!roomType.equalsIgnoreCase("Lab rooms.Room")) {
                throw new IllegalArgumentException("Invalid room type for this class. Must be 'Lab rooms.Room'.");
            }

            return "Lab Session";

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null; 
        }
    }
}
