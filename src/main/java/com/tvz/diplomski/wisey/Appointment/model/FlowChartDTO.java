package com.tvz.diplomski.wisey.Appointment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowChartDTO {
    private ArrayList<String> dates;
    private ArrayList<Integer> numberOfStudents;
}
