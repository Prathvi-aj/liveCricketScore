import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'wpl-point-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './wpl-points-table.component.html',
  styleUrl: './wpl-points-table.component.css',
})
export class WPLPointTableComponent implements OnInit {
  pointTable: any;
  pointTableRow:any

  constructor(private _api: ApiService) {}
  ngOnInit(): void {
    this.loadTable();
  }
  loadTable() {
    this._api.getWPL2024PointTable().subscribe({
      next: (data) => {
        this.pointTable = data;
        console.log(this.pointTable);        
        this.pointTableRow=[...this.pointTable[0]]
       
        this.pointTable=this.pointTable.filter((item:any,index:any)=>index>0)
       
        
        
       
        
      },
    });
  }
}
