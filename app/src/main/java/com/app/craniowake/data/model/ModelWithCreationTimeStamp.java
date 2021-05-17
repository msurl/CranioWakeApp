package com.app.craniowake.data.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Data;

@Data
public abstract class ModelWithCreationTimeStamp{

    private LocalDateTime dateTime;

    protected ModelWithCreationTimeStamp() {
        this.dateTime = LocalDateTime.now(ZoneId.systemDefault());
    }

}
