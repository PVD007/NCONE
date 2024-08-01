package org.example.ncone.data.mapper;

import org.example.ncone.data.domain.News;
import org.example.ncone.data.dto.news.CreateNewsDTO;
import org.example.ncone.data.dto.news.NewsDTO;
import org.example.ncone.data.dto.news.UpdateNewsDTO;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public News toCreateEntity(CreateNewsDTO createNewsDTO) {
        News news = new News();

        news.setHeadline(createNewsDTO.getHeadline());
        news.setDescription(createNewsDTO.getDescription());
        news.setPublicationTime(createNewsDTO.getPublicationTime());

        return news;
    }

    public News toUpdateEntity(News news, UpdateNewsDTO updateNewsDTO) {
        return news;
    }

    public NewsDTO toDto(News news) {
        if (news == null) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId(news.getId());
        newsDTO.setHeadline(news.getHeadline());
        newsDTO.setDescription(news.getDescription());
        newsDTO.setPublicationTime(news.getPublicationTime());

        return newsDTO;
    }

}
