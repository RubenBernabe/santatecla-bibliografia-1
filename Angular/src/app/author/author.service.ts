import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { HttpHeaders } from '@angular/common/http';


export interface Author {
  id?: number;
  name: string;
  urlImage?: string;
  birthDate?: Date;
  deathDate?: Date;
  bornPlace?: string;
  urlMap?: string;
  imgId?: number;
}

const URL = '/api/authors';


@Injectable()
export class AuthorService {
    constructor(private http: Http) {}

    getAuthors(customURL: string) {
        return this.http.get(URL+customURL,{ withCredentials: false })
          .pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
        );
    }

    getAuthor(id: number | string) {
      return this.http.get(URL + "/" +id, { withCredentials: true })
        .pipe(
            map(response => response.json()),


            catchError(error => this.handleError(error))
        );
    }

    // postAuthor(url: string, param: any) {
    //   let body = JSON.stringify(param);
    //   return this.http
    //       .post(url, body)
    //       .map(this.extractData)
    //       .catchError(this.handleError);
    //   }

    deleteAuthor(author: Author) {
      const headers = new Headers({
        'X-Requested-With': 'XMLHttpRequest'
      });
      const options = new RequestOptions({ withCredentials: true, headers });
  
      return this.http.delete(URL + "/" + author.id, options)
        .pipe(
          map(response => undefined),
          catchError(error => this.handleError(error))
        );
    }

    searchAuthor(name:string){
      return this.http.get(URL + "?name=" + name, { withCredentials: true })
        .pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
        );
    }

    saveAuthor (author:Author){
      const body= JSON.stringify(author)
      const headers = new Headers({'Content-Type': 'application/json',withCredentials: true});

      if(!author.id){
        return this.http.post(URL + "/" ,body, {headers})
        .pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
        );
      }
      else{
        return this.http.patch(URL + "/" +author.id,body, {headers})
        .pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
        );
      }  
    }


    private extractData(res: Response) {
      let body = res.json();
      return body || {};
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error author (' + error.status + '): ' + error.text());
    }
}
