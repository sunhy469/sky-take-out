package com.sky.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "EmployeeLoginVO")
public class EmployeeLoginVO implements Serializable {

    /**
     *  //@ApiModel 注解用于描述一个数据模型（通常是一个类），
     *              以便 API 文档生成工具可以理解和生成相应的文档。
     *  // @ApiModelProperty 注解用于描述一个 model 的属性，
     */
    @Schema(name = "id", description = "员工id")
    private Long id;

    @Schema(name = "userName", description = "员工用户名")
    private String userName;

    @Schema(name = "name", description = "员工姓名")
    private String name;

    @Schema(name = "token", description = "员工token")
    private String token;

}
