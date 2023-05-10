package com.project1.service.impl;

import com.project1.dto.PageDTO;
import com.project1.dto.TicketDTO;
import com.project1.entity.Ticket;
import com.project1.reponsitory.TicketReponsitory;
import com.project1.service.ITicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketReponsitory ticketReponsitory;

    public TicketDTO convertToDto(Ticket ticket) {
        return new ModelMapper().map(ticket, TicketDTO.class);
    }

    public Ticket convertToEntity(TicketDTO ticketDTO) {
        return new ModelMapper().map(ticketDTO, Ticket.class);
    }

    @Override
    public PageDTO<List<TicketDTO>> getAll(int page) {
        PageDTO<List<TicketDTO>> pageDTO = new PageDTO<>();
        Page<Ticket> pageEntity = ticketReponsitory.findAll(PageRequest.of(page,5));

        pageDTO.setTotalElements(pageEntity.getTotalElements());
        pageDTO.setTotalPages(pageDTO.getTotalPages());

        pageDTO.setData(pageEntity.get().map(u -> convertToDto(u))
                .collect(Collectors.toList()));

        return pageDTO;
    }

    @Override
    public void delete(Integer id) {
        ticketReponsitory.deleteById(id);
    }

    @Override
    public void update(TicketDTO ticketDTO) {
        Ticket ticket = ticketReponsitory.findById(ticketDTO.getId()).orElse(null);
        if (ticket != null) {
            ticket = new ModelMapper().map(ticketDTO, Ticket.class);
        }
        ticketReponsitory.save(ticket);
    }

    @Override
    public void create(TicketDTO ticketDTO) {
        ticketReponsitory.save(convertToEntity(ticketDTO));
    }

    @Override
    public TicketDTO getById(Integer id) {
        return convertToDto(ticketReponsitory.findById(id).orElse(null));
    }
}
