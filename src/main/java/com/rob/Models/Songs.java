package com.rob.Models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Songs {
    @XmlElement(name="song")
    List<Song> songsList = new ArrayList<Song>();

    int counter;





    public List<Song> getSongList() {
        return songsList;
    }

    public void setSongList(List<Song> songList) {
        this.songsList = songList;
    }
}
