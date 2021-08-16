import { Controller } from 'stimulus';
import { susses } from '../susses';

export default class extends Controller {
  static targets = [ 'sus' ];

  getRandomSus() {
    return susses[Math.floor(Math.random() * susses.length)];
  }

  capitalize(s) {
    return s.charAt(0).toUpperCase() + s.slice(1);
  }

  newRandomSus() {
    this.susTarget.innerText = this.capitalize(this.getRandomSus());
  }
}
