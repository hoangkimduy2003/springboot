package com.project2.jobscheduler;

import com.project2.entity.User;
import com.project2.reponsitory.UserReponsitory;
import com.project2.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class JobSchedule {
    @Autowired
    private UserReponsitory userRepo;

    @Autowired
    private EmailService emailService;

//    @Scheduled(fixedDelay = 30000)
//    public void sendEmail(){
//        log.info("send success");
//
//    }

    // giây - phút - giờ - ngày - tháng - thứ
//    @Scheduled(cron = "0 33 10 * * *")
//    public void hello() {
//        log.info("hello");
//    }
//
//    @Scheduled(cron = "2 * * * * *")
//    public void morning() {
//        Calendar cal = Calendar.getInstance();
//        int day = cal.get(Calendar.DATE);
//        int month = cal.get(Calendar.MONTH) + 1;
//        List<User> list = userRepo.searchByBirthDate(day,month);
//        for(User x : list){
//            emailService.testEmail(x.getName());
//           log.info("Send success");
//        }
//    }

}
