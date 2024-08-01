package org.example.ncone.data.dto.news;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NewsDTO {

    private Long id;
    private String headline;
    private String description;
    private Instant publicationTime;

}
