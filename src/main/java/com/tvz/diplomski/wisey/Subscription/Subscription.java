package com.tvz.diplomski.wisey.Subscription;

import com.tvz.diplomski.wisey.Student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subscription")
    private Long idSubscription;

    @Column(name = "paid")
    private boolean paid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fromDT")
    private Date from;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "toDT")
    private Date to;

    @OneToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paidDT")
    private Date paidDate;
}
