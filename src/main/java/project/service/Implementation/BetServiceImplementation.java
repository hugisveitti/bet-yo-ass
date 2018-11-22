package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.PendingBet;
import project.persistence.entities.Bet;
import project.persistence.entities.User;
import project.persistence.repositories.BetRepository;
import project.persistence.repositories.PendingBetRepository;
import project.service.BetService;
import project.service.CustomUserDetailsService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.time.ZonedDateTime;

/*
**
* The implementation of the bet service. For both the pending bet's and bet's.
* There maybe a problem in calculating the opponent odds and amount because of
* floating point.
 */

@Service
public class BetServiceImplementation implements BetService {

    private PendingBetRepository pendingBetRepository;
    private BetRepository betRepository;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public BetServiceImplementation(PendingBetRepository pendingBetRepository, BetRepository betRepository, CustomUserDetailsService customUserDetailsService){
        this.pendingBetRepository = pendingBetRepository;
        this.betRepository = betRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    /*
    Pending bet service implementation
     */



    @Override
    public void savePendingBet(PendingBet pendingBet, User sender, User receiver) throws Exception{


        System.out.println(pendingBet);
        if(sender.getCredit() < pendingBet.getAmountSender()){
            throw new Exception("Sender does not have enough credits");
        }

        // remove money from the sender
        sender.removeCredit(pendingBet.getAmountSender());


        //TODO laga
        pendingBet.setDateAndTimeCreated(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));


        //TODO LAGA floating point number
        double odds = pendingBet.getOddsSender();
        double amount = pendingBet.getAmountSender();


        ArrayList<Double> opponentOddsAndAmount = calcOpponentOddsAndAmount(odds, amount);
        double oppOdds = opponentOddsAndAmount.get(0);
        double oppAmount = opponentOddsAndAmount.get(1);


        System.out.println("opp odds " + oppOdds + " opp amount " + oppAmount);
        System.out.println("your odds" + odds + " your amount " + pendingBet.getAmountSender());
        pendingBet.setOddsReceiver(oppOdds);
        pendingBet.setAmountReceiver(oppAmount);

        pendingBet.setAcceptSender(true);
        pendingBet.setAcceptReceiver(false);

        //add to senders pending bets
        Set<PendingBet> senderPendingBets = sender.getPendingBets();
        if(senderPendingBets == null){
            senderPendingBets = new HashSet<>();
        }
        senderPendingBets.add(pendingBet);
        pendingBet.setSender(sender.getUsername());
        pendingBet.setSenderID(sender.getId());

        //add to receiver pending bets
        Set<PendingBet> receiverPendingBets = receiver.getPendingBets();
        if(receiverPendingBets == null){
            receiverPendingBets = new HashSet<>();
        }
        receiverPendingBets.add(pendingBet);

        pendingBet.setReceiverID(receiver.getId());
        pendingBet.setReceiver(receiver.getUsername());



        pendingBetRepository.save(pendingBet);
    }

    @Override
    public void deletePendingBet(PendingBet pendingBet, User currUser){
        ArrayList<User> senderReceiver = findWhoIsSender(currUser, pendingBet);
        User sender = senderReceiver.get(0);
        User receiver = senderReceiver.get(1);

        sender.removePendingBet(pendingBet);
        receiver.removePendingBet(pendingBet);

        pendingBetRepository.delete(pendingBet);
    }



    @Override
    public PendingBet findPendingBetById(Long Id){
        return pendingBetRepository.findOne(Id);
    }


