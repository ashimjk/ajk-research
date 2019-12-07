export class AuthState {
  token: string;
  authenticated: boolean;
}

export const initialAuthState: AuthState = {
  token: null,
  authenticated: false
};
