import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
@Injectable()
export class TokenInspectorService implements HttpInterceptor{

  constructor(private authService:AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
      const token=this.authService.getToken();

      if(token){
        const clonedReq=req.clone({
          setHeaders: {Authorization: `Bearer ${token}`}
        });
        return next.handle(clonedReq)
      }
      return next.handle(req);
  }
}
