
package orange;

//import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author damia
 */
public class Org {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Calendar> avaibleHours1 = new ArrayList<Calendar>();
    static ArrayList<Calendar> avaibleHours2 = new ArrayList<Calendar>();
    static ArrayList<String> finalMeetings = new ArrayList<String>();
    public static void main(String[] args) throws InterruptedException {
        //W tym miejscu tworzone są obiekty kalendarz które posiadają trzy pola. Jednym z tych pól jest list obietków zaplanowanych spotkań
        Kalendarz kalendarz = new Kalendarz("09:00", "20:00");
        kalendarz.pm.add(new planned_meeting("09:00", "10:30"));
        kalendarz.pm.add(new planned_meeting("12:00", "13:00"));
        kalendarz.pm.add(new planned_meeting("16:00", "18:30"));
        Kalendarz kalendarz2 = new Kalendarz("10:00", "18:30");
        kalendarz2.pm.add(new planned_meeting("10:00", "11:30"));
        kalendarz2.pm.add(new planned_meeting("12:30", "14:30"));
        kalendarz2.pm.add(new planned_meeting("14:30", "15:00"));
        kalendarz2.pm.add(new planned_meeting("16:00", "17:00"));
        
        //Tutaj podajemy czas trwania spotkania
        int meetingLength = 30;
        setMeetings(kalendarz, kalendarz2, meetingLength);
        for(int c = 0; c < finalMeetings.size(); c++){
            if(c%2==0){
                System.out.println("Proponowany czas rozpoczęcia spotkania: " + finalMeetings.get(c));
            }
            else{
                System.out.println("Proponowany czas zakończenia spotkania: " + finalMeetings.get(c));
            }
            
        }
    }
    
