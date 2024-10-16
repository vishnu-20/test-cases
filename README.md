import { TestBed } from "@angular/core/testing";
import { FinancialInstitutionSearchComponent } from "./financial-institution-search.component";
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { of } from "rxjs";
import { StaticReferenceDataService } from "app/services/common/ReferenceDataService/static-reference-data.service";
import { FinancialInstitutionSearchService } from "app/services/utilities/referenceinformation/financiaInstitutionSearch/financial-institution-search.service";
import { Store } from "@ngxs/store";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthorizationDataService } from "app/services/common/AuthorizationDataService/authorization-data.service";

// Mocking services
const mockStaticReferenceDataService = {
  getDropdownByCategory: jest.fn().mockReturnValue(of([{ label: "Oregon", value: "OR" }])),
};

const mockFinancialInstitutionSearchService = {
  getFinancialInstitutionSearchResult: jest.fn().mockReturnValue(of({ warningMessage: "", results: [] })),
};

const mockAuthDataService = {
  authResource: jest.fn().mockReturnValue(true),
};

const mockStore = {
  select: jest.fn().mockReturnValue(of({})),
  dispatch: jest.fn(),
};

describe("FinancialInstitutionSearchComponent", () => {
  let component: FinancialInstitutionSearchComponent;
  let fixture;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FinancialInstitutionSearchComponent],
      imports: [ReactiveFormsModule],
      providers: [
        FormBuilder,
        { provide: StaticReferenceDataService, useValue: mockStaticReferenceDataService },
        { provide: FinancialInstitutionSearchService, useValue: mockFinancialInstitutionSearchService },
        { provide: Store, useValue: mockStore },
        { provide: Router, useValue: { navigateByUrl: jest.fn() } },
        { provide: ActivatedRoute, useValue: {} },
        { provide: AuthorizationDataService, useValue: mockAuthDataService },
      ],
    });

    fixture = TestBed.createComponent(FinancialInstitutionSearchComponent);
    component = fixture.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });

  it("should initialize the form on ngOnInit", () => {
    const generateFormSpy = jest.spyOn(component, "generateForm");
    component.ngOnInit();
    expect(generateFormSpy).toHaveBeenCalled();
    expect(component.isAuthorized).toBe(true);
  });

  it("should generate the form correctly", () => {
    component.generateForm();
    expect(component.addressForm.contains("cityCode")).toBeTruthy();
    expect(component.financialInstitutionSearchCriteriaForm.contains("bankName")).toBeTruthy();
  });

  it("should mark form fields as touched", () => {
    component.generateForm();
    component.markAsTouched("bankNameAndStatusCodeStr");
    expect(component.financialInstitutionSearchCriteriaForm.get("bankName").touched).toBe(true);
  });

  it("should update validators based on search type", () => {
    component.generateForm();
    component.updateValidators("bankNameAndStatusCodeStr");
    expect(component.financialInstitutionSearchCriteriaForm.get("bankName").valid).toBe(false);
    expect(component.financialInstitutionSearchCriteriaForm.get("statusCode").valid).toBe(false);
  });

  it("should validate FEIN or PayorNumber correctly", () => {
    component.generateForm();
    component.validateFeinOrPayorNumber();
    expect(component.financialInstitutionSearchCriteriaForm.get("fein").valid).toBe(false);
  });

  it("should handle form submission", () => {
    const transformDataSpy = jest.spyOn(component, "transformData").mockReturnValue([]);
    component.generateForm();
    component.financialInstitutionSearchCriteriaForm.get("bankName").setValue("Some Bank");
    component.submitFinancialInstitutionSearchCriteriaForm(false);
    expect(mockFinancialInstitutionSearchService.getFinancialInstitutionSearchResult).toHaveBeenCalled();
    expect(transformDataSpy).toHaveBeenCalled();
  });

  it("should transform search results data correctly", () => {
    const rawResults = [{ overrideFinancialInstitutionAddress: {}, newAddress: {} }];
    const result = component.transformData(rawResults);
    expect(result).toEqual([{ overrideFinancialInstitutionAddress: null, fullAddress: null }]);
  });

  it("should navigate to 'add' page on add event", () => {
    const routerSpy = jest.spyOn(TestBed.inject(Router), "navigateByUrl");
    component.add({ mode: "add" });
    expect(routerSpy).toHaveBeenCalledWith("tools/referenceInformation/prepareFinancialInstitutionDetail");
  });

  it("should select or cancel record action", () => {
    component.selectOrCancel({ mode: "select", data: {} });
    expect(mockStore.dispatch).toHaveBeenCalled();
  });

  it("should handle onInput event for FEIN field", () => {
    const mockEvent = { target: { value: "1234567" } } as any;
    component.generateForm();
    component.onInput(mockEvent);
    expect(component.financialInstitutionSearchCriteriaForm.get("fein").value).toBe("1234567");
  });
});
