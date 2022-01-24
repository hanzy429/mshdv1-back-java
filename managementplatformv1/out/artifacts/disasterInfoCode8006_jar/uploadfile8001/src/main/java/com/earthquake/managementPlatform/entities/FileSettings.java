package com.earthquake.managementPlatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileSettings {
    private boolean isRead;
    private String cron;
}
