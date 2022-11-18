package kr.ac.jbnu.se.hackathon_project;

import java.io.Serializable;
import java.sql.Timestamp;

public class MatchData implements Serializable {
    private String event;
    private String player1;
    private String player2;
    private String place;
    private String time;

    public void setEvent(String event){
        this.event = event;
    }

    public void setPlayer1(String player1){
        this.player1 = player1;
    }

    public void setPlayer2(String player2){
        this.player2 = player2;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public void setTime(String time){
        this.time = time;
    }


    public String getEvent(){
        return this.event;
    }

    public String getPlayer1(){
        return this.player1;
    }

    public String getPlayer2(){
        return this.player2;
    }

    public String getPlace(){
        return this.place;
    }

    public String getTime(){
        return this.time;
    }
}
