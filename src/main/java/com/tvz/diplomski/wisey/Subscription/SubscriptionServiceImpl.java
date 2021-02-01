package com.tvz.diplomski.wisey.Subscription;

import com.tvz.diplomski.wisey.Student.Student;
import com.tvz.diplomski.wisey.Student.StudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, StudentRepository studentRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<SubscriptionDTO> findAll() {
        return subscriptionRepository.findAll().stream().map(this::mapSubscriptionToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<SubscriptionDTO> create(SubscriptionRequest subscriptionRequest) throws Exception {
        Subscription subscription = null;

            subscription = mapRequestToSubscription(subscriptionRequest);
            if(subscription == null) {
                throw new Exception("Učenik trenutno ima aktivnu pretplatu.");
            }
            else if(subscription.getFrom() == null) {
                throw new Exception("Početni datum mora biti unesen.");
            }

        return Optional.of(subscriptionRepository.save(subscription)).map(this::mapSubscriptionToDTO);
    }

    @Override
    public SubscriptionDTO findByStudent(Student student) {
        Subscription subscription = subscriptionRepository.findByStudent(student);

        return mapSubscriptionToDTO(subscription);
    }

    @Override
    public List<SubscriptionDTO> findAllByStudent(Student student) {
        return subscriptionRepository.findAllByStudent(student).stream().map(this::mapSubscriptionToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<SubscriptionDTO> update(Long idSubscription, SubscriptionRequest subscriptionRequest) {
        Subscription subscription = mapRequestToSubscription(subscriptionRequest);
        subscription.setIdSubscription(idSubscription);
        return Optional.of(subscriptionRepository.save(subscription)).map(this::mapSubscriptionToDTO);
    }

    @Override
    public void deleteById(Long idSubscription) {
        subscriptionRepository.deleteById(idSubscription);
    }

    private SubscriptionDTO mapSubscriptionToDTO(Subscription subscription) {
        return new SubscriptionDTO(
                subscription.getIdSubscription(),
                subscription.isPaid(),
                subscription.getFrom(),
                subscription.getTo(),
                subscription.getStudent(),
                subscription.getPaidDate()
        );
    }

    private Subscription mapRequestToSubscription(SubscriptionRequest subscriptionRequest) {

        Student student = studentRepository.findByIdStudent(subscriptionRequest.getIdStudent());
        Subscription tmpSubscription = subscriptionRepository.findByStudent(student);

        if(subscriptionRequest.getFrom() == null
                && subscriptionRequest.getTo() == null
                && subscriptionRequest.getPaidDate() != null
                && subscriptionRequest.isPaid() == true
        ) {
            return new Subscription(
                    tmpSubscription.getIdSubscription(),
                    subscriptionRequest.isPaid(),
                    tmpSubscription.getFrom(),
                    tmpSubscription.getTo(),
                    student,
                    subscriptionRequest.getPaidDate()
            );
        }

        if(tmpSubscription == null) {
            return new Subscription(
                    subscriptionRequest.getIdSubscription(),
                    subscriptionRequest.isPaid(),
                    subscriptionRequest.getFrom(),
                    subscriptionRequest.getTo(),
                    student,
                    subscriptionRequest.getPaidDate()
            );
        } else {
            return null;
        }

    }
}