    /*
    Method used when a bet is being countered.
    Removes credit's from current user and put's credit back to the other user's account
    the original amount he betted.
     */
    @Override
    public void counterPendingBet(PendingBet counterPendingBet, User currUser, double counterAmount, double counterOdds) throws Exception{
        ArrayList<User> senderReceiver = findWhoIsSender(currUser, counterPendingBet);
        User sender = senderReceiver.get(0);
        User receiver = senderReceiver.get(1);

        double newReceiverOdds, newReceiverAmount, newSenderOdds, newSenderAmount;
        ArrayList<Double> opponentOddsAndAmount = calcOpponentOddsAndAmount(counterOdds, counterAmount);
        //ef senderinn er buinn accepta tha er receiverinn ad countera
        if(counterPendingBet.isAcceptSender()){

            newReceiverOdds = counterOdds;
            newReceiverAmount = counterAmount;
            newSenderOdds = opponentOddsAndAmount.get(0);
            newSenderAmount = opponentOddsAndAmount.get(1);
            counterPendingBet.setAcceptSender(false);
            counterPendingBet.setAcceptReceiver(true);

            if(receiver.getCredit() < newReceiverAmount){
                throw new Exception("User does not have enough funds to counter this much");
            } else {
                receiver.removeCredit(newReceiverAmount);
                sender.addCredit(counterPendingBet.getAmountSender());
            }
        }   else {

            newReceiverOdds = opponentOddsAndAmount.get(0);
            newReceiverAmount = opponentOddsAndAmount.get(1);
            newSenderOdds = counterOdds;
            newSenderAmount = counterAmount;
            counterPendingBet.setAcceptSender(true);
            counterPendingBet.setAcceptReceiver(false);


            if(sender.getCredit() < newSenderAmount){
                System.out.println("sender trying to be more than he can, must throw some error here");
                throw new Exception("User does not have enough funds to counter this much");
            } else {
                sender.removeCredit(newSenderAmount);
                receiver.addCredit(counterPendingBet.getAmountReceiver());
            }
        }

        counterPendingBet.setOddsReceiver(newReceiverOdds);
        counterPendingBet.setAmountReceiver(newReceiverAmount);
        counterPendingBet.setAmountSender(newSenderAmount);
        counterPendingBet.setOddsReceiver(newSenderOdds);

        pendingBetRepository.save(counterPendingBet);
    }



    /*
    Bet service implementation
     */

    @Override
    public void deleteBet(Bet bet){
        betRepository.delete(bet);
    }


    /*
        Save the bet when both users have accepted the pending bet
        checks if both users have accepted and both users have enough credits
     */
    @Override
    public void saveBet(PendingBet pendingBet, User currUser) throws Exception{
        User sender;
        User receiver;
        ArrayList<User> senderReceiver = findWhoIsSender(currUser,pendingBet);
        sender = senderReceiver.get(0);
        receiver = senderReceiver.get(1);

        if(currUser.getUsername().equals(pendingBet.getSender())){
            if(!pendingBet.isAcceptReceiver()){
                throw new Exception("Both users have to accept the bet.");
            } else {
                if(currUser.getCredit() < pendingBet.getAmountSender()){
                    throw new Exception("Sender not enough credits");
                } else {
                    currUser.removeCredit(pendingBet.getAmountSender());
                }
            }
        } else if(currUser.getUsername().equals(pendingBet.getReceiver())){
            if(!pendingBet.isAcceptSender()){
                throw new Exception("Both users have to accept the bet.");
            } else {
                if(currUser.getCredit() < pendingBet.getAmountReceiver()){
                    throw new Exception("Receiver not enough credits");
                } else {
                    currUser.removeCredit(pendingBet.getAmountReceiver());
                }
            }
        }


        this.deletePendingBet(pendingBet, sender);
        this.deletePendingBet(pendingBet, receiver);

        Bet newBet = new Bet(pendingBet);
        Set<Bet> senderBets = sender.getBets();
        if(senderBets == null){
            senderBets = new HashSet<>();
        }
        senderBets.add(newBet);

        Set<Bet> receiverBets = receiver.getBets();
        if(receiverBets == null){
            receiverBets = new HashSet<>();
        }
        receiverBets.add(newBet);

        betRepository.save(newBet);

    }

    @Override
    public Bet findBetById(Long id){
        return betRepository.findOne(id);
    }


