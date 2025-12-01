package export.dto;

import com.opencsv.bean.CsvBindByName;

public class RoomCsvDto {

    @CsvBindByName
    private String roomId;

    @CsvBindByName
    private int capacity;

    @CsvBindByName
    private String type;


    public RoomCsvDto() {}

    public RoomCsvDto(String roomId, int capacity, String type) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.type = type.toUpperCase();
    }

    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}