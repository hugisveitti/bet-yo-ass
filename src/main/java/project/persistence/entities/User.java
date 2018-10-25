package project.persistence.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;

/**
 * The class for the User itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "betuser") // If you want to specify a table name, you can do so here
public class User implements UserDetails {




    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(unique=true, name="username")
    private String username;

    private String password;


    private String email;

    @Column(name="enabled")
    private Boolean enabled;


    @ManyToMany
    @JoinTable(
            name = "users_pending_bets",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "pending_bet_id", referencedColumnName = "pending_bet_id"))
    private Set<PendingBet> pendingBets;


    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(
                name = "username", referencedColumnName = "username"),
        inverseJoinColumns = @JoinColumn(
                name = "role", referencedColumnName = "role"))
    private Set<Role> roles;



    private float credit;


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


    public Set<PendingBet> getPendingBets() {
        return pendingBets;
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


    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "User " + this.username + " has id " + this.id + " and password " + this.password;
    }



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
}