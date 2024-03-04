package com.rbxu.market.domain.spi.dto.gooddoctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PersonDTO {

    /**
     * 病例ID
     */
    private String id;

    /**
     * 病人名称
     */
    private String name;
}
