import { Note } from './../models/note';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

}
