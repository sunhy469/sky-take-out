package com.sky.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Employee员工实体类")
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "员工id")
    private Long id;

    @Schema(name = "username", description = "员工用户名")
    private String username;

    @Schema(name = "name", description = "员工姓名")
    private String name;

    @Schema(name = "password", description = "员工密码")
    private String password;

    @Schema(name = "phone", description = "员工手机号")
    private String phone;

    @Schema(name = "sex", description = "员工性别")
    private String sex;

    @Schema(name = "idNumber", description = "员工身份证号")
    private String idNumber;

    @Schema(name = "status", description = "员工状态")
    private Integer status;


    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
