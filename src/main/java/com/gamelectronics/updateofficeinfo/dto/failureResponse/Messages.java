package com.gamelectronics.updateofficeinfo.dto.failureResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Messages {
    ArrayList<MessageDetails> messages;
}
