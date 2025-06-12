package com.mightygroups.backend.controller;

import com.mightygroups.backend.model.GroupFare;
import com.mightygroups.backend.security.JwtTokenProvider;
import com.mightygroups.backend.service.GroupFareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-fares")
@RequiredArgsConstructor
public class GroupFareController {

    private final GroupFareService groupFareService;
    private final JwtTokenProvider jwtTokenProvider;

    // ✅ GET All Group Fares
    @GetMapping
    public ResponseEntity<List<GroupFare>> getAllGroupFares() {
        List<GroupFare> fares = groupFareService.getAllGroupFares();
        return ResponseEntity.ok(fares);
    }

    // ✅ GET Group Fare by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupFare> getGroupFareById(@PathVariable String id) {
        return groupFareService.getGroupFareById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST Create new Group Fare (with uploadedBy from token)
    @PostMapping
    public ResponseEntity<GroupFare> createGroupFare(@RequestBody GroupFare groupFare,
                                                     @RequestHeader("Authorization") String token) {
        // 🔐 Token cleanup
        token = token.replace("Bearer ", "");

        // 🧑‍💻 Extract user ID from token
        String uploaderId = jwtTokenProvider.getUserIdFromJWT(token);

        // 📌 Set uploader ID
        groupFare.setUploadedBy(uploaderId);

        // 💾 Save GroupFare
        GroupFare savedFare = groupFareService.createGroupFare(groupFare);
        return ResponseEntity.ok(savedFare);
    }
}
