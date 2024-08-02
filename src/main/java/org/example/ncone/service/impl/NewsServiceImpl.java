package org.example.ncone.service.impl;

import org.example.ncone.Range;
import org.example.ncone.data.domain.News;
import org.example.ncone.data.dto.PeriodDTO;
import org.example.ncone.data.dto.news.CreateNewsDTO;
import org.example.ncone.data.dto.news.NewsDTO;
import org.example.ncone.data.dto.news.UpdateNewsDTO;
import org.example.ncone.data.mapper.NewsMapper;
import org.example.ncone.repository.NewsRepository;
import org.example.ncone.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final String NO_NEWS_WITH_SUCH_ID = "There is no News with this id";

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public NewsDTO create(CreateNewsDTO createVacancyDTO) {
        News news = newsMapper.toCreateEntity(createVacancyDTO);
        return newsMapper.toDto(newsRepository.save(news));
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public NewsDTO update(UpdateNewsDTO updateNewsDTO) {
        News news = newsRepository.getById(updateNewsDTO.getId());
        news = newsMapper.toUpdateEntity(news, updateNewsDTO);
        return newsMapper.toDto(newsRepository.save(news));
    }

    @Override
    @Transactional(readOnly = true)
    public NewsDTO getById(Long id) {
        return newsMapper.toDto(newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NO_NEWS_WITH_SUCH_ID)));
    }

    @Override
    public List<NewsDTO> getAll() {
        List<News> allNews = newsRepository.findAll();
        List<NewsDTO> newsDTOS = allNews
                .stream()
                .map(news -> newsMapper.toDto(news))
                .toList();
        return newsDTOS;
    }

    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public boolean isExist(String newsHeadline) {
        return newsRepository.existsByHeadline(newsHeadline);
    }

    @Override
    public List<NewsDTO> getAllByPeriod(PeriodDTO periodDTO) {
        Range<Long> periodLongRange = periodDTO.getPeriod();
        Range<Instant> periodInstantRange = mapRangeMillisecondListToInstant(periodLongRange);
        List<News> allNewsByPeriod = newsRepository.getAllByPeriod(
                periodInstantRange.getMin(),
                periodInstantRange.getMax()
        );
        return allNewsByPeriod
                .stream()
                .map(news -> newsMapper.toDto(news))
                .toList();
    }

    public static Range<Instant> mapRangeMillisecondListToInstant(Range<Long> millisecondRange) {
        if (Objects.isNull(millisecondRange)) {
            return null;
        }

        Range<Instant> rangeInstant = new Range<>();

        rangeInstant.setMin(Instant.ofEpochMilli(millisecondRange.getMin()));
        rangeInstant.setMax(Instant.ofEpochMilli(millisecondRange.getMax()));

        return rangeInstant;
    }

}
