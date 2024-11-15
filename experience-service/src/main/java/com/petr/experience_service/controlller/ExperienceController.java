package com.petr.experience_service.controlller;

import com.petr.experience_service.dto.ExperienceRequestDto;
import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.mapper.ExperienceMapper;
import com.petr.experience_service.service.ExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController()
@RequestMapping("/api/v1/exs")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @RequestMapping("/get/{id}")
    public ResponseEntity<ExperienceResponseDto> getExperienceById(@PathVariable("id") Long id) {
        ExperienceResponseDto responseDto = experienceService.getExperience(id);
        if (isNull(responseDto)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping("/save")
    public ResponseEntity<ExperienceResponseDto> saveExperience(@Valid @RequestBody ExperienceRequestDto requestDto) {
        experienceService.saveExperience(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
