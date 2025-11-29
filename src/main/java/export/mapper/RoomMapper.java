package export.mapper;

import export.dto.RoomCsvDto;
import rooms.LabRoom;
import rooms.LectureRoom;
import rooms.Room;

public class RoomMapper implements MapperInterface{

    public static RoomCsvDto flatten(Room room) {
        String type = (room instanceof LabRoom) ? "lab" : "lecture";

        return new RoomCsvDto(
                room.getRoomID(),
                room.getMaxCapacity(),
                type
        );
    }

    public static Room inflate(RoomCsvDto roomCsvDto) {

        if (roomCsvDto.getType().equals("lab")) {
            return new LabRoom(roomCsvDto.getRoomId(), roomCsvDto.getType(), roomCsvDto.getCapacity());
        }else if (roomCsvDto.getType().equals("lecture")) {
            return new LectureRoom(roomCsvDto.getRoomId(), roomCsvDto.getType(), roomCsvDto.getCapacity());
        }
        throw new IllegalArgumentException("Unknown room type: " + roomCsvDto.getType());
    }
}
