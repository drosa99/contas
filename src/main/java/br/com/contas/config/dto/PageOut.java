package br.com.contas.config.dto;

import java.util.List;

public class PageOut<T> {
    private Long totalElements;
    private Long totalPages;
    private List<T> elements;

    public PageOut() {
    }

    public PageOut(Long totalElements, Long totalPages, List<T> elements) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.elements = elements;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
