import {Key} from '@briebug/ngrx-auto-entity';

export class Customer {
  @Key id?: number;
  name: string;
  title: string;
  email: string;
  handles?: {
    twitter?: string;
    facebook?: string;
  };
  addressId?: number;
}
