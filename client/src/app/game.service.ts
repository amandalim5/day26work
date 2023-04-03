import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject, firstValueFrom, lastValueFrom, take } from 'rxjs';
import { Game } from './models';
import { GAME_API } from './constants';

@Injectable()
export class GameService {
  constructor(private httpClient: HttpClient) {}

  onGameId = new Subject<Game>();

  getagame(id: number) {
    let result$ = this.httpClient.get(`${GAME_API}game/${id}`).pipe(take(1));
    return lastValueFrom(result$)
      .then((data: any) => {
        console.info('we received something on angular!', data);
        let game = data as Game;
        this.onGameId.next(game);
        console.info('the previous line was used');
        return () => {};
      })
      .catch((message: any) => {
        console.info(message);
        this.onGameId.error(message);
        return () => {};
      });
  }
}
