package com.springapi.services;

import com.springapi.payload.response.StatusResponse;
import com.springapi.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    public List<StatusResponse> getAllStatus() {
        return statusRepository.findAll().stream()
                .map(status -> new StatusResponse(status.getStatus_id(), status.getStatus()))
                .collect(java.util.stream.Collectors.toList());
    }
}
