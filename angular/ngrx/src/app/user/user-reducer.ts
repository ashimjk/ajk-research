export function reducer(state, action) {
  switch (action.type) {
    case 'MASK_USER_NAME':
      console.log('existing state: ' + JSON.stringify(state));
      console.log('action: ' + JSON.stringify(action));

      return {...state, maskUserName: action.payload};

    default:
      return state;
  }
}
