export class ContactRequest {
  personalData: PersonalData;
  requestType: any = '';
  text = '';
}

export class PersonalData {
  email = '';
  mobile = '';
  country = '';
}
