import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DynamicDialogRef, DialogService, DynamicDialogConfig} from 'primeng/dynamicdialog';
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
  note: Note;
  isNoteUpdate: boolean;

  constructor(
    public ref: DynamicDialogRef,
    public config : DynamicDialogConfig,
    private fb: FormBuilder,
    private noteService: NoteService,
    private formBuilder: FormBuilder
    ) {}

  ngOnInit(): void {
    this.note = this.config.data?.note;
    this.isNoteUpdate = !!this.note;
    this.createNoteForm();
    if(this.note){
      this.noteForm.setValue({
        titulo: this.note?.titulo,
        descricao: this.note?.descricao
      });
    }
  }

  createNoteForm(){
    this.noteForm = this.formBuilder.group({
      titulo:['', Validators.required],
      descricao:[],
    });
  }



  get controls(){
    return this.noteForm.controls;
  }

  saveNote(){
    var note = new Note();
    note.titulo = this.controls['titulo'].value;
    note.descricao = this.controls['descricao'].value;

    if(this.isNoteUpdate){
      note.id = this.note.id;
      this.noteService.updateNote(note).subscribe(()=>{
        this.ref.close();
      });
    }else{
      this.noteService.createNote(note).subscribe(x=>{
        this.ref.close();
      });
    }
  }

}
