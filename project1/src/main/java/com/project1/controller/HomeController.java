package com.project1.controller;

import com.project1.dto.DepartmentDTO;
import com.project1.dto.SearchDTO;
import com.project1.entity.Department;
import com.project1.reponsitory.DepartmentReponsitory;
import com.project1.service.impl.DepartmentService;
import com.project1.service.impl.TicketService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/viewTicket")
    public String viewTicket(Model model,
                             @ModelAttribute("searchDTO") SearchDTO searchDTO) {
        searchDTO.setKeyWord(searchDTO.getKeyWord() == null ? "" : searchDTO.getKeyWord());
        searchDTO.setStart(searchDTO.getStart() == null ? null : searchDTO.getStart());

        model.addAttribute("departmentList", departmentService.getAll(0).getData());
        model.addAttribute("ticketList",ticketService.searchByCreatedAt(searchDTO,0).getData());
        model.addAttribute("searchDTO",searchDTO);
        return "homeTicket";
    }

    @GetMapping("/viewDepartment")
    public String viewDepartment(Model model,
                                 @RequestParam(name = "keyWord", required = false) String keyWord) {
        keyWord = keyWord != null ? keyWord : "";
        model.addAttribute("departmentList", departmentService.searchByKeyWordName(keyWord, 0).getData());
        model.addAttribute("keyWord",keyWord);
        return "homeDepartment";
    }
}
