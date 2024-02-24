import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WPLPointTableComponent } from './wpl-points-table.component';

describe('WPLPointTableComponent', () => {
  let component: WPLPointTableComponent;
  let fixture: ComponentFixture<WPLPointTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WPLPointTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WPLPointTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
