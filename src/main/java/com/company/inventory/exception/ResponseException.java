package com.company.inventory.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseException {
    private String date;
    private String message;
    private String path;
}
