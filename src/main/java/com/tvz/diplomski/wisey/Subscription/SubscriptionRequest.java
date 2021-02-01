package com.tvz.diplomski.wisey.Subscription;

import com.tvz.diplomski.wisey.Student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {
    private Long idSubscription;
    private boolean paid;
    private Date from;
    private Date to;
    private Long idStudent;
    private Date paidDate;
}
