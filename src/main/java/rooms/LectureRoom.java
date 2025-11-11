package rooms;

public class LectureRoom extends Room{

    public LectureRoom(String roomID, String roomType, int maxCapacity)
    {
        super(roomID, roomType, maxCapacity); 
    }
    @Override
    public void setMaxCapacity(int maxCapacity)
    { 
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

    @Override
    public  String canHold(String roomType)
    {
        try {
            if (roomType == null || roomType.isEmpty()) {
                throw new IllegalArgumentException("rooms.Room type cannot be empty.");
            }

            // Only "Lab rooms.Room" allowed (case-insensitive)
            if (!roomType.equalsIgnoreCase("Lecture rooms.Room")) {
                throw new IllegalArgumentException("Invalid room type for this class. Must be 'Lab rooms.Room'.");
            }

            return "Lecture and Tutorial";

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
}
