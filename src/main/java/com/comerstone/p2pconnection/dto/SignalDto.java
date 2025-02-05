package com.comerstone.p2pconnection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalDto {

    private String from;
    private String to;
    private String type;
    private Object data;
}
