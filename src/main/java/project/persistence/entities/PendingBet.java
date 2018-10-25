package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Postit Note itself.
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
    private int oddsSender;
    private int oddsReceiver;
    private int amountSender;
    private int amountReceiver;
    private String dateAndTimeCreated;
    private String dateAndTimeResolve;

    private boolean acceptSender;
    private boolean acceptReceiver;


    public PendingBet() {
    }

    public PendingBet(String receiver, String title, String description) {
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
                ", dateAndTimeResolve='" + dateAndTimeResolve + '\'' +
                ", acceptSender=" + acceptSender +
                ", acceptReceiver=" + acceptReceiver +
                '}';
    }
}
