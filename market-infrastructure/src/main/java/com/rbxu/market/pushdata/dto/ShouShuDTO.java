package com.rbxu.market.pushdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ShouShuDTO {

    private String ksId;

    private String shoushuId;

    private String shoushuName;

    private String date;

    private Boolean zhudao;

    private Integer num;
}
