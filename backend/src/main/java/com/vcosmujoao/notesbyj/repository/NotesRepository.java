package com.vcosmujoao.notesbyj.repository;

import com.vcosmujoao.notesbyj.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface NotesRepository extends JpaRepository<Note,Long> {

    @Query("SELECT n FROM Note n ORDER BY n.dataCriacao DESC")
    Page<Note> findAll(Pageable pageable);

}
