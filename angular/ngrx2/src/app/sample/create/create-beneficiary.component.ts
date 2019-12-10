import {Component, OnInit} from '@angular/core';
import {CorpayBreadCrumbItem} from '@corpay/controls';
import {Observable} from 'rxjs';
import {FormGroupState} from 'ngrx-forms';
import {Store} from '@ngrx/store';
import {BeneficiaryEntityFacade} from '../facades/beneficiary-entity-facade.service';
import {BENEFICIARY_FEATURE_NAME} from '../../state/contants';
import {BENEFICIARY_CREATE_FORM_ID, BeneficiaryCreateForm} from "../create/store/beneficiary-create.state";

@Component({
  selector: 'create-beneficiary',
  template: `
    <form *ngIf="formState$" novalidate [ngrxFormState]="(formState$ | async)">
      <corpay-layouts-container>
        <corpay-layouts-container-header position="header">
          <corpay-layouts-flex-justify-content-between>
            <corpay-breadcrumbs [items]="breadcrumbs"></corpay-breadcrumbs>
            <div class="d-flex justify-content-end">
              <corpay-button
                [styleClass]="'btn-outline-dark'"
                [icon]="'far fa-ban'"
                [label]="'Cancel'">
              </corpay-button>
              <div class="p-1"></div>
              <corpay-button
                [styleClass]="'btn-outline-primary'"
                [icon]="'far fa-save'"
                [label]="'Save'">
              </corpay-button>
              <div class="p-1"></div>
              <corpay-button
                [styleClass]="'btn-primary'"
                icon="fas fa-arrow-circle-right"
                (onClick)="submit()"
                [label]="'Submit'">
              </corpay-button>
            </div>
          </corpay-layouts-flex-justify-content-between>
        </corpay-layouts-container-header>

        <corpay-layouts-container-sidebar position="sidebar" #sidebar
                                          [activeAccordion]="activeAccordion"
                                          [activeAccordionItem]="activeAccordionItem">

          <corpay-layouts-container-sidebar-accordion header="Basic Info" #activeAccordion>

                        <corpay-layouts-container-sidebar-accordion-item label="Beneficiary Info"
                                                                         [bodyItem]="basicInfo">
                        </corpay-layouts-container-sidebar-accordion-item>

                        <corpay-layouts-container-sidebar-accordion-item label="Verification IDs" #activeAccordionItem
                                                                         [bodyItem]="verificationIds">
                        </corpay-layouts-container-sidebar-accordion-item>

          </corpay-layouts-container-sidebar-accordion>

        </corpay-layouts-container-sidebar>

        <corpay-layouts-container-viewport position="viewport">
          <corpay-layouts-container-viewport-body
            [scrollable]="true"
            [scrollableHeight]="400"
            [sidebar]="sidebar"
            [hasHeader]="true">

            <corpay-anchor-nav-button
              position="leftHeader"
              [label]="'Beneficiary '"
              [icon]="'fas fa-arrow-left font-weight-bold'"
              [routerLink]="'/ben'"
              [description]="'Created by Omar Mismar, on Nov 16, 2019 12:10:03 PM.'"
            ></corpay-anchor-nav-button>

            <corpay-status-label
              position="rightHeader"
              [status]="'Draft'"
            ></corpay-status-label>

            <corpay-layouts-container-form-mode position="formMode"></corpay-layouts-container-form-mode>

                        <corpay-layouts-container-viewport-body-item #basicInfo>
                          <beneficiary-info [formState$]="formState$"></beneficiary-info>
                        </corpay-layouts-container-viewport-body-item>

                        <corpay-layouts-container-viewport-body-item #verificationIds>
                          <verification-ids [formState$]="formState$"></verification-ids>
                        </corpay-layouts-container-viewport-body-item>

          </corpay-layouts-container-viewport-body>
        </corpay-layouts-container-viewport>

        <corpay-layouts-container-footer position="footer"></corpay-layouts-container-footer>

      </corpay-layouts-container>
    </form>
  `
})
export class CreateBeneficiaryComponent implements OnInit {

  breadcrumbs: CorpayBreadCrumbItem[] = [
    {label: 'Configuration', icon: 'fas fa-home', routerLink: ['/modules']},
    {label: 'Beneficiaries', routerLink: ['/modules', 'ben']},
    {label: 'Beneficiary (New)'}
  ];

  formState$: Observable<FormGroupState<BeneficiaryCreateForm>>;
  submittedValue: BeneficiaryCreateForm;

  constructor(private facade: BeneficiaryEntityFacade, private store: Store<any>) {
    this.formState$ = this.store.select(state => state[BENEFICIARY_FEATURE_NAME][BENEFICIARY_CREATE_FORM_ID]);

    this.formState$.subscribe(value => {
      this.submittedValue = value.value;
    });
  }

  ngOnInit() {

  }

  submit() {
    alert(this.submittedValue);
  }

}
