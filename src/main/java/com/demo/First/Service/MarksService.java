package com.demo.First.Service;

import java.util.List;
import com.demo.First.DTO.MarksResponseDTO;
import com.demo.First.DTO.MarksRequest;
import com.demo.First.Model.Marks;

public interface MarksService {
    public String createMarks(MarksRequest marks);
    public String updateMarks(Marks marks);
    public String deleteMarks(Long marksID);
    public Marks getMarks(Long marksID);
    public List<MarksResponseDTO> getAllMarksDTO();
    public MarksResponseDTO getMarksDTO(Long marksID);

}
