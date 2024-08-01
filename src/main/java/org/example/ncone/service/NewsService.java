package org.example.ncone.service;

import org.example.ncone.data.domain.News;
import org.example.ncone.data.dto.PeriodDTO;
import org.example.ncone.data.dto.news.CreateNewsDTO;
import org.example.ncone.data.dto.news.NewsDTO;
import org.example.ncone.data.dto.news.UpdateNewsDTO;

import java.util.List;

public interface NewsService {

    NewsDTO create(CreateNewsDTO createNewsDTO);

    void save(News news);

    NewsDTO update(UpdateNewsDTO updateNewsDTO);

    NewsDTO getById(Long id);

    void delete(Long id);

    List<NewsDTO> getAll();

    boolean isExist(String newsHeadline);

    List<NewsDTO> getAllByPeriod(PeriodDTO periodDTO);
}
