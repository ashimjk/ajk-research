import {createSelector} from '@ngrx/store';

export class AppState {
  user: UserState;
}

export class UserState {
  username: string;
  authentication: AuthenticationState;
}

export class AuthenticationState {
  isAuthenticated: boolean;
}

export const userFeature = (state: AppState) => state.user;
export const userFeatureUsername = createSelector(userFeature, (state: UserState) => state.username);

export const authenticationFeature = (state: UserState) => state.authentication;
// export const authenticationFeatureAuthenticated = createSelector(userFeature, authenticationFeature);

// (userState: UserState, authenticationState: AuthenticationState) => authenticationState.isAuthenticated;

createSelector(authenticationFeature, (state: AuthenticationState) => state.isAuthenticated);

export interface User {
  id: number;
  name: string;
}

export interface Book {
  id: number;
  userId: number;
  name: string;
}

export interface AppState {
  selectedUser: User;
  allBooks: Book[];
}

export const selectUser = (state: AppState) => state.selectedUser;
export const selectAllBooks = (state: AppState) => state.allBooks;

createSelector(selectUser, selectAllBooks, (user: User, books: Book[]) => {
  if (user && books) {
    return books.filter((book: Book) => book.userId === user.id);
  } else {
    return books;
  }
});

export const selectVisibleBooks = createSelector(
  selectUser,
  selectAllBooks,
  (selectedUser: User, allBooks: Book[]) => {
    if (selectedUser && allBooks) {
      return allBooks.filter((book: Book) => book.userId === selectedUser.id);
    } else {
      return allBooks;
    }
  }
);
