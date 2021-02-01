package com.tvz.diplomski.wisey.Subscription;

import com.tvz.diplomski.wisey.Student.Student;
import com.tvz.diplomski.wisey.Student.StudentDTO;
import com.tvz.diplomski.wisey.Student.StudentRequest;
import com.tvz.diplomski.wisey.Volunteer.VolunteerDTO;
import com.tvz.diplomski.wisey.Volunteer.VolunteerRequest;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    List<SubscriptionDTO> findAll();

    Optional<SubscriptionDTO> create(SubscriptionRequest subscriptionRequest) throws Exception;

    SubscriptionDTO findByStudent(Student student);

    List<SubscriptionDTO> findAllByStudent(Student student);

    Optional<SubscriptionDTO> update(Long idSubscription, SubscriptionRequest subscriptionRequest);

    void deleteById(Long idSubscription);
}
