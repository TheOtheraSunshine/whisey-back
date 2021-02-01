package com.tvz.diplomski.wisey.Subscription;

import com.tvz.diplomski.wisey.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAll();

    Subscription findByIdSubscription(Long idSubscription);

    Subscription findByStudent(Student student);

    List<Subscription> findAllByStudent(Student student);

    void deleteById(Long idSubscription);
}
