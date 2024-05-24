package com.springapi.services;

import com.springapi.payload.response.FoodTagResponse;
import com.springapi.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public List<FoodTagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new FoodTagResponse(tag.getTag_id(), tag.getTag_name()))
                .collect(java.util.stream.Collectors.toList());
    }
}
