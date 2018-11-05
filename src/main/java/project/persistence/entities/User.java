package project.persistence.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

/**
 * The class for the User itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "user_account") // If you want to specify a table name, you can do so here
public class User implements UserDetails {




    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(unique=true, name="username")
    private String username;

    private String password;

    private double credit;

    @Column(name="enabled")
    private Boolean enabled;


    @ManyToMany
    @JoinTable(
            name = "user_pending_bet",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "pending_bet_id", referencedColumnName = "pending_bet_id"))
    private Set<PendingBet> pendingBets;




    @ManyToMany
    @JoinTable(
            name = "user_bet",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns =  @JoinColumn(
                    name = "bet_id", referencedColumnName = "bet_id"))
    private Set<Bet> bets;


    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(
                name = "username", referencedColumnName = "username"),
        inverseJoinColumns = @JoinColumn(
                name = "role", referencedColumnName = "role"))
    private Set<Role> roles;






    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        //maybe change, 100 to begin with
        this.credit = 100;
        this.enabled = true;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> userRoles = this.getRoles();
        if(userRoles != null)
        {
            for (Role role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    public Set<Bet> getResolvedBets(){
        Set<Bet> resolvedBets = new HashSet<>();
        for(Bet b : getBets()){
            if(b.isHasBeenResolved()){
                resolvedBets.add(b);
            }
        }
        return resolvedBets;
    }

    public Set<Bet> getActiveBets(){
        Set<Bet> activeBets = new HashSet<>();
        for(Bet b : getBets()){
            if(!b.isHasBeenResolved()){
                activeBets.add(b);
            }
        }
        return activeBets;
    }


    public Set<PendingBet> getPendingBets() {
        return pendingBets;
    }

    @Transactional
    public void removePendingBet(PendingBet pendingBet){
        pendingBets.remove(pendingBet);
        for(PendingBet p : pendingBets){
            System.out.println(p);
        }

    }

    public void setPendingBets(Set<PendingBet> pendingBets) {
        this.pendingBets = pendingBets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString(){
        return "User " + this.username + " has id " + this.id + " and password " + this.password;
    }



    //ATH BUA TIL VARIABLES FYRIR THESSI METHODS TIL AD SKILA
    //TODO LAGA THESSA BOLEAN DAEMI
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }

    public void removeCredit(double removeCredit){
        this.credit = this.credit - removeCredit;
    }
}