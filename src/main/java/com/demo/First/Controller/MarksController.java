package com.demo.First.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.First.Model.Marks;
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
    public String createMarks(@RequestBody Marks marks){
        return marksService.createMarks(marks);
    }
    @PutMapping
    public String updateMarks(@RequestBody Marks marks){
        return marksService.updateMarks(marks);
    }
    @DeleteMapping("/{marksId}")
    public String deleteMarks(@PathVariable Long marksId){
        return marksService.deleteMarks(marksId);
    }
    @GetMapping("/{marksId}")
    public MarksDTO getMarks(@PathVariable Long marksId) {
        return marksService.getMarksDTO(marksId);
    }
    
    @GetMapping
    public List<MarksDTO> createMarks(){
        return marksService.getAllMarksDTO();
    }
}