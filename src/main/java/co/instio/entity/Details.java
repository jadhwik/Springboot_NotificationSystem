package co.instio.entity;

import co.instio.enums.Status;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter

@Table(name="details")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String message;
    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;
}
