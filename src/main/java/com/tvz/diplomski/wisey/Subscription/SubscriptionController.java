package com.tvz.diplomski.wisey.Subscription;

import com.tvz.diplomski.wisey.Student.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private SubscriptionService subscriptionService;
    private StudentService studentService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, StudentService studentService) {
        this.subscriptionService = subscriptionService;
        this.studentService = studentService;
    }

    @GetMapping("/get")
    private List<SubscriptionDTO> getAll() {
        return subscriptionService.findAll();
    }

    @GetMapping("get/{idStudent}")
    private List<SubscriptionDTO> findByIdStudent(@PathVariable final Long idStudent) {
        List<SubscriptionDTO> subscriptionsDTO = null;
        Student student;
        try {
            student = studentService.findByIdStudent(idStudent);
            subscriptionsDTO = subscriptionService.findAllByStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subscriptionsDTO;
    }

    @PostMapping("/add")
    public ResponseEntity addSubscription(@RequestBody final SubscriptionRequest subscriptionRequest) throws Exception {

        if(subscriptionRequest != null) {
            try{
                subscriptionService.create(subscriptionRequest);
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
        return  new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/{idSubscription}")
    public ResponseEntity<SubscriptionDTO> updateStudent(@PathVariable final Long idSubscription, @RequestBody final SubscriptionRequest subscriptionRequest) {
        return subscriptionService.update(idSubscription, subscriptionRequest)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{idSubscription}")
    public void deleteById(@PathVariable Long idSubscription) {
        try {
            subscriptionService.deleteById(idSubscription);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
