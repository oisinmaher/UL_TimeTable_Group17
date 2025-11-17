package src.main.java.rooms;

/**
 *
 */
public abstract class Room
{
    protected int maxCapacity; 
    private String roomID; 
    protected String roomType;

    /**
     *
     * @param roomID
     * @param roomType
     * @param maxCapacity
     */
    public Room(String roomID, String roomType, int maxCapacity){
        this.roomID = roomID; 
        this.roomType = roomType; 
        this.maxCapacity = maxCapacity; 
    }

    /**
     *
     * @return
     */
    public String getRoomID()
    {
        return this.roomID; 
    }

    /**
     *
     * @param roomID
     */
    public void setRoomID(String roomID)
    {
        this.roomID = roomID; 
    }

    /**
     *
     * @param maxCapacity
     */
    public abstract void setMaxCapacity(int maxCapacity);

    /**
     *
     * @return
     */
    public int getMaxCapacity()
    {
        return this.maxCapacity;
    }

    /**
     *
     * @param roomType
     */
    public void setRoomType(String roomType)
    {
        try {
        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("rooms.Room type cannot be empty.");
        }

        // Accept only "Lab rooms.Room" or "Lecture rooms.Room" (case insensitive)
        if (!roomType.equalsIgnoreCase("Lab rooms.Room") &&
            !roomType.equalsIgnoreCase("Lecture rooms.Room")) {
            throw new IllegalArgumentException("Invalid room type. Must be 'Lab rooms.Room' or 'Lecture rooms.Room'.");
        }

        this.roomType = roomType;
        System.out.println("rooms.Room type successfully set to: " + roomType);

    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    }

    }

    /**
     *
     * @return
     */
    public String getRoomType()
    {
        return this.roomType; 
    }

    /**
     *
     * @param roomType
     * @return
     */
    public abstract String canHold(String roomType); 


}
