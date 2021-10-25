import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private localStorageService: LocalStorageService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const userToken = this.localStorageService.getToken();
    console.log("User Token is ::: " + userToken);

    const isApiUrl = request.url.indexOf("seller") !== -1;
    console.log("Api URL is =" + isApiUrl);

    if (isApiUrl) {
      request = request.clone({ 
        headers: request.headers.set('Authorization', `Bearer ${userToken}`),
      });
      
    }

    return next.handle(request);

  }
}
