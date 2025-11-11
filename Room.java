public abstract class Room
{
    protected int maxCapacity; 
    private String roomID; 
    protected String roomType; 

    public Room(String roomID, String roomType, int maxCapacity){
        this.roomID = roomID; 
        this.roomType = roomType; 
        this.maxCapacity = maxCapacity; 
    }


    public String getRoomID()
    {
        return this.roomID; 
    } 
    public void setRoomID(String roomID)
    {
        this.roomID = roomID; 
    }

    public abstract void setMaxCapacity(int maxCapacity); 
    public int getMaxCapacity()
    {
        return this.maxCapacity;
    }

public void setRoomType(String roomType)
    {
        try {
        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be empty.");
        }

        // Accept only "Lab Room" or "Lecture Room" (case insensitive)
        if (!roomType.equalsIgnoreCase("Lab Room") && 
            !roomType.equalsIgnoreCase("Lecture Room")) {
            throw new IllegalArgumentException("Invalid room type. Must be 'Lab Room' or 'Lecture Room'.");
        }

        this.roomType = roomType;
        System.out.println("Room type successfully set to: " + roomType);

    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    }

    }    public String getRoomType()
    {
        return this.roomType; 
    }    

    public abstract String canHold(String roomType); 


}
