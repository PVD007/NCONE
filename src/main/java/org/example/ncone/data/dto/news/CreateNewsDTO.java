package org.example.ncone.data.dto.news;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;


@Getter
@Setter
public class CreateNewsDTO {

    @NotBlank
    private String headline;

    @NotBlank
    private String description;

    @NotNull
    private Instant publicationTime;

}
