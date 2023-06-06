package com.mini.service;

import com.mini.dto.PageDTO;
import com.mini.dto.StatusDTO;
import com.mini.entity.Status;
import com.mini.reponsitory.StatusReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IStatusService {

    StatusDTO convertToDto(Status status);
    Status converToEntity(StatusDTO statusDTO);
    List<StatusDTO> getAll();
    StatusDTO getById(Long id);
    void create(StatusDTO statusDTO);
    void update(StatusDTO statusDTO);
    void delete(Long id);

    @Service
    class StatusService implements IStatusService{
        @Autowired
        private StatusReponsitory statusRepo;

        @Override
        public StatusDTO convertToDto(Status status) {
            return new ModelMapper().map(status,StatusDTO.class);
        }

        @Override
        public Status converToEntity(StatusDTO statusDTO) {
            return new ModelMapper().map(statusDTO,Status.class);
        }

        @Override
        public List<StatusDTO> getAll() {
            return statusRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public StatusDTO getById(Long id) {
            return convertToDto(statusRepo.findById(id).orElse(null));
        }

        @Override
        public void create(StatusDTO statusDTO) {
            statusRepo.save(converToEntity(statusDTO));
        }

        @Override
        public void update(StatusDTO statusDTO) {
            Status status = statusRepo.findById(statusDTO.getId()).orElse(null);
            if(status != null){
                status = converToEntity(statusDTO);
            }
            statusRepo.save(status);
        }

        @Override
        public void delete(Long id) {
            statusRepo.deleteById(id);
        }
    }
}
