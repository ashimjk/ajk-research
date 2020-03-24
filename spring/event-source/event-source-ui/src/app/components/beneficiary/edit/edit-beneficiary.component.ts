import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BeneficiaryService} from '../services/beneficiary.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-edit-beneficiary',
  templateUrl: 'edit-beneficiary.component.html',
  providers: [BeneficiaryService]
})
export class EditBeneficiaryComponent implements OnInit {
  beneficiaryForm: FormGroup;

  constructor(private valueService: BeneficiaryService,
              private route: ActivatedRoute,
              private router: Router,
              private fb: FormBuilder) {
    this.beneficiaryForm = this.fb.group({
      id: [],
      fullName: ['', Validators.required],
      nationality: ['']
    });
  }

  ngOnInit() {
    const valueId = this.route.snapshot.params.id;
    this.valueService.get(valueId).subscribe(value =>
      this.beneficiaryForm = this.fb.group({
        id: [value.id],
        fullName: [value.fullName, [Validators.required, Validators.minLength(2)]],
        nationality: [value.nationality]
      })
    );
  }

  updateValue() {
    const value = this.beneficiaryForm.value;
    if (this.beneficiaryForm.valid) {
      this.valueService.update(value).subscribe(updatedValue =>
        this.router.navigate(['list'])
      );
    }
  }
}
