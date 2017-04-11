package org.FuelPoints.entities;

import org.FuelPoints.utilities.HasId;
import org.FuelPoints.utilities.PasswordStorage;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;


    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password) throws PasswordStorage.CannotPerformOperationException {
        this.name = name;
        setPassword(password);
    }

    @Override
    public String getId() {
        return id;
    }

//    public void setId(String id){
//        this.id = id;
//    }

    private String getPasswordHash() {
        return password;
    }

    public void setPassword(String password) throws PasswordStorage.CannotPerformOperationException {
        this.password = PasswordStorage.createHash(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean verifyPassword(String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        return PasswordStorage.verifyPassword(password, getPasswordHash());
    }
}
