package main.entityes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String task;

    private boolean unCheck;
}
