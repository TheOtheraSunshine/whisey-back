package com.tvz.diplomski.wisey.Subscription;

import com.tvz.diplomski.wisey.Student.Student;
import lombok.Data;

import java.util.Date;

@Data
public class SubscriptionDTO {
    private final Long idSubscription;
    private final boolean paid;
    private final Date from;
    private final Date to;
    private final Student student;
    private final Date paidDate;
}
