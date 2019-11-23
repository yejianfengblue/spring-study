package com.yejianfengblue.spring.bean;

import com.yejianfengblue.spring.validation.IsFltDate;
import com.yejianfengblue.spring.validation.IsNumber;
import lombok.Data;

import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author yejianfengblue
 */
@Data
public class ValidatedBean {

    @NotNull
    private String notNullString;

    @Size(max = 10)
    private String maxSize10String;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal int10Fra2Decimal;

    @Pattern(regexp = "[IO]", message = "is not 'I' or 'O'")
    private String inOutInd;

    @IsFltDate
    private String fltDateString;

    @IsNumber
    private String numberString;
}
