import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { GameService } from '../game.service';

@Component({
  selector: 'app-getagame',
  templateUrl: './getagame.component.html',
  styleUrls: ['./getagame.component.css'],
})
export class GetagameComponent implements OnInit {
  getagameForm!: FormGroup;
  constructor(private fb: FormBuilder, private gameSvc: GameService) {}
  ngOnInit(): void {
    this.getagameForm = this.fb.group({
      gid: this.fb.control<number>(0),
    });
  }
  processForm() {
    const gid = this.getagameForm.value.gid;
    console.info('you clicked submit: ', gid);
    this.gameSvc.getagame(gid);
    this.getagameForm.reset();
  }
}
