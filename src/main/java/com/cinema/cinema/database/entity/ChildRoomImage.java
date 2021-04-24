package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class ChildRoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long childRoomImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "childRoomId")
    private ChildRoom childRoom;

    public ChildRoomImage() {
    }

    public ChildRoomImage(String image) {
        this.image = image;
    }

    public ChildRoomImage(String image, ChildRoom childRoom) {
        this.image = image;
        this.childRoom = childRoom;
    }

    @Override
    public String toString() {
        return "ChildRoomImage{" +
                "childRoomImageId=" + childRoomImageId +
                ", image='" + image + '\'' +
                '}';
    }

    public long getChildRoomImageId() {
        return childRoomImageId;
    }

    public void setChildRoomImageId(long childRoomImageId) {
        this.childRoomImageId = childRoomImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ChildRoom getChildRoom() {
        return childRoom;
    }

    public void setChildRoom(ChildRoom childRoom) {
        this.childRoom = childRoom;
    }
}
