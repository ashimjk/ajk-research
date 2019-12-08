export interface UserState {
  users: User[];
  error: string;
}

export interface User {
  username: string;
  email: string;
}

export const initialUserState: UserState = {
  users: [],
  error: ''
};
