package com.vcosmujoao.notesbyj.repository;

import com.vcosmujoao.notesbyj.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Note,Long> {
}
