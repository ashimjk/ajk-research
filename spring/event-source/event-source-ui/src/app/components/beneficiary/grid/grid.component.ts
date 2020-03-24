import {Component, OnInit} from '@angular/core';
import {Beneficiary, BeneficiaryService} from '../services/beneficiary.service';

@Component({
  selector: 'app-grid',
  templateUrl: 'grid.component.html',
  providers: [BeneficiaryService]
})
export class GridComponent implements OnInit {
  beneficiaries: Beneficiary[] = [];

  constructor(private valueService: BeneficiaryService) {
  }

  ngOnInit() {
    // this.valueService.getAll().subscribe(list => this.beneficiaries = list);

    this.valueService.listBeneficiaryStream().subscribe({
      next: (event) => {
        const data = event.data;
        this.beneficiaries.push(JSON.parse(data));
      }
    });
  }
}
