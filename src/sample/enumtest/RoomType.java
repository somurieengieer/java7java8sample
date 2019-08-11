package sample.enumtest;

public enum RoomType {
    NORMAL(new NormalRoom()),
    SPECIAL(new SpecialRoom());

    Room room;

    RoomType(Room room) {
        this.room = room;
    }
}
