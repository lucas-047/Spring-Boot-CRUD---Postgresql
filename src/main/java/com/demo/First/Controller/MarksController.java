package com.demo.First.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.demo.First.Model.Marks;
import com.demo.First.Response.ResponseHandler;
import com.demo.First.DTO.MarksDTO;
import com.demo.First.Service.MarksService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/marks")
@NoArgsConstructor
@AllArgsConstructor
public class MarksController {
    @Autowired
    MarksService marksService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createMarks(@RequestBody Marks marks) {
        marksService.createMarks(marks);
        return ResponseHandler.responseBuilder("Marks created Successfully", HttpStatus.CREATED, null);
    }

    @PutMapping
    public ResponseEntity<Object> updateMarks(@RequestBody Marks marks) {
        marksService.updateMarks(marks);
        return ResponseHandler.responseBuilder("Marks updated Successfully", HttpStatus.OK, null);
    }

    @DeleteMapping("/{marksId}")
    public ResponseEntity<Object> deleteMarks(@PathVariable Long marksId) {
        marksService.deleteMarks(marksId);
        return ResponseHandler.responseBuilder("Marks deleted Successfully", HttpStatus.OK, null);
    }

    @GetMapping("/{marksId}")
    public ResponseEntity<Object> getMarks(@PathVariable Long marksId) {
        MarksDTO marksDTO = marksService.getMarksDTO(marksId);
        return ResponseHandler.responseBuilder("Marks fetched Successfully", HttpStatus.OK, marksDTO);
    }

    @GetMapping
    public ResponseEntity<Object> createMarks() {
        List<MarksDTO> marksDTOs = marksService.getAllMarksDTO();
        return ResponseHandler.responseBuilder("Marks list fetched Successfully", HttpStatus.OK, marksDTOs);
    }
}