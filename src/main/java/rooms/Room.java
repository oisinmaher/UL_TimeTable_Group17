package rooms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Room class.
 * Contains:
 *      - Maximum capacity of a room
 *      - roomID, or the room number
 *      - room type (Lecture room, Lab room, Tutorial room)
 *      - Map of roomId to room object
 *      - Map of roomId to set of room times that have already been used
 */
public abstract class Room
{
    protected int maxCapacity; 
    private String roomID;
    protected String roomType;
    protected static Map<String, Room> allRooms = new HashMap<>();
    protected static Map<String, Set<String>> roomTimesUsed = new HashMap<>();

    /**
     * Constructor that
     * @param roomID room number
     * @param roomType type of room
     * @param maxCapacity maximum number of students that the room can hold
     * @throws IllegalArgumentException if the given room ID is already associated with another room
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

    /**
     * Constructor that doesn't take a maximum capacity for the room
     * @param roomID room ID number
     * @param roomType type of room
     * @throws IllegalStateException if the given room ID is already associated with another room
     */
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

    /**
     * Checks if a given roomID has already been used (each one must be unique)
     * @param roomID room ID number
     * @return true if the given roomID is unique, false otherwise
     */
    public static boolean containsRoom(String roomID){
        roomID = roomID.toLowerCase();
        return allRooms.containsKey(roomID);
    }

    /**
     * Gets the Room using a roomId
     * @param roomID room ID number
     * @throws IllegalArgumentException if the given roomId could not find the corresponding Room
     * @return Room object from a given roomId
     */
    public static Room getRoomFromId(String roomID){
        roomID = roomID.toLowerCase();
        if(containsRoom(roomID)){
            return allRooms.get(roomID);
        }
        throw new IllegalArgumentException("Room Id doesn't exist");
    }

    /**
     * Gets the room number / ID
     * @return roomID
     */
    public String getRoomID()
    {
        return this.roomID; 
    }

    /**
     * Gets the room number / ID
     * @param roomID ID associated with a room
     */
    public void setRoomID(String roomID)
    {
        this.roomID = roomID; 
    }

    /**
     * Sets the max capacity of the room. Lab and Lecture room override this method.
     * @param maxCapacity maximum number of students that the room can hold
     */
    public abstract void setMaxCapacity(int maxCapacity);

    /**
     * Gets the max capacity of the room
     * @return the maximum number of students that the room can hold
     */
    public int getMaxCapacity()
    {
        return this.maxCapacity;
    }

    /**
     * Sets the room type if given the correct room type
     * @param roomType the type of room
     * @throws IllegalArgumentException if the given room type is null or empty
     * @throws IllegalArgumentException if the incorrect room type was given
     */
    public void setRoomType(String roomType) {
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
     * Gets the type of room
     * @return room type
     */
    public String getRoomType()
    {
        return this.roomType; 
    }

    /**
     * Gets the types of classes that the room can hold.
     * @param roomType type of room
     * @return the type of classes (Lectures, Labs, Tutorials) that can be facilitated in a room
     */
    public abstract String canHold(String roomType);

    /**
     * @return roomID
     */
    @Override
    public String toString(){
        return roomID;
    }
}
