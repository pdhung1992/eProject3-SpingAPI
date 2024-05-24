package com.springapi.services;

import com.springapi.payload.response.ServeTypeResponse;
import com.springapi.repositories.ServeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServeTypeService {
    @Autowired
    ServeTypeRepository serveTypeRepository;

    public List<ServeTypeResponse> getAllServeTypes() {
        return serveTypeRepository.findAll().stream()
                .map(serveType -> new ServeTypeResponse(serveType.getServe_type_id(), serveType.getServe_type_name(), serveType.getValue()))
                .collect(java.util.stream.Collectors.toList());
    }
}
