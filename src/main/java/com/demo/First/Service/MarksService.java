package com.demo.First.Service;

import java.util.List;
import com.demo.First.DTO.MarksDTO;
import com.demo.First.Model.Marks;

public interface MarksService {
    public String createMarks(Marks marks);
    public String updateMarks(Marks marks);
    public String deleteMarks(Long marksID);
    public Marks getMarks(Long marksID);
    public List<MarksDTO> getAllMarksDTO();
    public MarksDTO getMarksDTO(Long marksID);

}
