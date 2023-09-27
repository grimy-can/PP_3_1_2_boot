package preproject.boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "alias")
    private String alias;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "is_alive")
    private Boolean isAlive;

    @Column(name = "regdate")
    private String regdate;

    @Column(name = "edited")
    private String edited;
}