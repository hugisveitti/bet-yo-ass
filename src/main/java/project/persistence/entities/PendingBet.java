package project.persistence.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

/**
 * The class for the PendingBet Note itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "pendingBet") // If you want to specify a table name, you can do so here
public class PendingBet {


    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pending_bet_id")
    private Long id;

    private String receiver;
    private Long receiverID;
    private String sender;
    private Long senderID;
    private String title;
    private String description;
    private double oddsSender;
    private double oddsReceiver;
    private double amountSender;
    private double amountReceiver;

//    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private String dateAndTimeCreated;



    //must use datetime-local in html form or use time and date html
    private String dateAndTimeResolve;
//    private String dateResolve;
//
//    private String timeResolve;

    private boolean acceptSender;
    private boolean acceptReceiver;


    public PendingBet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getReceiverID() {return receiverID;}

    public void setReceiverID(Long receiverID) {this.receiverID = receiverID;}

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getSenderID() {return senderID;}

    public void setSenderID(Long senderID) {this.senderID = senderID;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOddsSender() {
        return oddsSender;
    }

    public void setOddsSender(double oddsSender) {
        this.oddsSender = oddsSender;
    }

    public double getOddsReceiver() {
        return oddsReceiver;
    }

    public void setOddsReceiver(double oddsReceiver) {
        this.oddsReceiver = oddsReceiver;
    }

    public double getAmountSender() {
        return amountSender;
    }

    public void setAmountSender(double amountSender) {
        this.amountSender = amountSender;
    }

    public double getAmountReceiver() {
        return amountReceiver;
    }

    public void setAmountReceiver(double amountReceiver) {
        this.amountReceiver = amountReceiver;
    }

    public String getDateAndTimeCreated() {
        return dateAndTimeCreated;
    }

    public void setDateAndTimeCreated(String dateAndTimeCreated) {
        this.dateAndTimeCreated = dateAndTimeCreated;
    }

    public String getDateAndTimeResolve() {
        return dateAndTimeResolve;
    }

    public void setDateAndTimeResolve(String dateAndTimeResolve) {
        this.dateAndTimeResolve = dateAndTimeResolve;
    }

    public boolean isAcceptSender() {
        return acceptSender;
    }

    public void setAcceptSender(boolean acceptSender) {
        this.acceptSender = acceptSender;
    }

    public boolean isAcceptReceiver() {
        return acceptReceiver;
    }

    public void setAcceptReceiver(boolean acceptReceiver) {
        this.acceptReceiver = acceptReceiver;
    }


    public boolean hasUserAcceptedPendingBet(User user){
        return (user.getUsername().equals(this.getSender()) && this.isAcceptSender() ) || (user.getUsername().equals(this.getReceiver()) && this.isAcceptReceiver());
    }


    //the next four methods are used by the jsp.
    public double getOpponentAmount(String username){
        if(username.equals(getSender())){
            return getAmountReceiver();
        } else {
            return getAmountSender();
        }
    }

    public double getOppenentOdds(String username){
        if(username.equals(getSender())){
            return getOddsReceiver();
        } else {
            return getOddsSender();
        }
    }

    public double getYourAmount(String username){
        if(username.equals(getSender())){
            return getAmountSender();
        } else {
            return getAmountReceiver();
        }
    }

    public double getYourOdds(String username){
        if(username.equals(getSender())){
            return getOddsSender();
        } else {
            return getOddsReceiver();
        }
    }

    public String getOpponent(String username){
        if(username.equals(getSender())){
            return getReceiver();
        } else {
            return getSender();
        }
    }


//    public String getDateResolve() {
//        return dateResolve;
//    }
//
//    public void setDateResolve(String dateResolve) {
//        this.dateResolve = dateResolve;
//    }
//
//    public String getTimeResolve() {
//        return timeResolve;
//    }
//
//    public void setTimeResolve(String timeResolve) {
//        this.timeResolve = timeResolve;
//    }


    @Override
    public String toString() {
        return "PendingBet{" +
                "id=" + id +
                ", receiver='" + receiver + '\'' +
                ", receiverID='" + receiverID+ '\'' +
                ", sender='" + sender + '\'' +
                ", senderID='" + senderID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", oddsSender=" + oddsSender +
                ", oddsReceiver=" + oddsReceiver +
                ", amountSender=" + amountSender +
                ", amountReceiver=" + amountReceiver +
                ", dateAndTimeCreated='" + dateAndTimeCreated + '\'' +
                ", acceptSender=" + acceptSender +
                ", acceptReceiver=" + acceptReceiver +
                '}';
    }
}
