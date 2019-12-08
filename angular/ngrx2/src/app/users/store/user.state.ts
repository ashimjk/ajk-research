export interface UserState {
  users: User[];
}

export interface User {
  username: string;
  email: string;
}

export const initialUserState: UserState = {
  users: [
    {
      username: 'ashim',
      email: 'ashim.khadka@clusus.com'
    }
  ]
};
