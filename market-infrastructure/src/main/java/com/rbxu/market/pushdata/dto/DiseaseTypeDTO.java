package com.rbxu.market.pushdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DiseaseTypeDTO {

    private Integer ksId;

    private String ksName;

    private Integer bzId;

    private String bzName;
}
