import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';
import { single, multi, pie, times } from './data';

@Component({
    selector: 'bookForm',
    templateUrl: './bookForm.component.html',
    styleUrls: ['./app.component.css'],
    animations: [tdRotateAnimation],
})
export class BookFormComponent {
   


// Timeframe
date: Date = new Date(new Date().getTime() - 2 * 60 * 60 * 24 * 1000);



}