import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {forkJoin, Observable} from 'rxjs';
import { map } from 'rxjs/operators';

import { IAutoEntityService, IEntityInfo } from '@briebug/ngrx-auto-entity';
import { environment } from '../../environments/environment';

class PageInfo {
}

@Injectable()
export class EntityService implements IAutoEntityService<any> {
  constructor(private http: HttpClient) {
  }

  load(entityInfo: IEntityInfo, id: any, criteria?: any): Observable<any> {
    return this.http.get<any>(
      `${environment.rootUrl}/${entityInfo.modelName}/${id}`,
      {params: criteria ? criteria.query || {} : {}}
    );
  }

  loadAll(entityInfo: IEntityInfo): Observable<any[]> {
    return this.http.get<any[]>(
      `${environment.rootUrl}/${entityInfo.modelName}`
    );
  }

  loadMany(entityInfo: IEntityInfo, criteria: any): Observable<any[]> {
    return this.http.get<any[]>(
      `${environment.rootUrl}/${entityInfo.modelName}`,
      {params: criteria ? criteria.query || {} : {}}
    );
  }

  // loadPage(entityInfo: IEntityInfo, page: PageInfo, criteria: any): Observable<any[]> {
  //   return this.http.get<any[]>(
  //     `${environment.rootUrl}/${entityInfo.modelName}`,
  //     {params: criteria ? criteria.query || {} : {}}
  //   ).pipe(
  //     map(entities => ({ // Must return entities with page info!
  //       entityInfo,
  //       pageInfo,
  //       entities
  //     }))
  //   );
  // }

  create(entityInfo: IEntityInfo, entity: any): Observable<any> {
    return this.http.post<any>(
      `${environment.rootUrl}/${entityInfo.modelName}`,
      entity
    );
  }

  update(entityInfo: IEntityInfo, entity: any): Observable<any> {
    return this.http.patch<any>(
      `${environment.rootUrl}/${entityInfo.modelName}/${entity.id}`,
      entity
    );
  }

  replace(entityInfo: IEntityInfo, entity: any): Observable<any> {
    return this.http.put<any>(
      `${environment.rootUrl}/${entityInfo.modelName}/${entity.id}`,
      entity
    );
  }

  delete(entityInfo: IEntityInfo, entity: any): Observable<any> {
    return this.http.delete<any>(
      `${environment.rootUrl}/${entityInfo.modelName}/${entity.id}`
    ).pipe(map(() => entity)); // Must return entity with key
  }

  deleteMany(entityInfo: IEntityInfo, entities: any[]): Observable<any[]> {
    return forkJoin(
      entities.map(entity => this.delete(entityInfo, entity))
    );
  }
}