    /*
    Method used when users vote on who won the bet.
    If they disagree on who won, either make them vote again
    or give them their money back.
    Todo: let users know with some kind of notification that the other user has voted.
     */
    @Override
    public void voteBet(Bet voteBet, User currUser, String vote){
        //findWhoIsSender notar pendingbet en ekki bet. Buum til pending bet ur bettinu og setjum
        // bara mikilvaegustu hlutina. Ef vid setjum einn yfirklasa sem heitir Bet
        //og svo thrja undirklasa sem heita active, pending og resolved, tha tharf thetta ekki.
        PendingBet p = new PendingBet();
        p.setReceiver(voteBet.getReceiver());
        p.setSender(voteBet.getSender());
        ArrayList<User> senderReceiver = findWhoIsSender(currUser, p);
        User sender = senderReceiver.get(0);
        User receiver = senderReceiver.get(1);

        if(currUser.getUsername().equals(voteBet.getSender())){
            if(vote.equals("me")){
                voteBet.setVoteSender("sender");
            } else {
                voteBet.setVoteSender("receiver");
            }
            voteBet.setSenderResolved(true);
            if(voteBet.isReceiverResolved()){
                voteBet.setHasBeenResolved(true);
            }
        } else {
            if(vote.equals("me")){
                voteBet.setVoteReceiver("receiver");
            } else {
                voteBet.setVoteReceiver("sender");
            }
            voteBet.setReceiverResolved(true);
            if(voteBet.isSenderResolved()){
                voteBet.setHasBeenResolved(true);
            }
        }

        //if both disagree then either delete bet or make both vote again
        if(voteBet.isHasBeenResolved() && !voteBet.getVoteReceiver().equals(voteBet.getVoteSender())){
            voteBet.setHasBeenResolved(false);
            voteBet.setReceiverResolved(false);
            voteBet.setSenderResolved(false);
            voteBet.setVoteSender(null);
            voteBet.setVoteReceiver(null);


            //todo throw error, let the users know that they disagreed
            System.out.println("receiver and sender disagree on who won!!!!! make an exception throw");
            voteBet.setMessage("You and the other user voted the opposite, you have to vote again!");
        } else if(voteBet.isHasBeenResolved()) {

            System.out.println("sender voted " + voteBet.getVoteSender());
            System.out.println("receiver voted "+ voteBet.getVoteReceiver());


            if(voteBet.getVoteSender().equals("sender")){
                System.out.println("sender won");
                sender.addCredit(voteBet.getAmountSender() + voteBet.getAmountReceiver());
                voteBet.setWinnerId(sender.getId());
                voteBet.setLoserId(receiver.getId());
            } else {
                System.out.println("recevier won");
                receiver.addCredit(voteBet.getAmountSender() + voteBet.getAmountReceiver());
                voteBet.setLoserId(sender.getId());
                voteBet.setWinnerId(receiver.getId());
            }
        }
        betRepository.save(voteBet);
    }

    /*
    Class that uses what the user sent with post to calculate what the opponent should send to match.
    We both calculate here and on the frontend because I think it may be securer.
    Maybe some errors with this calculation. We have to test and find out.
    */
    private ArrayList<Double> calcOpponentOddsAndAmount(double odds, double amount){
        ArrayList<Double> opponentOddsAndAmount = new ArrayList<>();

        double likur = Math.round((1.0 / odds) * 100.0 * 100.0) / 100.0;
        double oppOdds = Math.round((1.0 / (100.0 - likur)) * 100.0 * 100.0) / 100.0;


        double oppAmount = (((amount * odds) / oppOdds) * 100) /100;


        opponentOddsAndAmount.add(oppOdds);
        opponentOddsAndAmount.add(oppAmount);

        return opponentOddsAndAmount;
    }

    /*
    findWhoIsSender can be usefull because sometimes it does not matter who is sending
    or receiving. Not all the methods in this class use this.
     */
    private ArrayList<User> findWhoIsSender(User currUser, PendingBet pendingBet){
        User sender;
        User receiver;
        ArrayList<User> senderReceiver = new ArrayList<>();
        if(currUser.getUsername().equals(pendingBet.getSender())){
            sender = currUser;
            pendingBet.setAcceptSender(true);
            receiver = customUserDetailsService.findByUsername(pendingBet.getReceiver());
            senderReceiver.add(sender);
            senderReceiver.add(receiver);
        } else {
            receiver = currUser;
            pendingBet.setAcceptReceiver(true);
            sender = customUserDetailsService.findByUsername(pendingBet.getSender());
            senderReceiver.add(sender);
            senderReceiver.add(receiver);
        }
        return senderReceiver;
    }
}