    public static List<Calendar> setMeetings(Kalendarz kalendarz, Kalendarz kalendarz2, int meetingLength) throws InterruptedException
    {
        ArrayList<Calendar> meetings = new ArrayList<Calendar>();
        
        Calendar startJob = parseToDate(kalendarz, "startJob", 1);
        Calendar endJob = parseToDate(kalendarz, "endJob", 1);
        for(int i = 0; i < kalendarz.pm.size(); i++)
        {
            
            Calendar avaibleStartingHour;
            Calendar avaibleEndingHour;
            Calendar avaibleEndingHour2;
            Calendar startMeeting = parseToDate(kalendarz, "start", i);
            Calendar endMeeting = parseToDate(kalendarz, "end", i);
            
            if(i == 0)
            {
                if(startMeeting.get(Calendar.HOUR) != startJob.get(Calendar.HOUR) && 
                        startMeeting.get(Calendar.MINUTE) != startJob.get(Calendar.MINUTE)){
                    avaibleHours1.add(startJob);
                }
                avaibleHours1.add(endMeeting);
            }
            else if(i > 0)
            {
                if(avaibleHours1.get(avaibleHours1.size()-1).get(Calendar.HOUR) == startMeeting.get(Calendar.HOUR) && 
                   avaibleHours1.get(avaibleHours1.size()-1).get(Calendar.MINUTE) == startMeeting.get(Calendar.MINUTE))
                {
                    avaibleHours1.remove(avaibleHours1.size()-1);
                    avaibleHours1.add(endMeeting);
                }
                else
                {
                    avaibleHours1.add(startMeeting);
                    avaibleHours1.add(endMeeting);
                    
                }
 
                if(i+1 == kalendarz.pm.size())
                {
                    if(endMeeting.get(Calendar.HOUR) != endJob.get(Calendar.HOUR))
                    {
                        avaibleHours1.add(endJob);
                    }
                    else if(endMeeting.get(Calendar.HOUR) == endJob.get(Calendar.HOUR))
                    {
                        if(endMeeting.get(Calendar.MINUTE) == endJob.get(Calendar.MINUTE)){
                            continue;
                        }
                        else{
                            avaibleHours1.add(endJob);
                        }
                    }
                    
                }
            }    
        }
        Thread.sleep(100);
        Calendar startJob2 = parseToDate(kalendarz2, "startJob", 1);
        Calendar endJob2 = parseToDate(kalendarz2, "endJob", 1);
        for(int i = 0; i < kalendarz2.pm.size(); i++)
        {
            
            Calendar avaibleStartingHour;
            Calendar avaibleEndingHour;
            Calendar avaibleEndingHour2;
            Calendar startMeeting = parseToDate(kalendarz2, "start", i);
            Calendar endMeeting = parseToDate(kalendarz2, "end", i);
            
            if(i == 0)
            {
                if(startMeeting.get(Calendar.HOUR) != startJob2.get(Calendar.HOUR) && 
                        startMeeting.get(Calendar.MINUTE) != startJob2.get(Calendar.MINUTE)){
                    avaibleHours2.add(startJob2);
                }
                avaibleHours2.add(endMeeting);
            }
            else if(i > 0)
            {
                if(avaibleHours2.get(avaibleHours2.size()-1).get(Calendar.HOUR) == startMeeting.get(Calendar.HOUR) && 
                   avaibleHours2.get(avaibleHours2.size()-1).get(Calendar.MINUTE) == startMeeting.get(Calendar.MINUTE))
                {
                    avaibleHours2.remove(avaibleHours2.size()-1);
                    System.out.println(avaibleHours2.size()-1);
                    avaibleHours2.add(endMeeting);
                }
                else
                {
                    avaibleHours2.add(startMeeting);
                    avaibleHours2.add(endMeeting);
                    
                }

                if(i+1 == kalendarz2.pm.size())
                {
                    if(endMeeting.get(Calendar.HOUR) != endJob2.get(Calendar.HOUR))
                    {
                        avaibleHours2.add(endJob2);
                    }
                    else if(endMeeting.get(Calendar.HOUR) == endJob2.get(Calendar.HOUR))
                    {
                        if(endMeeting.get(Calendar.MINUTE) == endJob2.get(Calendar.MINUTE)){
                            continue;
                        }
                        else{
                            avaibleHours2.add(endJob2);
                        }
                    }
                    
                }
            }    
        }
        Thread.sleep(120);
        for(int i = 0; i < avaibleHours1.size(); i++)
        {
            if(i%2==0)
            {
                for(int ii = 0; ii < avaibleHours2.size(); ii++)
                {
                    
                    if(ii % 2 == 0)
                    {
                        
                        //System.out.println(avaibleHours1.get(i+1).getTime().compareTo(avaibleHours2.get(ii+1).getTime()));
                        if(avaibleHours1.get(i).getTime().before(avaibleHours2.get(ii).getTime()))
                        {
                            if(avaibleHours1.get(i+1).getTime().after(avaibleHours2.get(ii).getTime()) && avaibleHours1.get(i+1).getTime().before(avaibleHours2.get(ii+1).getTime()) || avaibleHours1.get(i+1).getTime().compareTo(avaibleHours2.get(ii+1).getTime()) == 1)
                            {
                                meetings.add(avaibleHours2.get(ii));
                                meetings.add(avaibleHours1.get(i+1));
                                scheduleMeetings(avaibleHours2.get(ii), avaibleHours1.get(i+1), meetingLength);
                                
                            }
                            else if(avaibleHours1.get(i+1).getTime().after(avaibleHours2.get(ii).getTime()) && avaibleHours1.get(i+1).getTime().after(avaibleHours2.get(ii+1).getTime()) || avaibleHours1.get(i+1).getTime().compareTo(avaibleHours2.get(ii+1).getTime()) == 1)
                            {
                                meetings.add(avaibleHours2.get(ii));
                                meetings.add(avaibleHours2.get(i+1));
                                scheduleMeetings(avaibleHours2.get(ii), avaibleHours2.get(ii+1), meetingLength);
                            }
                        }
                        else if(avaibleHours1.get(i).getTime().after(avaibleHours2.get(ii).getTime()) && avaibleHours1.get(i).getTime().before(avaibleHours2.get(ii+1).getTime()))
                        {
                            if(avaibleHours1.get(i+1).getTime().before(avaibleHours2.get(ii+1).getTime()) || avaibleHours1.get(i).getTime().compareTo(avaibleHours2.get(ii+1).getTime()) == 1)
                            {
                                meetings.add(avaibleHours1.get(i));
                                meetings.add(avaibleHours1.get(i+1));
                                scheduleMeetings(avaibleHours1.get(i), avaibleHours1.get(i+1), meetingLength);
                            }
                            else if(avaibleHours1.get(i+1).getTime().after(avaibleHours2.get(ii+1).getTime()) || avaibleHours1.get(i+1).getTime().compareTo(avaibleHours2.get(ii+1).getTime()) == 1)
                            {
                                meetings.add(avaibleHours1.get(i));
                                meetings.add(avaibleHours2.get(ii+1));
                                scheduleMeetings(avaibleHours1.get(i), avaibleHours2.get(ii+1), meetingLength);
                            }
                            
                        }
                        
                    }
                    else
                    {
                        continue;
                    }
                }
                
            }
            else
            {
                continue;
            }
            //Thread.sleep(40);
//            for(int c = 0; i < meetings.size(); i++){
//            System.out.println("Lista meetings: " + meetings.get(c).getTime());
//            }
    }
        

        return meetings;
    }
    public static List<String> scheduleMeetings(Calendar startingHour, Calendar endingHour, int meetingLength){
        int hourCounter = 0;
        String strDate;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        
        while(true){
            
            strDate = dateFormat.format(startingHour.getTime());
            finalMeetings.add(strDate);
            startingHour.add(Calendar.MINUTE, meetingLength);
            hourCounter += meetingLength;
            
            if(startingHour.get(Calendar.HOUR) == endingHour.get(Calendar.HOUR) && startingHour.get(Calendar.MINUTE) == endingHour.get(Calendar.MINUTE))
            {
                strDate = dateFormat.format(endingHour.getTime());
                finalMeetings.add(strDate);
                break;
            }
                       
            if(startingHour.getTime().before(endingHour.getTime()) && !startingHour.getTime().after(endingHour.getTime()) && startingHour.getTime().compareTo(endingHour.getTime()) == -1)
            {

                if(!finalMeetings.contains(strDate) || startingHour.before(endingHour)){
                    strDate = dateFormat.format(startingHour.getTime());
                    finalMeetings.add(strDate);
                }
                   
            }
            if(startingHour.getTime().after(endingHour.getTime())){
                finalMeetings.remove(strDate);
                break;
            }
            
        }
        startingHour.add(Calendar.MINUTE, -hourCounter);
        
        
        return finalMeetings;
    }
    
    public static Calendar parseToDate(Kalendarz kalendarz, String type, int i){
        Calendar date1 = Calendar.getInstance();
        if(type == "start"){
        String[] parts = kalendarz.pm.get(i).start.split(":");
        date1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]) );
        date1.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
        date1.set(Calendar.SECOND, 0);
        return date1;
        }
        else if(type == "end"){
        String[] parts = kalendarz.pm.get(i).end.split(":");
        date1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
        date1.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
        date1.set(Calendar.SECOND, 0);
        }
        else if(type == "startJob"){
        String[] parts = kalendarz.start.split(":");
        date1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
        date1.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
        date1.set(Calendar.SECOND, 0);
        }
        else if(type == "endJob"){
        String[] parts = kalendarz.end.split(":");
        date1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
        date1.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
        date1.set(Calendar.SECOND, 0);
        }
        return date1;
        
    }
    
}
