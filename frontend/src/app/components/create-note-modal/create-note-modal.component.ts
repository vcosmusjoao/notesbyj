import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DynamicDialogRef, DialogService} from 'primeng/dynamicdialog';
import { NoteService } from 'src/app/services/note.service';
import { Note } from 'src/app/models/note';

@Component({
  selector: 'create-note-modal',
  templateUrl: './create-note-modal.component.html',
  styleUrls: ['./create-note-modal.component.css']
})
export class CreateNoteModalComponent implements OnInit {

  display: boolean = true;
  noteForm: FormGroup;


  constructor(
    public ref: DynamicDialogRef,
    private fb: FormBuilder,
    private noteService: NoteService,
    private formBuilder: FormBuilder
    ) {}

  ngOnInit(): void {
    this.createNoteForm();
  }

  createNoteForm(){
    this.noteForm = this.formBuilder.group({
      titulo:[],
      descricao:[],
    });
  }

  get controls(){
    return this.noteForm.controls;
  }

  saveNewNote(){
    var note = new Note();
    note.titulo = this.controls['titulo'].value;
    note.descricao = this.controls['descricao'].value;
    this.noteService.createNote(note).subscribe(x=>{
      this.ref.close();
    })
  }



}
