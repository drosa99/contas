package br.com.contas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageOut<T> {
    private Long totalElements;
    private Long totalPages;
    private List<T> elements;
}
