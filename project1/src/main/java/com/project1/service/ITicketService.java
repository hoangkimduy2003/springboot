package com.project1.service;

import com.project1.dto.PageDTO;
import com.project1.dto.SearchDTO;
import com.project1.dto.TicketDTO;
import com.project1.entity.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface ITicketService extends IObjectService<TicketDTO> {

    PageDTO<List<TicketDTO>> searchByCreatedAt(SearchDTO searchDTO, int page);
}
