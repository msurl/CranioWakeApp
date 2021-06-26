package com.app.craniowake.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class University {
    private String name;
    private String location;

    @Override
    public String toString() {
        return name + ", " + location;
    }
}
