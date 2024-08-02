package org.example.ncone.controller;

import org.example.ncone.data.dto.PeriodDTO;
import org.example.ncone.data.dto.news.CreateNewsDTO;
import org.example.ncone.data.dto.news.NewsDTO;
import org.example.ncone.data.dto.news.UpdateNewsDTO;
import org.example.ncone.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public abstract class AbstractNewsController {

    @Autowired
    protected NewsService newsService;

    @PreAuthorize("hasAnyAuthority(@environment.getProperty('module.security.role.news.create'))")
    @PostMapping
    public NewsDTO createNews(@Valid @ModelAttribute CreateNewsDTO createNewsDTO) {
        return newsService.create(createNewsDTO);
    }

    @PreAuthorize("hasAnyAuthority(@environment.getProperty('module.security.role.news.update'))")
    @PutMapping
    public NewsDTO updateNews(@Valid @ModelAttribute UpdateNewsDTO updateNewsDTO) {
        return newsService.update(updateNewsDTO);
    }

    @GetMapping("/by-published-date")
    @PreAuthorize("isAuthenticated()")
    public List<NewsDTO> getAllByPeriod(@Valid @ModelAttribute PeriodDTO periodDTO){
        return newsService.getAllByPeriod(periodDTO);
    }

    @GetMapping("/{id}")
    public NewsDTO getById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping
    public List<NewsDTO> getAllNews() {
        return newsService.getAll();
    }

    @PreAuthorize("hasAnyAuthority(@environment.getProperty('module.security.role.news.delete'))")
    @DeleteMapping("/news/{newsId}")
    public void deleteNews(@PathVariable Long newsId) {
        newsService.delete(newsId);
    }

}
