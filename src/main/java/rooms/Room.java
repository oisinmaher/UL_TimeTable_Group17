package rooms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public abstract class Room
{
    protected int maxCapacity; 
    private String roomID;
    protected String roomType;
    protected static Map<String, Room> allRooms = new HashMap<>();
    protected static Map<String, Set<String>> roomTimesUsed = new HashMap<>();

    /**
     * @param roomID
     * @param roomType
     * @param maxCapacity
     */
    // Constructor full parameters (includes max capacity)
    public Room(String roomID, String roomType, int maxCapacity) {
        roomID = roomID.toLowerCase();
        this.roomID = roomID;
        this.roomType = roomType;
        this.maxCapacity = maxCapacity;
        if(containsRoom(roomID)){
           throw new IllegalArgumentException("This room already exists");
        }
        allRooms.put(roomID, this);
        roomTimesUsed.put(roomID, new HashSet<>());
    }
    // Constructor no maxCapacity
    public Room(String roomID, String roomType) {
        roomID = roomID.toLowerCase();
        this.roomID = roomID;
        this.roomType = roomType;
        if(containsRoom(roomID)){
            throw new IllegalArgumentException("This room already exists");
        }
        allRooms.put(roomID, this);
        roomTimesUsed.put(roomID, new HashSet<>());
    }
    public static boolean containsRoom(String roomID){
        roomID = roomID.toLowerCase();
        return allRooms.containsKey(roomID);
    }
    public static Room getRoomFromId(String roomID){
        roomID = roomID.toLowerCase();
        if(containsRoom(roomID)){
            return allRooms.get(roomID);
        }
        throw new IllegalArgumentException("Room Id doesnt exist");
    }

    /**
     * @return
     */
    public String getRoomID()
    {
        return this.roomID; 
    }

    /**
     * @param roomID
     */
    public void setRoomID(String roomID)
    {
        this.roomID = roomID; 
    }

    /**
     * @param maxCapacity
     */
    public abstract void setMaxCapacity(int maxCapacity);

    /**
     * @return
     */
    public int getMaxCapacity()
    {
        return this.maxCapacity;
    }

    public static Map<String, Room> getAllRooms(){
        return allRooms;
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

        // Accept only "Lab rooms.Room" or "Lecture rooms.Room" (case-insensitive)
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

    @Override
    public String toString(){
        return roomID;
    }
}
