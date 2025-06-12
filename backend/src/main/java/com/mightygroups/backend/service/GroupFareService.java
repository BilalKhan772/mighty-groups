package com.mightygroups.backend.service;

import com.mightygroups.backend.model.GroupFare;
import com.mightygroups.backend.repository.GroupFareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupFareService {

    private final GroupFareRepository groupFareRepository;

    // Get all group fares
    public List<GroupFare> getAllGroupFares() {
        return groupFareRepository.findAll();
    }

    // Get by ID
    public Optional<GroupFare> getGroupFareById(String id) {
        return groupFareRepository.findById(id);
    }

    // Create a new group fare
    public GroupFare createGroupFare(GroupFare groupFare) {
        return groupFareRepository.save(groupFare);
    }

    // Filter by route
    public List<GroupFare> filterByFromAndTo(String from, String to) {
        return groupFareRepository.findByFromAndTo(from, to);
    }

    // Get all fares uploaded by a specific B2B user
    public List<GroupFare> getFaresByUploader(String userId) {
        return groupFareRepository.findByUploadedBy(userId);
    }
}
