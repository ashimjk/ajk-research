import {Component} from '@angular/core';

class ProfilePicture {
  encodedImage: string;
  imageUrl: string;
  file: File;
}

@Component({
  selector: 'app-profile-picture',
  templateUrl: './profile-picture.component.html',
  styleUrls: ['./profile-picture.component.css']
})
export class ProfilePictureComponent {

  selectedProfilePicture: ProfilePicture;
  errorMessage: string;

  getFile(files: File[]): File {
    const file = files.length === 0 ? null : files [0];

    if (file) {
      const mimeType = file.type;

      if (mimeType.match(/image\/*/) == null) {
        this.errorMessage = 'Only images are supported.';
        return null;
      }
    }

    return file;
  }

  imageUrl(event: any) {
    const file = this.getFile(event.target.files);

    if (file) {
      const URL = window.URL;
      const imageUrl = URL.createObjectURL(file);

      this.selectedProfilePicture = new ProfilePicture();
      this.selectedProfilePicture.imageUrl = imageUrl;
      this.selectedProfilePicture.file = file;
    }
  }

  encodedImage(files: any) {
    const file = this.getFile(files);

    if (file) {
      const reader = new FileReader();
      this.selectedProfilePicture = new ProfilePicture();
      this.selectedProfilePicture.file = file;

      reader.readAsDataURL(file);
      reader.onload = (eventFile: any) => {
        this.selectedProfilePicture.encodedImage = eventFile.target.result;
      };
    }
  }
}
