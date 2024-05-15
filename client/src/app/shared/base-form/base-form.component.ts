import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-base-form',
  templateUrl: './base-form.component.html',
  styleUrl: './base-form.component.css'
})
export class BaseFormComponent {
  @Input() title: string = '';
  @Input() backgroundImage: string = '';
}
