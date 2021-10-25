import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { LocalStorageRefService } from "./local-storage-ref.service";

interface MyData {
  name: string;
  age: number;
}

@Injectable({ providedIn: "root" })
export class LocalStorageService {
  private _localStorage: Storage;

   constructor(private _localStorageRefService: LocalStorageRefService) {
    this._localStorage = _localStorageRefService.localStorage;
  }

 
  clearAllLocalStorage(): void {
    this._localStorage.clear();
  }

setToken(token: string): void {
  this._localStorage.setItem("token", token);
}

getToken(): string {
  return this._localStorage.getItem("token");
}

removeToken(): void {
  this._localStorage.removeItem("token");
}

}
