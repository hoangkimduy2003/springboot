package com.project1.controller;

import com.project1.dto.SearchDTO;
import com.project1.dto.TicketDTO;
import com.project1.service.impl.DepartmentService;
import com.project1.service.impl.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/viewInput")
    public String viewInput(Model model) {
        model.addAttribute("departmentList",departmentService.getAll(0).getData());
        model.addAttribute("ticket",new TicketDTO());
        return "ticket";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("ticket") TicketDTO ticketDTO){
        ticketService.create(ticketDTO);
        return "redirect:/viewTicket";
    }

//    @PostMapping("/search")
//    public String search(@ModelAttribute SearchDTO searchDTO,
//                         Model model){
//        return "redirect:/viewTicket";
//    }
}
