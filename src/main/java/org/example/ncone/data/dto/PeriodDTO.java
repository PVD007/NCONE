package org.example.ncone.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.ncone.Constants;
import org.example.ncone.ValidateRange;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PeriodDTO {

    @NotNull
    @ValidateRange
    private Constants.DataBinding.TimeRange period;

}
