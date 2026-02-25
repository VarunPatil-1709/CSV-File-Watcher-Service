package com.FileWatcherService.Service;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FileWatcherService.DTO.UserCsvDto;
import com.FileWatcherService.Entity.User;
import com.FileWatcherService.Repo.UserRepository;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class CsvProcessorService {

    private final UserRepository userRepository;

    public CsvProcessorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void process(Path filePath) {

        List<User> users = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(filePath);
             CSVParser parser = new CSVParser(reader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : parser) {

                UserCsvDto dto = new UserCsvDto(
                        record.get("name"),
                        record.get("email"),
                        record.get("phone")
                );

                validate(dto);

                User user = new User();
                user.setName(dto.getName());
                user.setEmail(dto.getEmail());
                user.setPhone(dto.getPhone());

                users.add(user);
            }

            userRepository.saveAll(users);

        } catch (Exception e) {
            throw new RuntimeException("Failed to process CSV", e);
        }
    }

    private void validate(UserCsvDto dto) {
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new RuntimeException("Email cannot be empty");
        }
    }
}