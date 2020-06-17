package orange;


import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damia
 */
public class Kalendarz {
    String start;
    String end;
    //List<planned_meeting> plannedMeeting = new List<planned_meeting>();
    ArrayList<planned_meeting> pm = new ArrayList<>();
    public Kalendarz(String start, String end){
        this.start=start;
        this.end=end;
    }    
}
