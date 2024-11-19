package com.petr.experience_service.controlller;

import com.petr.experience_service.dto.ExperienceRequestDto;
import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.service.ExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController()
@RequestMapping("/api/v1/exs")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ExperienceResponseDto> getExperienceById(@PathVariable("id") Long id) {
        ExperienceResponseDto responseDto = experienceService.getExperience(id);
        if (isNull(responseDto)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ExperienceResponseDto>> getExperienceById() {
        List<ExperienceResponseDto> responseDto = experienceService.getAllExperience();
        if (responseDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ExperienceResponseDto> addExperience(@Valid @RequestBody ExperienceRequestDto requestDto) {
        ExperienceResponseDto responseDto = experienceService.saveExperience(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getIndustry/{id}")
    public Long getIndustryId(@PathVariable("id") Long id) {
        return experienceService.getIndustry(id);
    }

}
