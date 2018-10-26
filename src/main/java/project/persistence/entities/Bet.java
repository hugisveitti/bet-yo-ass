package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the User itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "bets") // If you want to specify a table name, you can do so here
public class Bet {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiver;
    private Long receiverID;
    private String sender;
    private Long senderID;
    private String title;
    private String description;
    private int oddsSender;
    private int oddsReceiver;
    private int amountSender;
    private int amountReceiver;
    private String dateAndTimeCreated;
    private String dateAndTimeResolve;


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

    public int getOddsSender() {
        return oddsSender;
    }

    public void setOddsSender(int oddsSender) {
        this.oddsSender = oddsSender;
    }

    public int getOddsReceiver() {
        return oddsReceiver;
    }

    public void setOddsReceiver(int oddsReceiver) {
        this.oddsReceiver = oddsReceiver;
    }

    public int getAmountSender() {
        return amountSender;
    }

    public void setAmountSender(int amountSender) {
        this.amountSender = amountSender;
    }

    public int getAmountReceiver() {
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

    @Override
    public String toString(){
        return "Bet name " + this.title + " between " + this.sender + " and " + this.receiver;
    }

}