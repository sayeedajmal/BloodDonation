package com.strong.BloodDonation.Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BloodResponse {
    private String Message;
    private Integer Status;
    private long TimeStamp;
}