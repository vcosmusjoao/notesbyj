import { Note } from './../models/note';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { url } from 'inspector';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private httpClientService: HttpClient) { }

  note: Note[] = [];
  private baseUrl = 'http://localhost:8080/notes';

  getAllNotes(): Observable<any>{
    return this.httpClientService.get(this.baseUrl);
  }

  createNote(note: Note): Observable<any> {
    return this.httpClientService.post(this.baseUrl, note);
  }

  updateNote(note: Note): Observable<any> {
    return this.httpClientService.put(this.baseUrl + '/' + note.id, note);
  }

  getNoteById(id: number): Observable<any> {
    return this.httpClientService.get(this.baseUrl + '/' + id);
  }

}
