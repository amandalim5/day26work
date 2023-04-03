import { Component, OnInit, OnDestroy } from '@angular/core';
import { GameService } from '../game.service';
import { Game } from '../models';
import { Observable, Subscription } from 'rxjs';
import { GetagameComponent } from './getagame.component';

@Component({
  selector: 'app-displayagame',
  templateUrl: './displayagame.component.html',
  styleUrls: ['./displayagame.component.css'],
})
export class DisplayagameComponent implements OnInit, OnDestroy {
  constructor(private gameSvc: GameService) {}
  game$!: Observable<Game>;
  chosengame!: Game;
  subscription!: Subscription;
  message: string = '';
  exist: boolean = false;
  observer = {
    next: (value: Game) => {
      this.exist = true;
      console.info('this is the info ', value.game_id);
      this.message = '';
      this.chosengame = value;
    },
    error: (value: any) => {
      this.exist = false;
      console.info('this is the error: ', value);
      console.info('this is the error: ', value.error);
      this.message = value.error;
      this.subscription.unsubscribe();
    },
  };
  ngOnInit(): void {
    this.subscription = this.gameSvc.onGameId.subscribe(this.observer);
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
