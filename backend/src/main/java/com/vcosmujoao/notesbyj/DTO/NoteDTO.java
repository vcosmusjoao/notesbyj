package com.vcosmujoao.notesbyj.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vcosmujoao.notesbyj.entity.Note;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoteDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;

    public NoteDTO(Long id, String titulo, String descricao, LocalDateTime dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    public NoteDTO() {
    }
    public NoteDTO(Note entity){
        this.id = entity.getId();
        this.titulo = entity.getTitulo();
        this.descricao = entity.getDescricao();
        this.dataCriacao = entity.getDataCriacao();
        this.dataCriacao = entity.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
