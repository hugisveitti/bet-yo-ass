package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the User itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "bet") // If you want to specify a table name, you can do so here
public class Bet {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bet_id")
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
    private String dateAndTimeCreated;
    private String dateAndTimeResolve;



    private boolean senderResolved;
    private boolean receiverResolved;

    private boolean hasBeenResolved;

    //remember who won and lost
    private String winnerId;
    private String loserId;





    public Bet(){}

    public Bet(String receiver, String title, String description) {
        this.receiver = receiver;
        this.title = title;
        this.description = description;
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

    public void setOddsSender(int oddsSender) {
        this.oddsSender = oddsSender;
    }

    public double getOddsReceiver() {
        return oddsReceiver;
    }

    public void setOddsReceiver(int oddsReceiver) {
        this.oddsReceiver = oddsReceiver;
    }

    public double getAmountSender() {
        return amountSender;
    }

    public void setAmountSender(int amountSender) {
        this.amountSender = amountSender;
    }

    public double getAmountReceiver() {
        return amountReceiver;
    }

    public void setAmountReceiver(int amountReceiver) {
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


    public boolean isSenderResolved() {
        return senderResolved;
    }

    public void setSenderResolved(boolean senderResolved) {
        if(receiverResolved){
            this.hasBeenResolved = true;
        }
        this.senderResolved = senderResolved;
    }

    public boolean isReceiverResolved() {
        return receiverResolved;
    }

    public void setReceiverResolved(boolean receiverResolved) {
        if(senderResolved){
            this.hasBeenResolved = true;
        }
        this.receiverResolved = receiverResolved;
    }

    public boolean isHasBeenResolved() {
        return hasBeenResolved;
    }

    public void setHasBeenResolved(boolean hasBeenResolved) {
        this.hasBeenResolved = hasBeenResolved;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public String getLoserId() {
        return loserId;
    }

    public void setLoserId(String loserId) {
        this.loserId = loserId;
    }

    @Override
    public String toString(){
        return "Bet name " + this.title + " between " + this.sender + " and " + this.receiver;
    }

}